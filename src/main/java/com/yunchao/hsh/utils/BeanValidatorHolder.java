package com.yunchao.hsh.utils;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.validation.metadata.BeanDescriptor;
import java.util.Set;

/**
 * Created by wangqi on 2017/11/7
 */
public class BeanValidatorHolder {
    private static Validator validator;

    public BeanValidatorHolder() {
    }

    public void afterPropertiesSet() throws Exception {
        if (validator == null) {
            throw new IllegalStateException("not found JSR303(HibernateValidator) 'validator' for BeanValidatorHolder ");
        }
    }

    public void setValidator(Validator v) {
        if (validator != null) {
            throw new IllegalStateException("BeanValidatorHolder already holded 'validator'");
        } else {
            validator = v;
        }
    }

    private static Validator getRequiredValidator() {
        if (validator == null) {
            throw new IllegalStateException("'validator' property is null,BeanValidatorHolder not yet init.");
        } else {
            return validator;
        }
    }

    public static final <T> Set<ConstraintViolation<T>> validate(T object, Class... groups) {
        return getRequiredValidator().validate(object, groups);
    }

    public static final <T> void validateWithException(T object, Class... groups) throws ConstraintViolationException {
        Set constraintViolations = getRequiredValidator().validate(object, groups);
        String msg = "validate failure on object:" + object.getClass().getSimpleName();
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(msg, constraintViolations);
        }
    }

    public static final <T> Set<ConstraintViolation<T>> validateProperty(T object, String propertyName, Class... groups) {
        return getRequiredValidator().validateProperty(object, propertyName, groups);
    }

    public static final <T> void validatePropertyWithException(T object, String propertyName, Class... groups) throws ConstraintViolationException {
        Set constraintViolations = getRequiredValidator().validateProperty(object, propertyName, new Class[0]);
        String msg = "validate property failure on object:" + object.getClass().getSimpleName() + "." + propertyName + "";
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(msg, constraintViolations);
        }
    }

    public static final <T> Set<ConstraintViolation<T>> validateValue(Class<T> beanType, String propertyName, Object value, Class... groups) {
        return getRequiredValidator().validateValue(beanType, propertyName, value, groups);
    }

    public static final <T> void validateValueWithException(Class<T> beanType, String propertyName, Object value, Class... groups) throws ConstraintViolationException {
        Set constraintViolations = getRequiredValidator().validateValue(beanType, propertyName, value, new Class[0]);
        String msg = "validate value failure on object:" + beanType.getSimpleName() + "." + propertyName + " value:" + value;
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(msg, constraintViolations);
        }
    }

    public static final BeanDescriptor getConstraintsForClass(Class<?> clazz) {
        return getRequiredValidator().getConstraintsForClass(clazz);
    }

    public static final <T> T unwrap(Class<T> type) {
        return getRequiredValidator().unwrap(type);
    }

    public static void cleanHolder() {
        validator = null;
    }
}
