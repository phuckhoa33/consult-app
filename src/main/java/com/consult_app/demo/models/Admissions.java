package com.consult_app.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Admissions {
    private int admissionId;
    private String admissionDate;
    private String dischargeDate;
    private String treatingLocation;
    private String testsAndResults;
    private String treatmentPlan;
}
