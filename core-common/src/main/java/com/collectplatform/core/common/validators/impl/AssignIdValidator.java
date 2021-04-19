package com.collectplatform.core.common.validators.impl;

import com.collectplatform.core.common.validators.annotation.AssignId;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AssignIdValidator implements ConstraintValidator<AssignId, Long> {
    private boolean require = false;

    @Override
    public void initialize(AssignId constraintAnnotation) {
        require = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext constraintValidatorContext) {
        String sid = id.toString();
        return sid.length() == 19;
    }
}
