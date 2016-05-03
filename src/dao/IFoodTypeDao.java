package dao;

import domain.FoodType;

import java.util.List;

/**
 * Created by RZ on 4/19/16.
 * 菜系模块,接口设计
 */
public interface IFoodTypeDao {
    /**
     * save new Type
     */

    void add(FoodType foodType);

    //update
    void update(FoodType foodType);
    /**
     * deleteById
     */

    void deleteById(int id);

    /**
     * list all
     * @return List<FoodType>
     */
    List<FoodType> list();

    /**
     * searchByTypeName
     * @param
     * @return
     */

    List<FoodType> getAll(String foodTypeName);

    /**
     * findById
     */
    FoodType findById(int id);
    List<FoodType> search(String keyword);
}

