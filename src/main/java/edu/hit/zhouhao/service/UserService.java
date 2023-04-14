package edu.hit.zhouhao.service;

import edu.hit.zhouhao.domain.User;

public interface UserService {
    boolean registUser(User user);
    User login(User user);
    boolean checkStatus(User user);
}
