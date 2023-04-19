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

import java.util.Arrays;
import java.util.List;

import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.Rule;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;

import com.gomilkyway.profile.adari.annotations.ValidPassword;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Valid password validator
 * 
 */
public class ValidPasswordValidator implements ConstraintValidator<ValidPassword, String> {
    
    private List<Rule> rules;

    @Override
    public void initialize(ValidPassword constraintAnnotation) {
        this.rules = Arrays.asList(
            new LengthRule(constraintAnnotation.minLength(), constraintAnnotation.maxLength()),
            new CharacterRule(EnglishCharacterData.UpperCase, constraintAnnotation.hasUppercase() ? 1 : 0),
            new CharacterRule(EnglishCharacterData.LowerCase, constraintAnnotation.hasLowercase() ? 1 : 0),
            new CharacterRule(EnglishCharacterData.Digit, constraintAnnotation.hasDigit() ? 1 : 0),
            new CharacterRule(EnglishCharacterData.Special, constraintAnnotation.hasSpecial() ? 1 : 0)
        );
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        PasswordValidator validator = new PasswordValidator(this.rules);
        return validator.validate(new PasswordData(password)).isValid();
    }

}
