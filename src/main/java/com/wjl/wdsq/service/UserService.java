package com.wjl.wdsq.service;

import com.wjl.wdsq.dao.UserDAO;
import com.wjl.wdsq.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserDAO userDAO;
    public User getUser(int id){
        return userDAO.selectById(id);
    }
}
