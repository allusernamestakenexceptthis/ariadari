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

package com.gomilkyway.profile.adari.annotations;

import java.lang.annotation.Target;

import com.gomilkyway.profile.adari.utils.ValidPasswordValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Valid password annotation
 * 
 */
@Target({ ElementType.FIELD })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = { ValidPasswordValidator.class })
public @interface ValidPassword {
    String message() default "Password must be between 8 and 64 characters, and contain at least one digit, one lowercase, one uppercase, and one special character";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    int minLength() default 8;
    int maxLength() default 64;
    boolean hasDigit() default true;
    boolean hasLowercase() default true;
    boolean hasUppercase() default true;
    boolean hasSpecial() default true;
    
}
