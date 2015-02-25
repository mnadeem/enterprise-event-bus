package com.prokarma.middleware.eeb.service.support;

import javax.inject.Named;

import org.switchyard.annotations.Validator;
import org.switchyard.validate.BaseValidator;
import org.switchyard.validate.ValidationResult;

import com.prokarma.middleware.eeb.service.model.Notification;

@Named("Validators")
public class Validators {

    @Validator
    public ValidationResult validate(Notification notification) {
    	System.out.println("Validating notification** " +  notification);
        if (notification.getPublisher().equalsIgnoreCase("BAD_PUBLISHER")) {
            return BaseValidator.invalidResult("Bad Publisher");
        }
        return BaseValidator.validResult();
    }
}
