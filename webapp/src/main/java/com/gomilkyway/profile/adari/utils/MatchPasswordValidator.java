package com.gomilkyway.profile.adari.utils;

import java.util.Objects;

import org.springframework.beans.BeanWrapperImpl;

import com.gomilkyway.profile.adari.annotations.MatchPassword;
import com.gomilkyway.profile.adari.user.UserDTO;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Match password validator
 * 
 */
public class MatchPasswordValidator implements ConstraintValidator<MatchPassword, Object> {

    private String passwordFieldName;
    private String confirmPasswordFieldName;

    @Override
    public void initialize(final MatchPassword constraintAnnotation) {
        this.passwordFieldName = constraintAnnotation.passwordField();
        this.confirmPasswordFieldName = constraintAnnotation.passwordField();
    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        if (Objects.isNull(value)) {
            return false;
        }
        final UserDTO user = (UserDTO) value;

        final String password = (String) new BeanWrapperImpl(user).getPropertyValue(passwordFieldName);
        final String confirmPassword = (String) new BeanWrapperImpl(user).getPropertyValue(confirmPasswordFieldName);

        if (password == null || confirmPassword == null) {
            return false;
        }
        
        return password.equals(confirmPassword);
    }
    
}
