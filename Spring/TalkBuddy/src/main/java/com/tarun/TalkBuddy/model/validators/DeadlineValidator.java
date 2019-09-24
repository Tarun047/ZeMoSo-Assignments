package com.tarun.TalkBuddy.model.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.Instant;
import java.time.temporal.TemporalUnit;
import java.util.Date;
public class DeadlineValidator implements ConstraintValidator<Deadline, Date> {

    @Override
    public boolean isValid(Date value, ConstraintValidatorContext context) {
        return true;
    }
}