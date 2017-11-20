package com.lucy.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.lucy.domain.Profile;

public class EqualPasswordsValidator implements ConstraintValidator<EqualPasswords, Profile> {
   
    
    @Override
    public void initialize(EqualPasswords constraint) {
    }
    
    @Override
    public boolean isValid(Profile user, ConstraintValidatorContext context) {
     
      return user.getPassword().equals(user.getConfirmpassword());
    }

	
 
}