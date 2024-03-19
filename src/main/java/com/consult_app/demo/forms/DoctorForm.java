package com.consult_app.demo.forms;

import com.consult_app.demo.enums.DoctorStatus;
import com.drew.lang.annotations.NotNull;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Getter
@Setter
public class DoctorForm {
	
	@NotNull
	private Long userId;
	
	@NotNull
	@NotEmpty
	private String email;
	
	@NotNull
	@NotEmpty
	private String username;
	
	@NotNull
	@NotEmpty
	private String password;
	
	@NotNull
	@NotEmpty
	private String firstname;
	
	@NotNull
	@NotEmpty
	private String lastname;
	
	@NotNull
	@NotEmpty
	private String avatar;
	
	@NotNull
	@NotEmpty
	private String phoneNumber;
	
	@NotNull
	private String dateOfBirth;
	
	@NotNull
	private Boolean gender;
	
	@NotNull
	private Boolean ban;
	
	@NotNull
	@NotEmpty
	private String address;
	
	@NotNull
	@NotEmpty
	private String experience;
	
	@NotNull
	@NotEmpty
	private String education;

	
	private int bioId;
	
	
	private String servicesOffered;
	
	private DoctorStatus status;
	
    private Double rating;
    
}
