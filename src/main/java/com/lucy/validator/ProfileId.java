/*//package com.lucy.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ProfileIdValidator.class)
public @interface ProfileId {

	String message() default "{com.lucy.validator.ProfileId.message}";

	Class<?>[] groups() default {};

	public abstract Class<? extends Payload>[] payload() default {};

}*/
