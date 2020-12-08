package com.bhh.validation;

import com.bhh.validation.PhoneValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author bhh
 * @description
 * @date Created in 2020-12-08 12:40
 * @modified By
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneValidator.class)

public @interface Phone {

    //默认信息
    String message() default "手机号检验错误";

    //默认组别
    Class<?>[] groups() default {};

    //有效负载
    Class<? extends Payload>[] payload() default {};
}
