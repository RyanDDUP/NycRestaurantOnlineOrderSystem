package web;

import domain.FoodType;
import factory.BeanFactory;
import org.apache.commons.beanutils.BeanUtils;
import service.IFoodTypeService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by RZ on 4/19/16.
 */
public class FoodTypeServlet extends HttpServlet {
    private Object url;
    private IFoodTypeService service = BeanFactory.getInstance("foodTypeService",IFoodTypeService.class);
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method= request.getParameter("method");
        if ("add".equals(method)) {
            add(request,response);
        } else if ("delete".equals(method)) {
            delete(request,response);
        } else if ("list".equals(method)) {
            list(request,response);

        } else if ("viewUpdate".equals(method)) {
            viewUpdate(request,response);
        } else if ("update".equals(method)) {
            update(request,response);
        } else if("search".equals(method)) {
            search(request,response);
        }
    }

    private void search(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
        String keyword = request.getParameter("keyword");
        try {
            List<FoodType> list = service.search(keyword);
            request.setAttribute("list",list);
            list(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        FoodType foodType = new FoodType();
        try {
            BeanUtils.populate(foodType,request.getParameterMap());
            service.update(foodType);
            list(request,response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ftn = request.getParameter("foodTypeName");
        FoodType newType = new FoodType();
        newType.setTypeName(ftn);
        try {
            service.add(newType);
            list(request,response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        try {
            service.deleteById(id);
            list(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        goTo(request, response);
    }
    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<FoodType> list = service.list();
            request.setAttribute("list",list);
            url=request.getRequestDispatcher("sys/type/foodtype_list.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            url="";
        }
        goTo(request, response);
    }
    public void viewUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            FoodType type = service.findById(id);
            request.setAttribute("type",type);
            url=request.getRequestDispatcher("/sys/type/foodtype_update.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
        goTo(request, response);
    }
    private void goTo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(url instanceof RequestDispatcher) {
            ((RequestDispatcher) url).forward(request,response);
        }else if (url instanceof String) {
            response.sendRedirect(request.getContextPath()+url);
        }
    }

}
