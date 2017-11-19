package com.lucy.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.lucy.domain.Profile;
import com.lucy.service.ProfileService;



public class ProfileIdValidator implements ConstraintValidator<ProfileId, Long> {

	@Autowired
	private ProfileService profileService;

	@Override
	public void initialize(ProfileId arg0) {
	}

	@Override
	public boolean isValid(Long value, ConstraintValidatorContext context) {
		Profile profile = null;
		try {
			profile = profileService.findById(value);
		} catch (Exception e) {
			System.out.println("Couldn't find product...");
		}
		return profile == null ? true : false;
	}

	

}
