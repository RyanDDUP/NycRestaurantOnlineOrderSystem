package service;

import domain.DinnerTable;
import domain.TableStatus;

import java.util.List;

/**
 * Created by RZ on 4/20/16.
 */
public interface IDinnerTableService {
    /**
     * save new Type
     */

    void add(DinnerTable dinnerTable);

    //update
    void reserve(int id);
    /**
     * deleteById
     */

    void deleteById(int id);

    /**
     * list all
     * @return List<FoodType>
     */
    List<DinnerTable> list();

    /**
     * findById
     */
    DinnerTable findById(int id);
    /**
     * searchByTypeName
     * @param
     * @return
     */
    List<DinnerTable> search(String keyword);

    List<DinnerTable> findByStatus(TableStatus status);

}
