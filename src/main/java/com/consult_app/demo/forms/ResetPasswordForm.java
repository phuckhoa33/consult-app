package com.consult_app.demo.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ResetPasswordForm {
    private String token;
    private String newPassword;
    private String confirmNewPassword;
}
