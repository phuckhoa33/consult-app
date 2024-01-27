package com.consult_app.demo.dtos;

import com.consult_app.demo.models.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TokenChecker {
    private boolean expired;
    private User user;
}
