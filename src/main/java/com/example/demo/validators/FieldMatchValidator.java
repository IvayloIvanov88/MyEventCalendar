package com.example.demo.validators;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {

    private String firstFieldName;
    private String secondFieldName;
    private String message;

    @Override
    public void initialize(FieldMatch constraintAnnotation) {
        this.firstFieldName = constraintAnnotation.first();
        this.secondFieldName = constraintAnnotation.second();
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        BeanWrapper wrapper = PropertyAccessorFactory.forBeanPropertyAccess(value);
        Object first = wrapper.getPropertyValue(firstFieldName);
        Object second = wrapper.getPropertyValue(secondFieldName);

        boolean valid = (first == null && second == null) || (first != null && first.equals(second));

        if (!valid) {
            context.
                    buildConstraintViolationWithTemplate(message).
                    addPropertyNode(firstFieldName).
                    addConstraintViolation().
                    buildConstraintViolationWithTemplate(message).
                    addPropertyNode(secondFieldName).
                    addConstraintViolation().
                    disableDefaultConstraintViolation();
        }
        return valid;
    }
}
