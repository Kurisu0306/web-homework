package edu.hit.zhouhao.service.impl;

import edu.hit.zhouhao.dao.UserDAO;
import edu.hit.zhouhao.dao.impl.UserDAOImpl;
import edu.hit.zhouhao.domain.User;
import edu.hit.zhouhao.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDAO userDAO = new UserDAOImpl();

    @Override
    public boolean registUser(User user) {
        if(userDAO.findUserByUserName(user.getUsername())!=null)
        {
            return false;
        }else {
            userDAO.save(user);
            return true;
        }
    }

    @Override
    public User login(User user) {
        return userDAO.findUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean checkStatus(User user) {
        return userDAO.checkStatus(user.getUsername(),user.getPassword());
    }
}
