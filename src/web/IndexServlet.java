package web;

import domain.DinnerTable;
import domain.TableStatus;
import factory.BeanFactory;
import service.IDinnerTableService;
import utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by RZ on 4/21/16.
 */
public class IndexServlet extends HttpServlet {
    private IDinnerTableService service = BeanFactory.getInstance("service",IDinnerTableService.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        if(method==null) {
            method="available";
        }

        if("available".equals(method)) {
            available(request,response);
        }
    }

    private void available (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Object uri = null;
        try {
            List<DinnerTable> list = service.findByStatus(TableStatus.Availabe);
            request.setAttribute("available",list);
            uri = request.getRequestDispatcher("/app/index.jsp");
        } catch (Exception e) {
            uri="error/error.jsp";
            e.printStackTrace();
        }
        WebUtils.goTo(request,response,uri);
    }
}
