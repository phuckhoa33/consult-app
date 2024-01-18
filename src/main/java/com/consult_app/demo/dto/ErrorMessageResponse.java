package com.consult_app.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class ErrorMessageResponse {
    private String message;
}