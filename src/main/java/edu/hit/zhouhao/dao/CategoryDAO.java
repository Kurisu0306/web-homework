package edu.hit.zhouhao.dao;

import edu.hit.zhouhao.domain.Category;
import edu.hit.zhouhao.domain.User;

import java.util.List;

public interface CategoryDAO {
    List<Category> findAll();
}
