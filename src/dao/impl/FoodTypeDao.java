package dao.impl;

import dao.IFoodTypeDao;
import domain.FoodType;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.JdbcUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * FoodTypeDao Implement
 * Created by RZ on 4/19/16.
 */
public class FoodTypeDao implements IFoodTypeDao {
    @Override
    public void add(FoodType foodType) {
        String sql = "insert into foodType values(null,?)";
        try {
            JdbcUtils.getQueryRunner().update(sql, foodType.getTypeName());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    @Override
    public void update(FoodType foodType) {
        String sql="update foodType set typeName=? where id=?";
        try {
            JdbcUtils.getQueryRunner().update(sql,foodType.getTypeName(),foodType.getId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql="delete from foodType where id=?";
        try {
            JdbcUtils.getQueryRunner().update(sql,id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<FoodType> list() {
        String sql="select * from foodType";
        try{
            return JdbcUtils.getQueryRunner().query(sql,new BeanListHandler<FoodType>(FoodType.class));
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<FoodType> getAll(String foodTypeName){
        String sql="select * from foodType where typeName=?";
        try{
            return JdbcUtils.getQueryRunner().query(sql,new BeanListHandler<FoodType>(FoodType.class),foodTypeName);
        }catch(Exception e){
        e.printStackTrace();
        throw new RuntimeException(e);
        }
    }


    @Override
    public FoodType findById(int id){
        String sql="Select * from foodType where id=?";
        try {
        return JdbcUtils.getQueryRunner().query(sql,new BeanHandler<FoodType>(FoodType.class),id);
        } catch (Exception e) {
        e.printStackTrace();
        throw new RuntimeException(e);
        }
        }

    @Override
    public List<FoodType> search(String keyword) {
        String sql = "select * from foodType where typeName like ?";
        try {
            return JdbcUtils.getQueryRunner().query(sql,new BeanListHandler<FoodType>(FoodType.class),"%"+keyword+"%");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
