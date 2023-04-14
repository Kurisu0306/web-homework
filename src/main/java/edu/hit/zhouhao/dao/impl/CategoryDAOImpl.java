package edu.hit.zhouhao.dao.impl;

import edu.hit.zhouhao.dao.CategoryDAO;
import edu.hit.zhouhao.domain.Category;
import edu.hit.zhouhao.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Category> findAll() {
        String sql = "select * from tab_category";
        try {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Category>(Category.class));
        } catch (DataAccessException | NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }
}
