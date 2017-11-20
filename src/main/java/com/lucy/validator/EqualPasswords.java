package com.lucy.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {EqualPasswordsValidator.class})
public @interface EqualPasswords {
 
    String message() default "{com.lucy.EqualPasswords.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
 
}