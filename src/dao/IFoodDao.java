package dao;

import domain.Food;
import domain.PageBean;

import java.util.List;

/**
 * Created by RZ on 4/20/16.
 */
public interface IFoodDao {
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

    List<Food> listByPage(int currPageNum, int pageCount);

    int findAllCount();


    /**
     * frond end query parts
     */

    void listByPageBean(PageBean pageBean);
    int countByPageBean(PageBean pageBean);
}
