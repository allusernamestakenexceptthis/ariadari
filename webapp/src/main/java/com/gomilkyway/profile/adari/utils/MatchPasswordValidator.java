package com.gomilkyway.profile.adari.utils;

import org.springframework.beans.BeanWrapperImpl;

import com.gomilkyway.profile.adari.annotations.MatchPassword;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Match password validator
 * 
 */
public class MatchPasswordValidator implements ConstraintValidator<MatchPassword, Object> {

    private String matchFieldName;

    @Override
    public void initialize(final MatchPassword constraintAnnotation) {
        this.matchFieldName = constraintAnnotation.matchField();
    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        final String field = (String) value;
        final Object matchField = new BeanWrapperImpl().getPropertyValue(matchFieldName);
        return field.equals(matchField);
    }
    
}
