package dao.impl;

import dao.IOrderDetailsDao;
import domain.Food;
import domain.OrderDetail;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.JdbcUtils;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by RZ on 4/23/16.
 */
public class OrderDetailsDao implements IOrderDetailsDao {
    @Override
    public List<OrderDetail> listByOrderId(int orderId) {
        try {
            return JdbcUtils.getQueryRunner().query("select * from orderDetail where orderId=?"
                    ,new BeanListHandler<OrderDetail>(OrderDetail.class),orderId);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void putOrderDetails(Map<Integer, Integer> cart, Map<Integer, Food> cartFood, int orderId) {
        QueryRunner runner = JdbcUtils.getQueryRunner();
        for(int i:cart.keySet()) {

            String sql="insert into orderDetail values(?,?,?)";
            try {
                runner.update(sql,orderId,i,cart.get(i));
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

    }

    public List<OrderDetail> findDetailsByOrderId(int orderId){
        String sql = "select * from orderDetail where orderId=?";
        try {
            return JdbcUtils.getQueryRunner().query(sql,new BeanListHandler<OrderDetail>(OrderDetail.class),orderId);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }


    }
}
