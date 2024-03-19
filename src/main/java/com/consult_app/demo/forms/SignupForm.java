package com.consult_app.demo.forms;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.consult_app.demo.annotations.ValidEmail;
import com.consult_app.demo.models.User;
import com.consult_app.demo.models.User.UserBuilder;
import com.drew.lang.annotations.NotNull;

import jakarta.validation.constraints.NotEmpty;
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
public class SignupForm {
    @NotNull
    @NotEmpty
    private String firstname;

    @NotNull
    @NotEmpty
    private String lastname;

    @NotNull
    @NotEmpty
    private String username;

    @NotNull
    @NotEmpty
    private String password;

    private String matchingPassword;

    @NotNull
    @NotEmpty
    // @ValidEmail
    private String email;

//    @NotNull
//    @NotEmpty
//    private String avatar;
//    
//    @NotNull
//    @NotEmpty
//    private String phoneNumber;
//    
//    @NotNull
//    private String dateOfBirth;
//    
//    @NotNull
//    private boolean gender;
//    
//    @NotNull
//    @NotEmpty
//    private boolean ban;
//    
//    @NotNull
//    @NotEmpty
//    private String address;
}
