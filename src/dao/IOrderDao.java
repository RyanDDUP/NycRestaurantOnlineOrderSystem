package dao;

import domain.Food;
import domain.Orders;
import domain.PageBean;

import java.util.List;
import java.util.Map;

/**
 * Created by RZ on 4/21/16.
 */
public interface IOrderDao {
    /**
     * front end design
     * @param cart
     * @param cartFood
     * @param tableId
     */
    int putOrder(Map<Integer,Integer> cart, Map<Integer, Food> cartFood, int tableId);

    int payByOrderId(int orderId);//return with table_id

    /**
     * sever end design
     */

    List<Orders> list(PageBean pb);

    int totalCount();

    int check(int orderId);
}
