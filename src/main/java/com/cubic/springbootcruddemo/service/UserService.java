package com.cubic.springbootcruddemo.service;

import com.cubic.springbootcruddemo.model.User;

import java.util.List;

public interface UserService {

    User saveUserInfo(User user);
    User updateUserInfo(int id,User user);
    String deleteUserInfo(int id);
    User getUserById(int id);
    List<User> findAllUser();

}
