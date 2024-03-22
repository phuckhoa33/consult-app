package com.consult_app.demo.services.Impl;

import java.nio.CharBuffer;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.consult_app.demo.mappers.UserMapper;
import com.consult_app.demo.models.User;
import com.consult_app.demo.services.CommonService;
import com.consult_app.demo.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    CommonService commonService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User createUser(User user) {
        Long userId = commonService.createRandomId(10);

        Date createdAt = new Date();

        String hashPassword = passwordEncoder.encode(CharBuffer.wrap(user.getPassword()));

        user.setUserId(userId);
        user.setCreatedAt(createdAt);
        user.setPassword(hashPassword);
        userMapper.createUser(user);

        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        return userMapper.getUserByEmail(email);
    }

    @Override
    public User getUserByUsername(String username) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUserByUsername'");
    }

    @Override
    public String updateUser(User user) {
        Date updatedAt = new Date();
        user.setUpdatedAt(updatedAt);

        return "Update is successfully";
    }

    @Override
    public String deleteSoftUser(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteSoftUser'");
    }

    @Override
    public String activateUser(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'activateUser'");
    }

    @Override
    public boolean checkExistUser(User user) {
        User oldUserByEmail = userMapper.getUserByEmail(user.getEmail());
        User oldUserByUsername = userMapper.getUserByUserName(user.getUsername());
        if (oldUserByEmail != null || oldUserByUsername != null) {
            return false;
        }

        return true;
    }

    @Override
    public User getUserByUserId(String userId) {
        // TODO Auto-generated method stub
        return userMapper.getUserByUserId(Integer.parseInt(userId));
    }

}
