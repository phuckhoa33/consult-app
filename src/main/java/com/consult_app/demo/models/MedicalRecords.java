package com.consult_app.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MedicalRecords {
    private int recordId;
    private int userId;
    private String confirmationDate;
    private String doctorSignature;
    private String createdAt;
    private String updatedAt;
}
