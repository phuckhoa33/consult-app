package com.consult_app.demo.services.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.consult_app.demo.mappers.AppointmentsMapper;
import com.consult_app.demo.services.AppointmentService;
import com.consult_app.demo.models.Appointments;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentsMapper appoinmentsMapper;

    @Override
    public List<Appointments> getAllAppointments() {
        return appoinmentsMapper.getAllAppointments();
    }

    @Override
    public Appointments getAppointmentById(Long id) {
        return appoinmentsMapper.getAppointmentById(id);
    }

	@Override
	public List<Appointments> getWaitingAppointments() {
		// TODO Auto-generated method stub
		return appoinmentsMapper.getWaitingAppointments();
	}

	@Override
	public List<Appointments> getCancelAppointments() {
		// TODO Auto-generated method stub
		return appoinmentsMapper.getCancelAppointments();
	}

	@Override
	public List<Appointments> getConfirmedAppointments() {
		// TODO Auto-generated method stub
		return appoinmentsMapper.getConfirmedAppointments();
	}

	@Override
	public void updateStatus(String status, int appointmentId) {
		// TODO Auto-generated method stub
		 appoinmentsMapper.updateStatus(appointmentId, status);;
	}

	@Override
	public void updateStatuscancel(String status, int appointmentId, String reason) {
		// TODO Auto-generated method stub
		appoinmentsMapper.updateStatuscancel(appointmentId, status, reason);
		
	}

}
