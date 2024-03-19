package com.consult_app.demo.models;

import org.springframework.data.relational.core.mapping.Column;

import com.consult_app.demo.enums.AppointmentStatus;

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
public class Appointments {
    private int appointmentId;
    private int userId;
    private int doctorId;
    private String appointmentDate;
    private String appointmentTime;
    private String status;
    private int priorityIndex;
    private String followUpNotes ;
    private String createdAt;
    private String updatedAt;
}
