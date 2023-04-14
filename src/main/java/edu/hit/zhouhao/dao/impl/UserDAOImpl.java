package edu.hit.zhouhao.dao.impl;

import edu.hit.zhouhao.dao.UserDAO;
import edu.hit.zhouhao.domain.User;
import edu.hit.zhouhao.util.JDBCUtils;
import edu.hit.zhouhao.util.UuidUtil;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDAOImpl implements UserDAO {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public User findUserByUserName(String username) {
        String sql = "select * from tab_user where username = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
        } catch (DataAccessException e) {
            return null;
        }

    }

    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        String sql = "select * from tab_user where username = ? and password = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username, password);
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public void save(User user) {
        String sql = "INSERT INTO tab_user(username,password,name,birthday,sex,telephone,email,status,code) VALUES(?,?,?,?,?,?,?,1,?);";
        String username = user.getUsername();
        String password = user.getPassword();
        String name = user.getName();
        String birthday = user.getBirthday();
        String sex = user.getSex();
        String telephone = user.getTelephone();
        String email = user.getEmail();
        String uid = UuidUtil.getUuid();
        jdbcTemplate.update(sql, username, password, name, birthday, sex, telephone, email,uid);
    }

    @Override
    public boolean checkStatus(String username, String password) {
        String sql = "select count(*) from tab_user where username = ? and password = ? and status  = 1";
        Long aLong = jdbcTemplate.queryForObject(sql, Long.class, username, password);
        if(aLong==0){
            return false;
        }else {
            return true;
        }
    }
}
