package web;

import domain.DinnerTable;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;
import java.io.IOException;
import java.net.URI;
import java.util.List;

/**
 * Created by RZ on 4/20/16.
 */
public class DinnerTableServlet extends BaseServlet {

    private Object search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        List<DinnerTable> list = dinnerTableService.search(keyword);
        request.setAttribute("list", list);
        return request.getRequestDispatcher("/sys/table/table_list.jsp");
    }

    private Object reserve(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DinnerTable table = new DinnerTable();
        int id = Integer.parseInt(request.getParameter("id"));
        dinnerTableService.reserve(id);
        return request.getContextPath()+"/table?method=list";
    }


    public Object add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ftn = request.getParameter("tableName");
        DinnerTable newTable = new DinnerTable();
        newTable.setTableName(ftn);
        dinnerTableService.add(newTable);
        return list(request,response);

    }

    public Object delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        dinnerTableService.deleteById(id);

        return list(request,response);


    }

    public Object list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<DinnerTable> list = dinnerTableService.list();
        request.setAttribute("list", list);
        return request.getRequestDispatcher("sys/table/table_list.jsp");

    }

    public Object viewUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        DinnerTable table = dinnerTableService.findById(id);
        request.setAttribute("table", table);
        return request.getContextPath()+"/sys/type/foodtype_update.jsp";

    }


}