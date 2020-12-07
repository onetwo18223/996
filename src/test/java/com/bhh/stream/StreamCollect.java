package com.bhh.stream;

import com.alibaba.fastjson.JSON;
import com.bhh.SkuService;
import com.bhh.entity.Sku;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Stream终端操作之收集器
 */
public class StreamCollect {

    List<Sku> list;

    @Before
    public void init() {
        list = SkuService.getSkuList();
    }

    /**
     * 收集结果为list集合
     */
    @Test
    public void toListTest() {
        List<Sku> list1 = list.stream()
                .filter(sku -> sku.getTotalPrice() > 1000)
                .collect(Collectors.toList());
        System.out.println(JSON.toJSONString(list1, true));
    }

    /**
     * 根据分组收集结果
     */
    @Test
    public void groupTest() {

        //Map<分组条件，分组结果>
        Map<Enum, List<Sku>> collect = list.stream()
                .collect(Collectors.groupingBy(
                        sku -> sku.getSkuCategory()));

        System.out.println(JSON.toJSONString(collect, true));
    }

    /**
     * 根据判断条件收集结果
     */
    @Test
    public void judgeTest() {

        //Map<判断条件，结果集>
        Map<Boolean, List<Sku>> collect = list.stream()
                .collect(Collectors.partitioningBy(
                        sku -> sku.getTotalPrice() > 1000));

        System.out.println(JSON.toJSONString(collect, true));
    }
}
