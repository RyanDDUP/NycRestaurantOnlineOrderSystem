package service;

import domain.Food;
import domain.PageBean;

import java.util.List;

/**
 * Created by RZ on 4/20/16.
 */
public interface IFoodService {
    /**
     * save new Type
     */

    void add(Food food);

    //update
    void update(Food food);
    /**
     * deleteById
     */

    void deleteById(int id);

    /**
     * list all
     * @return List<Food>
     */
    List<Food> list();



    /**
     * findById
     */
    Food findById(int id);


    /**
     * searchByTypeName
     * @param
     * @return
     */
    List<Food> search(String keyword);

    PageBean listByPage(int currPageNum, int pageCount);


    /**
     * frond end parts
     * @param pageBean
     */
    public void listByPageBean(PageBean pageBean) ;

}
