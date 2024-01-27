package com.consult_app.demo.services;

import com.consult_app.demo.models.User;

public interface UserService {
    User createUser(User user);

    boolean checkExistUser(User user);

    User getUserByEmail(String email);

    User getUserByUsername(String username);

    String updateUser(User user);

    String deleteSoftUser(User user);

    String activateUser(User user);
}
