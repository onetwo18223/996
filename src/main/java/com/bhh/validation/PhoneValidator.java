package com.bhh.validation;

/**
 * @author bhh
 * @description 自定义Phone注解关联验证器
 * @date Created in 2020-12-08 12:46
 * @modified By
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class PhoneValidator
        implements ConstraintValidator<Phone, String> {

    @Override
    public boolean isValid(
            String s, ConstraintValidatorContext constraintValidatorContext) {

        System.out.println(s);

        String result = null;
        try {
            result = HttpUtil.doGet("http://tcc.taobao.com/cc/json/mobile_tel_segment.htm?" + "tel=" + s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("result:\n" + result);
        String json = result.replace("__GetZoneResult_ = ", "");
        JSONObject jobj = JSON.parseObject(json);
        String data1 = jobj.get("province").toString();
        System.out.println("号码归属地:" + data1);
        if (!"湖南".equals(data1)) {
            return false;
        }
        return true;
    }
}
