/*
 * Copyright 2023 Ari Adari
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gomilkyway.profile.adari.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.gomilkyway.profile.adari.utils.MatchPasswordValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
/**
 * Match password annotation interface to ensure
 * password and confirm password fields match
 */
@Documented
@Target({ ElementType.TYPE })
@Constraint(validatedBy = { MatchPasswordValidator.class })
@Retention(RUNTIME)
public @interface MatchPassword {
    String message() default "Passwords do not match";
    String passwordField();
    String confirmField();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}