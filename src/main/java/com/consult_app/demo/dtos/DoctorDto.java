package com.consult_app.demo.dtos;

import com.consult_app.demo.enums.DoctorStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DoctorDto {
	 private Long doctorId;
	    private String experience;
	    private String services_offered;
	    private int bioId;
	    private String education;
	    private DoctorStatus status;
	    private Double rating;
}
