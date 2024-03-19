package com.consult_app.demo.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import com.consult_app.demo.models.Appointments;
import com.consult_app.demo.models.User;
import com.consult_app.demo.services.AppointmentService;
import com.consult_app.demo.services.DoctorService;
import com.consult_app.demo.services.UserService;

import jakarta.validation.Valid;



@Controller
public class DashboardController {
	@Autowired
	AppointmentService appService;
	@Autowired
	UserService userService;
	@Autowired
	DoctorService doctorService;
	
    @GetMapping("admin/dashboard")
    public String showDashboard() {
        // Hiển thị trang dashboard
        return "admin/dashboard";
    }

    @GetMapping("/admin/charts-doctor")
    public String showDoctorCharts(Model model) {
        // Logic to populate model with data for doctor charts
        return "admin/charts-doctor"; 
    }

    public 	List<Map<String, Object>> reloaddatawating(int  status ) {
    	List<Appointments> totalApp=new ArrayList<>();
    	if(status==0) {
    		totalApp=appService.getWaitingAppointments();
    	}else if(status ==1) {
    		totalApp=appService.getCancelAppointments();
    	}else {
    		totalApp=appService.getConfirmedAppointments();
    	}
    	
    	List<Map<String, Object>>  allinfo=new ArrayList<>();
    	for (int i=0;i<totalApp.size();i++) {
    		Map<String,Object> data=new HashMap<>();
    		String name;
    		name=userService.getUserById((long) totalApp.get(i).getUserId()).getFirstname()+userService.getUserById((long) totalApp.get(i).getUserId()).getLastname();
    		String phone;
    		phone=userService.getUserById((long) totalApp.get(i).getUserId()).getPhoneNumber();
    		String time;
    		time=totalApp.get(i).getAppointmentTime();
    		String date;
    		date=totalApp.get(i).getAppointmentDate();
    		int index;
    		index=totalApp.get(i).getPriorityIndex();
    		String reasons;
    		reasons=totalApp.get(i).getFollowUpNotes();
    		String statuss;
    		statuss=totalApp.get(i).getStatus();
    		data.put("idapp", totalApp.get(i).getAppointmentId()) ;
    		data.put("fullname", name) ;
    		data.put("phone", phone) ;
    		data.put("date", date);
    		data.put("time", time) ;
    		data.put("index", index) ;
    		data.put("status", statuss) ;
    		data.put("reason", reasons) ;
    		allinfo.add(data);
    	}
    	
    	return allinfo;
    	
    }
    @GetMapping("/admin/inf-appointment")
    public String showAppointmentInformation(Model model) {
    	List<Map<String, Object>>  allinfo=reloaddatawating(0);
    	model.addAttribute("appointments", allinfo);
        // Logic to populate model with data for appointment information
        return "admin/inf-appointment"; 
    }

    @GetMapping("/admin/updatestatus/confirmed/{idapp}")
    public String updatestatusconfirmed(Model model,@PathVariable String idapp) {
    	appService.updateStatus("Confirmed", Integer.parseInt(idapp));
    	List<Map<String, Object>>  allinfo=reloaddatawating(0);
    
    	model.addAttribute("appointments", allinfo);
        // Logic to populate model with data for appointment information
        return "admin/inf-appointment"; 
    }
    @GetMapping("/admin/updatestatus/cancel/{idapp}")
    public String updatestatuscancel(Model model,@PathVariable String idapp) {
    	List<Map<String, Object>>  allinfo=reloaddatawating(0);
    
    	model.addAttribute("appointments", allinfo);
        // Logic to populate model with data for appointment information
        return "admin/inf-appointment"; 
    }

    @PostMapping("/admin/updatestatus/cancel/{idapp}")
    public String updatestatuscancel(Model model,@PathVariable String idapp, @ModelAttribute("reason") String reason) {
    	appService.updateStatuscancel("Cancel", Integer.parseInt(idapp),reason);
    	List<Map<String, Object>>  allinfo=reloaddatawating(0);
    
    	model.addAttribute("appointments", allinfo);
        // Logic to populate model with data for appointment information
        return "admin/inf-appointment"; 
    }


    @GetMapping("/admin/list_accept_appointment")
    public String listAcceptedAppointments(Model model) {
    
    	List<Map<String, Object>>  allinfo=reloaddatawating(2);
    
    	model.addAttribute("appointments", allinfo);
        // Logic to populate model with data for accepted appointments
        return "admin/list_accept_appointment"; 
    }

    @GetMapping("/admin/list_refuse_appointment")
    public String listRefusedAppointments(Model model) {
     	List<Map<String, Object>>  allinfo=reloaddatawating(1);
    	model.addAttribute("appointments", allinfo);
        // Logic to populate model with data for refused appointments
        return "admin/list_refuse_appointment"; 
    }

    @GetMapping("/admin/profile/{userId}")
    public String showDoctorDetail(@PathVariable("userId") Long userId, Model model) {
	    User user = userService.getUserById(userId);
	    
	    if (user == null) {
	        model.addAttribute("message", "Không tìm thấy thông tin người dùng.");
	        return "error_page"; // Trang thông báo lỗi
	    }
	    
	    model.addAttribute("user", user);
        return "admin/profile"; 
    }
    
    @GetMapping("/admin/edit-information/{userId}")
    public String showUpdateUserForm(@PathVariable Long userId, Model model ) {
        User user=new User();
        user=userService.getUserById(userId);
        
        if(user !=null) {
        	model.addAttribute("user", user);
        	}else {
        		model.addAttribute("message", "User not found");
        	}
        return "admin/edit-information"; 
        }
    
    @PostMapping("/admin/edit-information/{userId}")
    public String updateUser(
            @PathVariable Long userId,
            @ModelAttribute("user") @Valid User user,
            @ModelAttribute("dateOfBirths") @Valid String date ,
            Model model) {
    
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
				user.setDateOfBirth(dateFormat.parse(date));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
        boolean result = userService.updateUser(userId, user);
        
        if (result) {
            return "redirect:/admin/profile/" + userId;
        } else {
            model.addAttribute("message", "Error occurred while updating the user.");
            return "admin/edit-information"; 
        }
    }
    
    @GetMapping("/admin/appointment")
    public String showAppointments(Model model) {
    	return "admin/appointment";
    }
    
}

