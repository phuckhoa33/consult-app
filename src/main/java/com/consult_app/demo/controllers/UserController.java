package com.consult_app.demo.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.consult_app.demo.models.User;
import com.consult_app.demo.services.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("user")
public class UserController {
	 @Autowired
	   UserService userService;
	 // Trang chi tiết người dùng
    @GetMapping("/{userId}")
    public String showUserDetail(@PathVariable String userId) {
        return "core/profile"; // Đường dẫn tới trang hiển thị thông tin người dùng
    }

    // Trang chỉnh sửa thông tin người dùng
    @GetMapping("/create")
	public String showCreateDoctorForm(Model model) {
	    User userform = new User();
	    model.addAttribute("user", userform);
	    return "create_user";
	}

	@PostMapping("/create")
	public String createDoctor(
	        @ModelAttribute("user") @Valid User user,
	        @ModelAttribute("dateOfBirths") @Valid String date ,
	        BindingResult bindingResult,
	        Model model) {
		  System.err.println(date);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//	    if (bindingResult.hasErrors()) {
//	        return "create_user";
//	    }
	   
	    	if(userService.getUserByEmail(user.getEmail())==null) {
	    		try {
					user.setDateOfBirth(dateFormat.parse(date));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        User savedUser = userService.createUser(user);
		      
	    	}else {
	    		  model.addAttribute("message", "Email đã tồn tại");
		            return "create_user";
	    	}
	    	model.addAttribute("message", "Thành công");
            return "create_user";
	}

    // Trang cập nhật thông tin người dùng
    @GetMapping("/update/{userId}")
    public String showUpdateUserForm(@PathVariable Long userId, Model model ) {
        User user=new User();
        user=userService.getUserById(userId);
        
        if(user !=null) {
        	model.addAttribute("user", user);
        	}else {
        		model.addAttribute("message", "User not found");
        	}
    	return "update_user"; 
    }
    
    @PostMapping("/update/{userId}")
    public String updateUser(
            @PathVariable Long userId,
            @ModelAttribute("user") @Valid User user,
            @ModelAttribute("dateOfBirths") @Valid String date ,
//            BindingResult bindingResult,
            Model model) {
    	
//        if (bindingResult.hasErrors()) {
//            return "update_user";
//        }
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
				user.setDateOfBirth(dateFormat.parse(date));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        boolean result = userService.updateUser(userId, user);

        if (result) {
            return "redirect:/user/user_detail/" + userId;
        } else {
            model.addAttribute("message", "Error occurred while updating the user.");
            return "update_user";
        }
    }
    
    //hiên thị
	@GetMapping("/user_detail/{userId}")
	public String showDoctorDetail(@PathVariable("userId") Long userId, Model model) {
	    User user = userService.getUserById(userId);
	    
	    if (user == null) {
	        model.addAttribute("message", "Không tìm thấy thông tin người dùng.");
	        return "error_page"; // Trang thông báo lỗi
	    }
	    
	    model.addAttribute("user", user);
	    return "user_detail";
	}

}