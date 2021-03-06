package com.rob.shop.inventory.api.model.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = { PhoneNoValidator.class })
public @interface IsValidPhoneNo {
    String message() default "PhoneNo is incorrect";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
