package com.bhh.stream;

import com.alibaba.fastjson.JSON;
import com.bhh.SkuService;
import com.bhh.entity.Sku;
import com.bhh.enums.SkuEnum;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class StreamTest {

    List<Sku> list;

    @Before
    public void init() {
        list = SkuService.getSkuList();
    }

    /**
     * filter操作（断言型接口）：过滤筛选
     */
    @Test
    public void filterTest() {
        list.stream()

                //中间操作：过滤操作，断言函数参数是流中的数据
                .filter((t) -> {
                    //return t.getTotalPrice() > 1000;
                    return SkuEnum.BOOKS.equals(t.getSkuCategory());
                })

                //终端操作
                //.forEach(System.err::println);
                .forEach(item -> {
                    System.err.println(JSON.toJSONString(item, true));
                });
    }

    /**
     * map操作（函数型接口）：对每一个元素进行操作，如Function<T,R>，参数为T,输出为R
     */
    @Test
    public void mapTest(){
        list.stream()

                //中间操作：map，函数式参数
                .map((t)->{
                    return t.getSkuName();
                })

                //终端操作
                .forEach(item -> {
                    System.err.println(JSON.toJSONString(item, true));
                });
    }

    /**
     * flatMap操作（函数型接口）：将对象转换为流的形势
     */
    @Test
    public void flatMapTest(){
        list.stream()

                //中间操作
                .flatMap(sku -> Arrays.stream(
                        sku.getSkuName().split("")
                ))

                //终端操作
                .forEach(item -> {
                    System.err.println(JSON.toJSONString(item, true));
                });
    }

    /**
     * peek操作（消费型接口）：提供对于流中每一个对象操作
     */
    @Test
    public void peekTest(){
        list.stream()

                //中间操作
                .peek((sku)->{
                    System.out.println(sku.getSkuName());
                })

                //终端操作
                .forEach(item -> {
                    System.err.println(JSON.toJSONString(item, true));
                });
    }
    
    public void sortTest(){

    }
}
