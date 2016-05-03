package service.impl;

import dao.IOrderDao;
import dao.IOrderDetailsDao;
import domain.Food;
import domain.OrderDetail;
import domain.Orders;
import domain.PageBean;
import factory.BeanFactory;
import service.IOrderService;

import java.util.List;
import java.util.Map;

/**
 * Created by RZ on 4/23/16.
 */
public class OrderService implements IOrderService {
    private IOrderDao orderDao = BeanFactory.getInstance("orderDao",IOrderDao.class);
    private IOrderDetailsDao orderDetailsDao = BeanFactory.getInstance("orderDetailsDao",IOrderDetailsDao.class);
    public int putOrder(Map<Integer, Integer> cart, Map<Integer, Food> cartFood, int tableId) {
        return orderDao.putOrder(cart,cartFood,tableId);
    }

    public int payByOrderId(int orderId) {
        return orderDao.payByOrderId(orderId);
    }

    public List<OrderDetail> listByOrderId(int orderId) {
        return orderDetailsDao.listByOrderId(orderId);
    }

    @Override
    public void list(PageBean pb) {
        int cur = pb.getCurrPageNum();
        int pageCount = pb.getPageCount();
        int totalCount = orderDao.totalCount();
        pb.setTotalCount(totalCount);
        pb.setTotalPage();
        List<Orders> list = orderDao.list(pb);
        pb.setPageContent(list);
    }

    @Override
    public List<OrderDetail> findDetailsByOrderId(int orderId) {
        return orderDetailsDao.findDetailsByOrderId(orderId);
    }

    @Override
    public int check(int orderId) {

              return   orderDao.check(orderId);
    }
}
