package dao.impl;

import dao.IDinnerTableDao;
import domain.DinnerTable;
import domain.TableStatus;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.JdbcUtils;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by RZ on 4/20/16.
 */
public class DinnerTableDao implements IDinnerTableDao{
    @Override
    public void add(DinnerTable dinnerTable) {
        String sql = "insert into dinnerTable values(null,?,0,null)";
        try {
            JdbcUtils.getQueryRunner().update(sql, dinnerTable.getTableName());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void reserve(int id) {
        String find = "select tableStatus from dinnerTable where id=?";
        String sql = "update dinnerTable set tableStatus=?, orderDate=? where id=?";
        try {
            int status = (int)JdbcUtils.getQueryRunner().query(find,new ArrayHandler(),id)[0];
            if(status==0){
                JdbcUtils.getQueryRunner().update(sql,1,new Timestamp(new Date().getTime()),id);
            } else {
                JdbcUtils.getQueryRunner().update(sql,0,null,id);

            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "delete from dinnerTable where id=?";
        try {
            JdbcUtils.getQueryRunner().update(sql, id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<DinnerTable> list() {
        String sql = "select * from dinnerTable";
        try {
            return JdbcUtils.getQueryRunner().query(sql,new BeanListHandler<DinnerTable>(DinnerTable.class));
        } catch (Exception e) {
            e.printStackTrace();
           throw new RuntimeException(e);
        }
    }


    @Override
    public DinnerTable findById(int id) {
        String sql = "select * from dinnerTable where id=?";
        try {
            return JdbcUtils.getQueryRunner().query(sql,new BeanHandler<DinnerTable>(DinnerTable.class),id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<DinnerTable> search(String keyword) {
        String sql = "select * from dinnerTable where tableName like ?";
        try {
            return JdbcUtils.getQueryRunner().query(sql,new BeanListHandler<DinnerTable>(DinnerTable.class),"%"+keyword+"%");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<DinnerTable> findByStatus(TableStatus status){
        String sql = "select * from dinnerTable where tableStatus=?";
        try {
            return JdbcUtils.getQueryRunner().query(sql,new BeanListHandler<DinnerTable>(DinnerTable.class),status.ordinal());

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
