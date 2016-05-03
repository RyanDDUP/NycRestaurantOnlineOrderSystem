package service.impl;

import dao.IFoodDao;
import domain.Food;
import factory.BeanFactory;
import service.IFoodService;
import domain.PageBean;

import java.util.List;

/**
 * Created by RZ on 4/20/16.
 */
public class FoodService implements IFoodService {
    private IFoodDao dao = BeanFactory.getInstance("foodDao",IFoodDao.class);

    @Override
    public void add(Food food) {
        try {
            dao.add(food);
        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Food food) {
        try {
            dao.update(food);
        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }


    @Override
    public void deleteById(int id) {
        try {
            dao.deleteById(id);
        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Food> list() {
        try {
           return dao.list();
        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }

    @Override
    public Food findById(int id) {
        try {
            return dao.findById(id);
        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Food> search(String keyword) {
        try {
            return dao.search(keyword);
        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }

    @Override
    public PageBean listByPage(int currPageNum, int pageCount) {
        try {
            List<Food> pageContent = dao.listByPage(currPageNum,pageCount);
            PageBean pb = new PageBean();
            pb.setCurrPageNum(currPageNum);
            pb.setPageCount(pageCount);
            int totalCount =dao.findAllCount();
            pb.setTotalCount(totalCount);
            pb.setTotalPage((int) Math.ceil(totalCount*1.0/pageCount));
            pb.setPageContent(pageContent);
            return pb;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * frond end parts
     * @param pageBean
     */
    public void listByPageBean(PageBean pageBean) {
        pageBean.setTotalCount(dao.countByPageBean(pageBean));
        pageBean.setTotalPage((int)Math.ceil(pageBean.getTotalCount()*1.0/pageBean.getPageCount()));
        dao.listByPageBean(pageBean);
    }

}
