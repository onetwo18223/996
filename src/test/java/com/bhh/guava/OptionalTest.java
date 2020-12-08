package com.bhh.guava;

import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author bhh
 * @description
 * @date Created in 2020-12-07 16:02
 * @modified By
 */
public class OptionalTest {

    /**
     * 创建optional和对象的常用方法
     */
    @Test
    public void optionalTest1() {
        /**
         * Optional创建方式：
         */
        Optional<Object> optional = Optional.empty();

        //常用1
        //optional = Optional.of("bhh");
        //常用2
        //optional = Optional.ofNullable("bhh1");

        //System.out.println(optional.get());

        /**
         * 判断是否是引用缺失
         */
        //不常用，因为原理知识判断是否为null
        System.out.println(optional.isPresent());
        /**
         * 当内容为空时，不执行
         */
        optional.ifPresent(System.err::println);

        /**
         * 当内容为空时执行
         */
        //执行语句
        System.out.println(optional.orElse("orElse"));
        //执行方法
        System.out.println(optional.orElseGet(() -> {
            return "orElseGet";
        }));
        //执行报错
        /*try {
            System.out.println(optional.orElseThrow(() -> {
                return new Exception("orElseThrow");
            }));
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        List<String> list = null;
        optional.ofNullable(list)
                //.map(strings -> strings.stream())
                .map(List::stream)

                //.orElseGet(()->Stream.empty())
                .orElseGet(Stream::empty)
                .forEach(System.err::println);
    }
}
