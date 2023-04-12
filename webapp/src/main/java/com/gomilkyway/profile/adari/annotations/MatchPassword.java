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
 * Match password annotation
 * 
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