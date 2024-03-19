package com.consult_app.demo.services;

import java.util.List;

import com.consult_app.demo.forms.DoctorForm;
import com.consult_app.demo.models.Appointments;

public interface AppointmentService {

	List<Appointments> getAllAppointments();

	Appointments getAppointmentById(Long id);
	List<Appointments> getWaitingAppointments();
	List<Appointments> getCancelAppointments();
	List<Appointments> getConfirmedAppointments();
	void updateStatus(String  status, int  appointmentId);
	void updateStatuscancel(String  status, int  appointmentId,String reason);
}
