package com.mycompany.journal.validation;


import com.mycompany.journal.db.model.Logpresence;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LogConstraintValidator implements ConstraintValidator<MyLogpresenceAnnotation, Logpresence> {

    public void initialize(MyLogpresenceAnnotation constraintAnnotation) {
    }

    public boolean isValid(Logpresence logpresence, ConstraintValidatorContext context) {


//        if (logpresence.getNote().length() < 3) {
//            return false;
//        }


        return true;
    }

}
