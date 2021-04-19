package com.collectplatform.core.common.validators.annotation;

import com.collectplatform.core.common.validators.impl.AssignIdValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AssignIdValidator.class)
public @interface AssignId {
    boolean required() default true;
    String message() default "值/格式不正确";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
