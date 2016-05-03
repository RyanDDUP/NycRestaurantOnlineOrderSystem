package web;

import service.IFoodService;
import service.IFoodTypeService;
import factory.BeanFactory;
import service.IDinnerTableService;
import service.IOrderService;
import utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Created by RZ on 4/22/16.
 */
public abstract class BaseServlet extends HttpServlet {
    protected IDinnerTableService dinnerTableService = BeanFactory.getInstance("service",IDinnerTableService.class);
    protected IFoodService foodService = BeanFactory.getInstance("foodService", IFoodService.class);
    protected IFoodTypeService typeService = BeanFactory.getInstance("foodTypeService", IFoodTypeService.class);
    protected IOrderService orderService = BeanFactory.getInstance("orderService",IOrderService.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object uri=null;
        String methodName = request.getParameter("method");
        int index = methodName.lastIndexOf("&");
        if (index>0) {
            methodName=methodName.substring(0,index);
        }
        Class clazz = this.getClass();
        try {
            Method method = clazz.getDeclaredMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            method.setAccessible(true);
            uri = method.invoke(this,request,response);
        } catch (Exception e) {
            e.printStackTrace();
            uri ="/error/error.jsp";
        }
        WebUtils.goTo(request,response,uri);

    }
}
