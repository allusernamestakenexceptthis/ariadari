/*
 * Copyright 2023 Ari Adari
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
        this.confirmPasswordFieldName = constraintAnnotation.confirmField();
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
        
        boolean isValid = password.equals(confirmPassword);
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                    .addPropertyNode(confirmPasswordFieldName).addConstraintViolation();
        }

        return isValid;   
    }
    
}
