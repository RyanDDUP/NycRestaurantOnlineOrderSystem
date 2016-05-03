package dao;

import domain.Food;
import domain.OrderDetail;

import java.util.List;
import java.util.Map;

/**
 * Created by RZ on 4/23/16.
 */
public interface IOrderDetailsDao {

    List<OrderDetail> listByOrderId(int orderId);

    void putOrderDetails(Map<Integer,Integer> cart, Map<Integer, Food> cartFood, int orderId);

    List<OrderDetail> findDetailsByOrderId(int orderId);
}
