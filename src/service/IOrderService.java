package service;

import domain.Food;
import domain.OrderDetail;
import domain.Orders;
import domain.PageBean;

import java.util.List;
import java.util.Map;

/**
 * Created by RZ on 4/23/16.
 */
public interface IOrderService {

    /**
     * front end design
     * @param cart
     * @param cartFood
     * @param tableId
     */
    int putOrder(Map<Integer,Integer> cart, Map<Integer, Food> cartFood, int tableId);

    int payByOrderId(int orderId);//return with table_id

    /**
     * list order details by orderId
     * @param orderId
     * @return
     */
   List<OrderDetail> listByOrderId(int orderId);

    void list(PageBean pb);

    List<OrderDetail> findDetailsByOrderId(int orderId);

    int check(int orderId);
}
