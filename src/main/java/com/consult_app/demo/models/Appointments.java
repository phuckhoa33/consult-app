package com.consult_app.demo.models;

import com.consult_app.demo.enums.AppointmentStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Appointments {
    private int appointmentId;
    private int userId;
    private int doctorId;
    private String appointmentDate;
    private String appointmentTime;
    private AppointmentStatus status;
    private int priorityIndex;
    private String followUpNotes;
    private String createdAt;
    private String updatedAt;
}
