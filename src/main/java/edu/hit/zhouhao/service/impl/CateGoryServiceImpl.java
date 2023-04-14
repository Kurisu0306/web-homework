package edu.hit.zhouhao.service.impl;

import edu.hit.zhouhao.dao.CategoryDAO;
import edu.hit.zhouhao.dao.impl.CategoryDAOImpl;
import edu.hit.zhouhao.domain.Category;
import edu.hit.zhouhao.service.CategoryService;

import java.util.List;

public class CateGoryServiceImpl implements CategoryService {
    private CategoryDAO categoryDAO = new CategoryDAOImpl();
    @Override
    public List<Category> findAll() {
        return categoryDAO.findAll();
    }
}
