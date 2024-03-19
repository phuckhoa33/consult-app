package com.consult_app.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.consult_app.demo.forms.DoctorForm;
import com.consult_app.demo.models.Doctor;

import com.consult_app.demo.services.DoctorService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/doctors")
public class DoctorController {
	
	@Autowired
    private DoctorService doctorService;
	
		//create
		// Hiển thị form tạo bác sĩ
		@GetMapping("/create")
		public String showCreateDoctorForm(Model model) {
		    DoctorForm doctorForm = new DoctorForm();
		    model.addAttribute("doctorForm", doctorForm);
		    return "create_doctor";
		}
	
		@PostMapping("/create")
		public String createDoctor(
		        @ModelAttribute("doctorForm") @Valid DoctorForm doctorForm,
		        BindingResult bindingResult,
		        Model model) {
	
		    if (bindingResult.hasErrors()) {
		        return "create_doctor";
		    }
		   
		        // Gọi phương thức tạo bác sĩ từ doctorService
		        Doctor savedDoctor = doctorService.createDoctor(doctorForm);
	
		        if (savedDoctor != null) {
		            return "redirect:/doctors/doctor_detail/" + savedDoctor.getDoctorId();
		        } else {
		            // Xử lý trường hợp savedDoctor là null
		            model.addAttribute("message", "Email đã tồn tại");
		            return "create_doctor";
		        }
		    
		    
		}

	
	@GetMapping("/doctor_detail/{doctorId}")
	public String showDoctorDetail(@PathVariable("doctorId") Long doctorId, Model model) {
	    // Lấy thông tin của bác sĩ từ doctorService bằng ID
	    Doctor doctor = doctorService.getDoctorById(doctorId);
	    
	    if (doctor == null) {
	        // Xử lý khi không tìm thấy bác sĩ
	        model.addAttribute("message", "Không tìm thấy thông tin bác sĩ.");
	        return "error_page"; // Trang thông báo lỗi
	    }
	    
	    // Đưa thông tin của bác sĩ vào model để hiển thị trên trang chi tiết
	    model.addAttribute("doctor", doctor);
	    return "doctor_detail"; // Trang chi tiết bác sĩ
	}

	
    @GetMapping("/update/{doctorId}")
    public String showUpdateDoctorForm(@PathVariable Long doctorId, Model model) {
    	Doctor doctor=new Doctor();
    	doctor=doctorService.getDoctorById(doctorId);
    	
        DoctorForm doctorForm = new DoctorForm();
        if (doctor != null) {
            // Đổ dữ liệu từ Doctor vào DoctorForm để hiển thị trên form
            doctorForm.setExperience(doctor.getExperience());
            doctorForm.setEducation(doctor.getEducation());
            doctorForm.setUserId(doctor.getUserId());
            model.addAttribute("doctorForm", doctorForm);
            model.addAttribute("doctorId", doctorId);
            return "update_doctor";
        } else {
            model.addAttribute("message", "Doctor not found");
            return "error";
        }
    }

    // Xử lý form cập nhật thông tin bác sĩ
    @PostMapping("/update/{doctorId}")
    public String updateDoctor(
            @PathVariable Long doctorId,
            @ModelAttribute("doctorForm") @Valid DoctorForm doctorForm,
            BindingResult bindingResult,
            Model model) {
    	
//        if (bindingResult.hasErrors()) {
//            return "update_doctor";
//        }
    	Doctor doctor=new Doctor();
    	doctor=doctorService.getDoctorById(doctorId);
    	doctorForm.setUserId(doctor.getUserId());
        boolean result = doctorService.updateDoctor(doctorId, doctorForm);

        if (result) {
        	
            return "redirect:/doctors/doctor_detail/" + doctorId;
        } else {
            model.addAttribute("message", "Error occurred while updating the doctor.");
            return "update_doctor";
            
        }
    }
  
//    //hiển thịCuộc hẹn
//    @GetMapping("appointment")
//    public String displayAppointment() {
//        return "core/appointment";
//    }
}
