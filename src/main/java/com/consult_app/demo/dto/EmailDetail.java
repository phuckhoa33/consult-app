package com.consult_app.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmailDetail {
    private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;
    private EmailVariable variables;
}
