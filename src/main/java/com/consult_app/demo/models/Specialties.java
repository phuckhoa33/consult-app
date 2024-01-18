package com.consult_app.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Specialties {
    private int specialtyId;
    private String name;
    private String createdAt;
    private String updatedAt;
}
