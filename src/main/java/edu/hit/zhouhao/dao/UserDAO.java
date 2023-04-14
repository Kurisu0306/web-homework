package edu.hit.zhouhao.dao;

import edu.hit.zhouhao.domain.User;

public interface UserDAO {
    User findUserByUserName(String username);
    User findUserByUsernameAndPassword(String username,String password);
    void save(User user);
    boolean checkStatus(String username,String password);
}
