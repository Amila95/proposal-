package com.Adinz.Proposal.Validator;

import com.Adinz.Proposal.PalyLoad.RegisterRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        RegisterRequest user = (RegisterRequest) target;
        if(user.getPassword().length()<6){
            errors.rejectValue("password","Length", "Password must be at least 6 characters");
        }

    }
}
