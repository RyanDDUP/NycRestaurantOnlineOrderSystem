package web;

import domain.DinnerTable;
import domain.Food;
import domain.OrderDetail;
import domain.PageBean;
import service.impl.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by RZ on 4/23/16.
 */
public class OrderServlet extends BaseServlet {
    private Object addToCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<Integer,Integer> cart = (Map<Integer,Integer>)request.getSession().getAttribute("cart");
        Map<Integer, Food> cartfood = (Map<Integer,Food>)request.getSession().getAttribute("cartFood");
        if(cart==null) {
            cart = new LinkedHashMap<>();
            cartfood = new HashMap<>();
        }
        if(request.getParameter("foodId")!=null) {
            int foodId = Integer.parseInt(request.getParameter("foodId"));
            if (cart.containsKey(foodId)) {
                cart.put(foodId, cart.get(foodId) + 1);
            } else {
                cart.put(foodId, 1);
                cartfood.put(foodId, foodService.findById(foodId));
            }
            request.getSession().setAttribute("cart", cart);
            request.getSession().setAttribute("cartFood", cartfood);
        }
        return request.getRequestDispatcher("/app/food_cart.jsp");
    }

    private Object alterNum(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<Integer,Integer> cart = (Map<Integer,Integer>)request.getSession().getAttribute("cart");
        int foodId = Integer.parseInt(request.getParameter("foodId"));
        int num = Integer.parseInt(request.getParameter("num"));

        cart.put(foodId,num);
        request.getSession().setAttribute("cart",cart);
        return request.getRequestDispatcher("/app/food_cart.jsp");
    }
    private Object delById(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<Integer,Integer> cart = (Map<Integer,Integer>)request.getSession().getAttribute("cart");
        Map<Integer, Food> cartfood = (Map<Integer,Food>)request.getSession().getAttribute("cartFood");

        int foodId = Integer.parseInt(request.getParameter("foodId"));

        cart.remove(foodId);
        cartfood.remove(foodId);
        request.getSession().setAttribute("cart",cart);
        return request.getRequestDispatcher("/app/food_cart.jsp");
    }
    private Object putOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int tableId = Integer.parseInt((String)request.getSession().getAttribute("tableId"));
        if(dinnerTableService.findById(tableId).getTableStatus()==0) {
            Map<Integer, Integer> cart = (Map<Integer, Integer>) request.getSession().getAttribute("cart");
            Map<Integer, Food> cartfood = (Map<Integer, Food>) request.getSession().getAttribute("cartFood");
            int orderId = orderService.putOrder(cart, cartfood, tableId);
            request.getSession().setAttribute("orderId",orderId);//存数组,可以加菜
            request.getSession().removeAttribute("cart");
            request.getSession().removeAttribute("tableId");
            return checkOrder(request,response);
        }
        else return "error/error.jsp";
    }
    private Object checkOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
            Map<Integer, Food> cartfood = (Map<Integer, Food>) request.getSession().getAttribute("cartFood");
            int orderId =(int)request.getSession().getAttribute("orderId");
            List<OrderDetail> orderDetails = orderService.listByOrderId(orderId);
            request.setAttribute("orderDetails",orderDetails);
            return request.getRequestDispatcher("/app/orderlist.jsp");
    }
    private Object pay(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getSession().removeAttribute("cartFood");
        int orderId =(int)request.getSession().getAttribute("orderId");
        request.getSession().removeAttribute("orderId");
        int tableId = orderService.payByOrderId(orderId);
        dinnerTableService.reserve(tableId);
        return request.getRequestDispatcher("/app/pay.jsp");
    }

    /**
     * back end design
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    private Object list(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PageBean pb = new PageBean();
        pb.setCurrPageNum(1);
        String _currPageNum=request.getParameter("currPageNum");
        if(_currPageNum!=null&&!"".equals(_currPageNum.trim())) {
            pb.setCurrPageNum(Integer.parseInt(_currPageNum));
        }
        pb.setPageCount(15);
        List<DinnerTable> tables = dinnerTableService.list();
        orderService.list(pb);
        request.setAttribute("pb",pb);
        request.setAttribute("tables",tables);
        return request.getRequestDispatcher("/sys/order/orderList.jsp");
    }
    private Object orderDetails(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int orderId= Integer.parseInt(request.getParameter("orderId"));
        List<OrderDetail> details = orderService.findDetailsByOrderId(orderId);
        List<Food> foods = new ArrayList<>();
        for(OrderDetail i:details) {
            foods.add(foodService.findById(i.getFoodId()));
        }
        request.setAttribute("details",details);
        request.setAttribute("foods",foods);
        return request.getRequestDispatcher("/sys/order/orderDetail.jsp");
    }
    private Object check(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int orderId= Integer.parseInt(request.getParameter("orderId"));
        int tableId = orderService.check(orderId);
        dinnerTableService.reserve(tableId);
        return list(request,response);
    }

}
