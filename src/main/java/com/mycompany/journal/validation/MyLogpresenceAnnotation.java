package com.mycompany.journal.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy=LogConstraintValidator.class)
@Documented
public @interface MyLogpresenceAnnotation {
    String message() default "Get this";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
