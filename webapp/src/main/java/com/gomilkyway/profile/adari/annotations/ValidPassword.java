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
