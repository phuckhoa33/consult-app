package com.consult_app.demo.services.Impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.javassist.compiler.SyntaxError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.consult_app.demo.enums.DoctorStatus;
import com.consult_app.demo.forms.DoctorForm;
import com.consult_app.demo.forms.SignupForm;
import com.consult_app.demo.mappers.DoctorMapper;
import com.consult_app.demo.models.Doctor;
import com.consult_app.demo.models.User;
import com.consult_app.demo.services.AuthService;
import com.consult_app.demo.services.CommonService;
import com.consult_app.demo.services.DoctorService;
import com.consult_app.demo.services.UserService;

import jakarta.validation.Valid;

@Service
public class DoctorServiceImpl implements DoctorService {

   @Autowired
   DoctorMapper doctorMapper;

   @Autowired
   PasswordEncoder passwordEncoder;
   
   @Autowired
   UserService userService;
   
   @Autowired
   AuthService authService;
   
   @Autowired
   CommonService commonService;
   
    @Override
    public List<Doctor> getAllDoctors() {
        return doctorMapper.getAllDoctors();
    }
    
//    @Override
//    public boolean checkExistUser(DoctorForm doctorForm) {
//    	DoctorForm oldUserByEmail = doctorMapper.getDoctorByEmail(doctorForm.getEmail());
//    	DoctorForm oldUserByUsername = doctorMapper.getDoctorByUsername(doctorForm.getUsername());
//        if (oldUserByEmail != null || oldUserByUsername != null) {
//            return false;
//        }
//
//        return true;
//    }

    @Override
    public Doctor createDoctor(DoctorForm doctorForm) {
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // Tạo một SignupForm mới từ DoctorForm
        User newSignupForm;
		try {
			newSignupForm = User.builder()
			        .firstname(doctorForm.getFirstname())
			        .lastname(doctorForm.getLastname())
			        .username(doctorForm.getUsername())
			        .password(doctorForm.getPassword())
			        .email(doctorForm.getEmail())
			        .address(doctorForm.getAddress())
			        .avatar(doctorForm.getAvatar())
			        .ban(doctorForm.getBan())
			        .phoneNumber(doctorForm.getPhoneNumber())
			        .dateOfBirth(dateFormat.parse(doctorForm.getDateOfBirth()))
			        .build(); User createdUser = userService.getUserByEmail(newSignupForm.getEmail());
			        // Gọi phương thức đăng ký của AuthService và kiểm tra kết quả
			        if(createdUser==null) {
			          User result = userService.createUser(newSignupForm);
			   
			              try {
			            	// Tạo một ID ngẫu nhiên cho bác sĩ
			                  Long doctorId = commonService.createRandomId(10);

			                  // Tạo một đối tượng Doctor mới từ DoctorForm và thông tin đã tạo
			                  Doctor newDoctor = Doctor.builder()
			                          .doctorId(doctorId)
			                          .userId(result.getUserId())
			                          .experience(doctorForm.getExperience())
			                          .bioId(88888)
			                          .education(doctorForm.getEducation())
			                          .status("Active")
			                          .rating(doctorForm.getRating())
			                          .build();

			                  // Gọi phương thức insertDoctor của DoctorMapper để lưu thông tin bác sĩ vào cơ sở dữ liệu
			                  doctorMapper.createDoctor(newDoctor);

			                  // Trả về bác sĩ đã được tạo thành công
			                  return newDoctor;
						} catch (Exception e) {
					
							  return null;
					
						}
			          } else {
			        	  
			              // Xử lý khi không tìm thấy người dùng sau khi đăng ký
			        	  return null;
			          }
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return null;
		}
     }
    
    //update
    public boolean updateDoctor(Long doctorId, @Valid DoctorForm doctorForm) {
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	Date now=new Date();
    	System.out.println(doctorForm.getUserId());
    	User user=userService.getUserById(doctorForm.getUserId());
    
	Doctor updateDoctor=new Doctor();
	updateDoctor.setUserId(doctorForm.getUserId());
	updateDoctor.setBioId(doctorForm.getBioId());
	updateDoctor.setDoctorId(doctorId);
	updateDoctor.setEducation(doctorForm.getEducation());
	updateDoctor.setExperience(doctorForm.getExperience());
	updateDoctor.setRating(doctorForm.getRating());
	updateDoctor.setServicesOffered(doctorForm.getServicesOffered());
	updateDoctor.setStatus("null");
	updateDoctor.setRating(doctorForm.getRating());
	doctorMapper.updateDoctor(updateDoctor);
	User updateUser= new User();
	updateUser.setUserId(doctorForm.getUserId());
	updateUser.setEmail(doctorForm.getEmail());
	updateUser.setUsername(doctorForm.getUsername());
	updateUser.setPassword(doctorForm.getPassword());
	updateUser.setFirstname(doctorForm.getFirstname().trim());
	updateUser.setLastname(doctorForm.getLastname().trim());
	updateUser.setAvatar(doctorForm.getAvatar());
	updateUser.setPhoneNumber(doctorForm.getPhoneNumber());
	try {
		updateUser.setDateOfBirth(dateFormat.parse(doctorForm.getDateOfBirth()));
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	updateUser.setGender(doctorForm.getGender());
	updateUser.setBan(doctorForm.getBan());
	updateUser.setAddress(doctorForm.getAddress());
	updateUser.setUpdatedAt(now);
	updateUser.setCreatedAt(user.getCreatedAt());
	userService.updateUser(doctorForm.getUserId(),updateUser);
return true;
    }
    

	 @Override
	    public Doctor getDoctorById(Long doctorId) {
	        return doctorMapper.getDoctorById(doctorId);
	    }

	@Override
	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkExistUser(DoctorForm doctorForm) {
		// TODO Auto-generated method stub
		return false;
	}

}
