package com.consult_app.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Medications {
    private int medicationId;
    private String prescriptionDate;
    private String medicationName;
    private String dosage;
}
