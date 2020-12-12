package com.bhh.lambda;

import com.bhh.Stu;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Comparator;

/**
 * @author bhh
 * @description 关于方法引用的测试
 * @date Created in 2020-12-12 10:45
 * @modified By
 */
public class lambdaTest {

    /**
     * 对于可以运行生成的lambda表达式来说
     * 参数固定（因为只有一个可实现的方法）
     * 返回值类型固定
     *
     * 我们要做的就是实现这一个方法，所以我们更重要的是实现这唯一方法
     * 所以方法引用写的就是实现方法的内容
     */


    /**
     * 对于静态方法，使用方法引用
     */
    @Test
    public void test1(){
        Comparator<Integer> com1 =  (o1, o2) -> Integer.compare(o1, o2);
        System.out.println(com1.compare(1, 2));
        Comparator<Integer> com2 = Integer::compareTo;
        System.out.println(com2.compare(2, 1));
    }


    /**
     * 写的是实现方法需要的内容
     * 正如：List::stream
     * 实现的也是由list转换为stream，重要的是实现的方法体
     */
    @Test
    public void test2(){
        Stu stu = str -> System.out.println(str);
        Stu stu1 = System.out::println;

        stu.getStu("1");
        stu1.getStu("2");
    }
}
