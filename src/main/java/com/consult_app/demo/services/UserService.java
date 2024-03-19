package com.consult_app.demo.services;

import java.util.List;

import com.consult_app.demo.models.User;

import jakarta.validation.Valid;

public interface UserService {
    User createUser(User user);

    boolean checkExistUser(User user);

    User getUserByEmail(String email);

    User getUserByUsername(String username);

    String updateUser(User user);

    String deleteSoftUser(User user);

    String activateUser(User user);

	User getUserById(Long userId);

	boolean isUserWithEmailExists(String email);

	boolean updateUser(Long userId, @Valid User user);

//	void updateUser(Long userId, User user);


}
