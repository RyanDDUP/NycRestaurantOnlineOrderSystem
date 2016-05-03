package service.impl;

import dao.IDinnerTableDao;
import domain.DinnerTable;
import domain.TableStatus;
import factory.BeanFactory;
import service.IDinnerTableService;

import java.util.List;

/**
 * Created by RZ on 4/20/16.
 */
public class DinnerTableService implements IDinnerTableService {
    private IDinnerTableDao dao = BeanFactory.getInstance("dinnerTableDao", IDinnerTableDao.class);

    @Override
    public void add(DinnerTable dinnerTable) {
        try {
            dao.add(dinnerTable);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void reserve(int id) {
        try {
            dao.reserve(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(int id) {
        try {
            dao.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<DinnerTable> list() {
        try {
            return dao.list();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public DinnerTable findById(int id) {
        try {
            return dao.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<DinnerTable> search(String keyword) {
        try {
            return dao.search(keyword);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<DinnerTable> findByStatus(TableStatus status) {

        try {
            return dao.findByStatus(status);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
