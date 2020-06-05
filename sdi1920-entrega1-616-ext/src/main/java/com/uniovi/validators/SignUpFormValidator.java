package com.uniovi.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.*;

import com.uniovi.entities.User;


@Component
public class SignUpFormValidator implements Validator {
	//@Autowired
	//private UsersService usersService;

	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
//		User user = (User) target;
//		
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "Error.empty");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "Error.empty");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "Error.empty");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Error.empty");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordConfirm", "Error.empty");
//		
//		if (user.getName().length() < 1 || user.getName().length() > 24) {
//			errors.rejectValue("name", "Error.signup.name.length");
//		}
//		if (user.getLastName().length() < 2 || user.getPassword().length() > 30) {
//			errors.rejectValue("lastName", "Error.signup.lastName.length");
//		}
//		if (user.getEmail().length() < 5 || user.getEmail().length() > 40) {
//			errors.rejectValue("email", "Error.signup.email.length");
//		}
//		if (user.getPassword().length() < 5 || user.getPassword().length() > 24) {
//			errors.rejectValue("password", "Error.signup.password.length");
//		}
//		if (!user.getPasswordConfirm().equals(user.getPassword())) {
//			errors.rejectValue("passwordConfirm", "Error.signup.passwordConfirm.coincidence");
//		}
//		if (usersService.getUserByEmail(user.getEmail()) != null) {
//			errors.rejectValue("email", "Error.signup.email.duplicate");
//		}
//		
	
	}
}