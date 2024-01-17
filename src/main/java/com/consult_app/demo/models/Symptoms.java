package com.consult_app.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Symptoms {
    private int symptomId;
    private String symptomMain;
    private String symptomsAdditional;
    private String currentCondition;
}
