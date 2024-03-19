package com.consult_app.demo.services.Impl;

import java.nio.CharBuffer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.consult_app.demo.mappers.UserMapper;
import com.consult_app.demo.models.User;
import com.consult_app.demo.services.CommonService;
import com.consult_app.demo.services.UserService;

import jakarta.validation.Valid;

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
    	System.err.println(userMapper.getUserByEmail(email));
        return userMapper.getUserByEmail(email);
    }

    @Override
    public User getUserByUsername(String username) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUserByUsername'");
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
        User oldUserByUsername = userMapper.getUserByUserName(user.getUsername().trim());
        if (oldUserByEmail != null || oldUserByUsername != null) {
            return false;
        }

        return true;
    }

	@Override
	public User getUserById(Long userId) {
		// TODO Auto-generated method stub
		return userMapper.getUserById(userId);
	}

	@Override
	public boolean isUserWithEmailExists(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateUser(Long userId,  User user) {
try {
	Date now=new Date();
	String hashPassword = passwordEncoder.encode(CharBuffer.wrap(user.getPassword().trim()));

	user.setUserId(userId);
	user.setPassword(hashPassword);
	user.setUpdatedAt(now);
	System.err.println(user);
	userMapper.updateUser(user);
	return true;
} catch (Exception e) {
	// TODO: handle exception
	return false;
}
    	
   
	}

	@Override
	public String updateUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
