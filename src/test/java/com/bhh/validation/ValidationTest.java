package com.bhh.validation;

import com.bhh.SkuService;
import com.bhh.entity.Sku;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.executable.ExecutableValidator;
import javax.validation.metadata.ConstraintDescriptor;
import java.lang.reflect.Method;
import java.util.*;

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

    private Set<ConstraintViolation<SkuService>> set1;

    @Before
    public void init() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        sku = new Sku();
        sku.setSkuName("18273461267");
        //sku.setSkuPrice(11.2);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, 10, 10);
        Date date = calendar.getTime();
        //System.out.println(calendar.getTime());
    }

    @After
    public void print() {

        set = Optional.ofNullable(set).orElseGet(() -> {
            Set<ConstraintViolation<Sku>> set = new HashSet<>();
            set.add(new ConstraintViolation<Sku>() {
                @Override
                public String getMessage() {
                    return null;
                }

                @Override
                public String getMessageTemplate() {
                    return null;
                }

                @Override
                public Sku getRootBean() {
                    return null;
                }

                @Override
                public Class<Sku> getRootBeanClass() {
                    return null;
                }

                @Override
                public Object getLeafBean() {
                    return null;
                }

                @Override
                public Object[] getExecutableParameters() {
                    return new Object[0];
                }

                @Override
                public Object getExecutableReturnValue() {
                    return null;
                }

                @Override
                public Path getPropertyPath() {
                    return null;
                }

                @Override
                public Object getInvalidValue() {
                    return null;
                }

                @Override
                public ConstraintDescriptor<?> getConstraintDescriptor() {
                    return null;
                }

                @Override
                public <U> U unwrap(Class<U> aClass) {
                    return null;
                }
            });
            return set;
        });

        set.forEach((item) -> {
            System.out.println(item.getMessage());
        });
    }

    @Test
    public void nullTest() {
        set = validator.validate(sku);
    }

    /**
     * 分组运行
     */
    @Test
    public void groupValidationTest() {
        //set = validator.validate(sku, Sku.LoginGroup.class);
        set = validator.validate(sku, Sku.RegistGroup.class);
    }

    /**
     * 组序列：定义组的执行顺序，但是组一但不符合条件则直接跳出，不会进行后面的组
     */
    @Test
    public void groupSequenceValidationTest() {
        set = validator.validate(sku, Sku.Group.class);
    }

    @Test
    public void skuServiceParamtersTest() throws NoSuchMethodException {
        ExecutableValidator executableValidator =
                validator.forExecutables();

        SkuService skuService = new SkuService();

        Method method = skuService.getClass()
                .getMethod("setSku", Sku.class);

        Object[] paramters = new Object[]{new Sku()};

        set1 = executableValidator
                .validateParameters(
                        skuService,
                        method,
                        paramters);
        System.out.println(set1);
    }
}
