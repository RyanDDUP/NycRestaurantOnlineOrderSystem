package web;

import domain.Food;
import domain.FoodType;
import domain.SearchCondition;
import javafx.scene.control.ContentDisplay;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import utils.FileUploadUtils;
import domain.PageBean;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import static com.sun.javafx.fxml.expression.Expression.add;

/**
 * Created by RZ on 4/20/16.
 */
public class FoodServlet extends BaseServlet {


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        List<Food> list = foodService.list();
        Set<Integer> typeNotNull = new HashSet<>();
        List<FoodType> types = new ArrayList<>();

        for(Food i:list) {
            int typeId = i.getFoodType_id();
            if(!typeNotNull.contains(typeId)) {
                typeNotNull.add(i.getFoodType_id());
                types.add(typeService.findById(i.getFoodType_id()));
            }
        }
        config.getServletContext().setAttribute("types",types);
    }



    private Object addQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            List<FoodType> types = typeService.list();
            request.setAttribute("types", types);
            return request.getRequestDispatcher("/sys/food/food_add.jsp");

    }

    private Object search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
            List<Food> list = foodService.search(keyword);
            request.setAttribute("list", list);
            return request.getRequestDispatcher("/sys/food/food_list.jsp");

    }

    private Object update(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Food food = new Food();
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setFileSizeMax(10 * 1024 * 200);
            upload.setHeaderEncoding("utf-8");
            if (upload.isMultipartContent(request)) {
                List<FileItem> list = upload.parseRequest(request);
                for (FileItem file : list) {
                    String fieldName = file.getFieldName();
                    if (file.isFormField()) {
                        String value = file.getString("utf-8");
                        BeanUtils.setProperty(food, fieldName, value);
                    } else {
                        String name = file.getName();
                        String path = request.getServletContext().getRealPath("/sys/images");
                        String realName = FileUploadUtils.getRealname(name);
                        if (realName != null && !"".equals(realName.trim())) {
                            String uuidName = FileUploadUtils.getUUIDName(realName);
                            String pathPlus = FileUploadUtils.getRandomDirs(uuidName);
                            File savePath = new File(path, pathPlus);
                            if (!savePath.exists()) {
                                savePath.mkdirs();
                            }
                            IOUtils.copy(file.getInputStream(), new FileOutputStream(new File(savePath, uuidName)));
                            file.delete();
                            BeanUtils.setProperty(food, fieldName, "/sys/images" + pathPlus + "/" + uuidName);
                        } else {
                            int id = food.getId();
                            String img = foodService.findById(id).getImg();
                            food.setImg(img);
                        }
                    }
                }

            }
        foodService.update(food);
            return list(request,response);
    }

    public Object add(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Food newFood = new Food();

            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setHeaderEncoding("utf-8");
            upload.setSizeMax(10 * 1024 * 200);
            if (upload.isMultipartContent(request)) {
                List<FileItem> list = upload.parseRequest(request);
                for (FileItem item : list) {
                    String fieldName = item.getFieldName();
                    if (item.isFormField()) {
                        String value = item.getString("utf-8");
                        BeanUtils.setProperty(newFood, fieldName, value);
                    } else {
                        String name = item.getName();
                        if (name != null && !"".equals(name.trim())) {
                            String realName = FileUploadUtils.getRealname(name);
                            String uuidName = FileUploadUtils.getUUIDName(realName);
                            String dirs = FileUploadUtils.getRandomDirs(uuidName);
                            String path = request.getServletContext().getRealPath("sys/images");
                            File file = new File(path, dirs);
                            if (!file.exists()) {
                                file.mkdirs();
                            }
                            IOUtils.copy(item.getInputStream(), new FileOutputStream(new File(file, uuidName)));
                            item.delete();
                            BeanUtils.setProperty(newFood,fieldName,"sys/images"+dirs+"/"+uuidName);
                        } else {

                        }
                    }

                }
                foodService.add(newFood);
            }
        return list(request,response);


    }

    public Object delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
            foodService.deleteById(id);
            return "/food?method=list";
    }

    public Object list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            int currPageNum=1;
            String _currPageNum = request.getParameter("currPageNum");
            if(_currPageNum!=null) {
                currPageNum=Integer.parseInt(_currPageNum);
            }
            int pageCount=10;
            String _pageCount = request.getParameter("pageCount");
            if(_pageCount!=null) {
                pageCount=Integer.parseInt(_pageCount);
            }

            PageBean pb = foodService.listByPage(currPageNum,pageCount);
            request.setAttribute("pb", pb);
            List<Food> list = pb.getPageContent();
            request.setAttribute("list",list);
            return request.getRequestDispatcher("sys/food/food_list.jsp");
    }

    public Object viewUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
            Food food = foodService.findById(id);
            List<FoodType> typeList = typeService.list();
            request.setAttribute("types", typeList);
            request.setAttribute("food", food);
            return request.getRequestDispatcher("/sys/food/food_update.jsp");
    }

    public Object menu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tableId = (String) request.getSession().getAttribute("tableId");
        if(tableId==null||"".equals(tableId.trim())) {
            tableId = request.getParameter("tableId");
            request.getSession().setAttribute("tableId",tableId);
        }

        PageBean pb = new PageBean();
        int currPage = 1;
        String _currPage = request.getParameter("currPageNum");
        if(_currPage!=null && !"".equals(_currPage.trim())) {
            currPage = Integer.parseInt(_currPage);
        }
        pb.setCurrPageNum(currPage);
        pb.setPageCount(6);

        SearchCondition condition = new SearchCondition();
        String foodName = request.getParameter("foodName");
        condition.setFoodName(foodName);
        String foodTypeId = request.getParameter("foodTypeId");
        if(foodTypeId!=null) {
            condition.setFoodTypeId(Integer.parseInt(foodTypeId));
        }
        pb.setCondition(condition);
        foodService.listByPageBean(pb);
        request.setAttribute("pageBean",pb);
        return request.getRequestDispatcher("/app/menu.jsp");

    }

    private Object detail(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int foodId = Integer.parseInt(request.getParameter("foodId"));
        Food food = foodService.findById(foodId);
        request.setAttribute("food",food);
        return request.getRequestDispatcher("/app/food_detail.jsp");
    }


}
