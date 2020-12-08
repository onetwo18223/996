package com.bhh.validation;

import com.bhh.entity.Sku;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @author bhh
 * @description
 * @date Created in 2020-12-08 9:24
 * @modified By
 */
public class ValidationTest {

    //定义验证器对象
    private Validator validator;

    //定义验证对象
    private Sku sku;

    //定义结果集
    private Set<ConstraintViolation<Sku>> set;

    @Before
    public void init(){
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        sku = new Sku();
        sku.setSkuName("13");
        sku.setSkuPrice(11.2);
    }

    @After
    public void print(){
        set.forEach((item)->{
            System.out.println(item.getMessage());
        });
    }

    @Test
    public void nullTest(){
        set = validator.validate(sku);
    }

}
