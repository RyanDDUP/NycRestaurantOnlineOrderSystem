package dao.impl;

import dao.IFoodDao;
import domain.Food;
import domain.PageBean;
import domain.SearchCondition;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import utils.JdbcUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by RZ on 4/20/16.
 */
public class FoodDao implements IFoodDao {
    public void add(Food food) {
        String sql = "insert into food values(null,?,?,?,?,?,?,?)";
        try {
            JdbcUtils.getQueryRunner().update(sql, food.getFoodName(),food.getFoodNameCN(),food.getFoodType_id(),food.getPrice(),
                    food.getMprice(),food.getDescription(),food.getImg());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    public void update(Food food) {
        String sql="update food set foodName=?,foodNameCN=?, foodType_id=?,price=?,mprice=?,description=?,img=? where id=?";
        try {
            JdbcUtils.getQueryRunner().update(sql,food.getFoodName(),food.getFoodNameCN(),food.getFoodType_id(),food.getPrice(),
                    food.getMprice(),food.getDescription(),food.getImg(),food.getId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void deleteById(int id) {
        String sql="delete from food where id=?";
        try {
            JdbcUtils.getQueryRunner().update(sql,id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<Food> list() {
        String sql="select * from food";
        try{
            return JdbcUtils.getQueryRunner().query(sql,new BeanListHandler<Food>(Food.class));
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Food findById(int id){
        String sql="Select * from food where id=?";
        try {
            return JdbcUtils.getQueryRunner().query(sql,new BeanHandler<Food>(Food.class),id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<Food> search(String keyword) {
        String sql = "select * from food where foodName like ? or foodNameCN like ?";
        try {
            return JdbcUtils.getQueryRunner().query(sql,new BeanListHandler<Food>(Food.class),"%"+keyword+"%","%"+keyword+"%");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<Food> listByPage(int currPageNum, int pageCount) {
        String sql = "select * from food limit ?,?";
        try {
            return JdbcUtils.getQueryRunner().query(sql,new BeanListHandler<Food>(Food.class),(currPageNum-1)*pageCount,pageCount);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }


    }

    public int findAllCount() {
        String sql = "select count(*) from food";
        try {
            long c = (long)JdbcUtils.getQueryRunner().query(sql,new ScalarHandler());
            return (int)c;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * frond end parts
     * @param pageBean
     */
    public void listByPageBean(PageBean pageBean) {
        int pageCount = pageBean.getPageCount();
        int currPageNum = pageBean.getCurrPageNum();
        StringBuffer sb = new StringBuffer();
        sb.append(" select *");
        sb.append("     from food");
        sb.append(" where 1=1");
        SearchCondition condition  = pageBean.getCondition();
        List<Object> list = new ArrayList<>();
        int foodTypeId = condition.getFoodTypeId();
        if(foodTypeId!=0) {
            sb.append(" and foodType_id=?");
            list.add(condition.getFoodTypeId());
        }
        else if(condition.getFoodName()!=null) {
            sb.append(" and foodName like ? or foodNameCN like ?");
            list.add("%"+condition.getFoodName()+"%");
            list.add("%"+condition.getFoodName()+"%");
        }
        sb.append(" limit ?,?");
            list.add((currPageNum-1)*pageCount);
            list.add(pageCount);
        try {
            List<Food> foods = JdbcUtils.getQueryRunner().query(sb.toString(),new BeanListHandler<Food>(Food.class),list.toArray());
            pageBean.setPageContent(foods);
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }


    }

    @Override
    public int countByPageBean(PageBean pageBean) {

        int pageCount = pageBean.getPageCount();
        int currPageNum = pageBean.getCurrPageNum();
        StringBuffer sb = new StringBuffer();
        sb.append(" select count(*)");
        sb.append("     from food");
        sb.append(" where 1=1");
        SearchCondition condition  = pageBean.getCondition();
        List<Object> list = new ArrayList<>();
        int foodTypeId = condition.getFoodTypeId();
        if(foodTypeId!=0) {
            sb.append(" and foodType_id=?");
            list.add(condition.getFoodTypeId());
        }
        else if(condition.getFoodName()!=null) {
            sb.append(" and foodName like ? or foodNameCN like ?");
            list.add("%"+condition.getFoodName()+"%");
            list.add("%"+condition.getFoodName()+"%");
        }
        try {
            long c = (Long) JdbcUtils.getQueryRunner().query(sb.toString(),new ScalarHandler(),list.toArray());
            return (int)c;
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }

    }
}
