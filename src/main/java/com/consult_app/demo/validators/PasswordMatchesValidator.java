package com.consult_app.demo.validators;

import com.consult_app.demo.annotations.PasswordMatches;
import com.consult_app.demo.forms.SignupForm;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator
        implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        SignupForm user = (SignupForm) obj;
        return user.getPassword().equals(user.getMatchingPassword());
    }
}