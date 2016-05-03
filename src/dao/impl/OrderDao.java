package dao.impl;

import dao.IOrderDao;
import domain.Food;
import domain.Orders;
import domain.PageBean;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import utils.JdbcUtils;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by RZ on 4/23/16.
 */
public class OrderDao implements IOrderDao{

    @Override
    public int putOrder(Map<Integer, Integer> cart, Map<Integer, Food> cartFood, int tableId) {
        double money = 0;
        double vmoney = 0;
        for(int id:cart.keySet()) {
            int num =cart.get(id);
            double price = cartFood.get(id).getPrice();
            double mprice = cartFood.get(id).getMprice();
            money +=price;
            vmoney +=mprice;
        }
        //存入order,status为0//未付款
        String sql = "insert into orders values(null,?,?,?,?,0)";
        QueryRunner runner = JdbcUtils.getQueryRunner();
        int orderId;
        try {
            runner.update(sql,tableId,new Timestamp(new Date().getTime()),money,vmoney);



        //存入orderDetails
        String sqlOrderId = "select max(id) from orders";

            orderId = (int)runner.query(sqlOrderId,new ScalarHandler());
            new OrderDetailsDao().putOrderDetails(cart,cartFood,orderId);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }





        //tableStatus为1,不可预定;
        new DinnerTableDao().reserve(tableId);
        return orderId;
    }

    @Override
    public int payByOrderId(int orderId) {
        String sql = "update orders set orderStatus=1 where id=?";
        String sqlTableId = "select table_id from orders where id=?";
        try {
            JdbcUtils.getQueryRunner().update(sql,orderId);
            return (int)JdbcUtils.getQueryRunner().query(sqlTableId,new ArrayHandler(),orderId)[0];
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Orders> list(PageBean pb) {
        int cur = pb.getCurrPageNum();
        int pageCount = pb.getPageCount();
        String sql="select * from orders order by id desc limit ?,?";
        try {
            return JdbcUtils.getQueryRunner().query(sql,new BeanListHandler<Orders>(Orders.class),(cur-1)*pageCount,pageCount);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public int totalCount() {
        String sql = "select count(*) from orders";
        try {
            long num = (long)JdbcUtils.getQueryRunner().query(sql,new ScalarHandler());
            return (int)num;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int check(int orderId) {
        String sql = "update orders set orderStatus=2 where id=?";//2checked 1 called 0 paid
        String sqlTableId = "select table_id from orders where id=?";
        try {
            JdbcUtils.getQueryRunner().update(sql,orderId);
            return (int)JdbcUtils.getQueryRunner().query(sqlTableId,new ArrayHandler(),orderId)[0];
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }
}
