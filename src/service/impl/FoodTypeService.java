package service.impl;

import dao.IFoodTypeDao;
import domain.FoodType;
import factory.BeanFactory;
import service.IFoodTypeService;

import java.util.List;

/**
 * Created by RZ on 4/19/16.
 */
public class FoodTypeService implements IFoodTypeService {

    private IFoodTypeDao dao = BeanFactory.getInstance("foodTypeDao",IFoodTypeDao.class);
    @Override
    public void add(FoodType foodType) {
        try {
            dao.add(foodType);
        } catch (Exception e) {
        throw new RuntimeException(e);
        }
    }

    @Override
    public void update(FoodType foodType) {
        dao.update(foodType);
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
    public List<FoodType> list() {
        try {
            return dao.list();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<FoodType> getAll(String foodTypeName) {
        try {
            return dao.getAll(foodTypeName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public FoodType findById(int id) {
        try {
            return dao.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<FoodType> search(String keyword) {
        try {
            return dao.search(keyword);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
