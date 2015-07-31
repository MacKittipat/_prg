package com.mackittipat.prg.validator;

import com.mackittipat.prg.model.UserModel;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Service
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return UserModel.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", null, "Name cannot be empty");

        UserModel userModel = (UserModel) o;
        if(userModel.getName().equals("e")) {
            errors.rejectValue("name", null, "User error");
        }
    }
}
