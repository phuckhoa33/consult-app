package com.consult_app.demo.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import com.consult_app.demo.models.Appointments;
import com.consult_app.demo.models.Doctor;
@Mapper
public interface AppointmentsMapper {



    List<Appointments> getAllAppointments();

	Appointments getAppointmentById(Long id);
	List<Appointments> getWaitingAppointments();
	List<Appointments> getCancelAppointments();
	List<Appointments> getConfirmedAppointments();
	  void updateStatus(@Param("appointmentId") int appointmentId, String status);
	  void updateStatuscancel(@Param("appointmentId") int appointmentId, String status,String reason);
}

