package com.bhh.stream;

import com.alibaba.fastjson.JSON;
import com.bhh.SkuService;
import com.bhh.entity.Sku;
import com.bhh.enums.SkuEnum;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : bhh
 * @description:归约和汇总
 * @date Created in 2020-12-07 10:26
 * @modified By:
 */
public class ReduceAndCollect {

    List<Sku> list;

    @Before
    public void init() {
        list = SkuService.getSkuList();
    }

    @Test
    public void reduceTest() {
        Sku sku = list.stream()
                //并行运行流操作
                .parallel()

                .reduce(
                        //合并初始数据
                        new Sku(0, "归约", (double) 0, 0, (double) 0,
                                SkuEnum.NOTHING)
                        //流中的数据合并操作
                        , (Sku sku1, Sku sku2) -> {
                            System.out.println("执行合并");

                            Integer totalNum =
                                    sku1.getTotalNum()
                                            + sku2.getTotalNum();

                            Double totalPrice =
                                    sku1.getTotalPrice()
                                            + sku2.getTotalPrice();

                            return new Sku(0, "归约", 0.0, totalNum, totalPrice,
                                    SkuEnum.NOTHING);
                        }
                        //并行运行时，最终合并计算结果
                        , (Sku sku1, Sku sku2) -> {
                            System.out.println("并行合并");

                            Integer totalNum =
                                    sku1.getTotalNum()
                                            + sku2.getTotalNum();

                            Double totalPrice =
                                    sku1.getTotalPrice()
                                            + sku2.getTotalPrice();

                            return new Sku(0, "归约", 0.0, totalNum, totalPrice,
                                    SkuEnum.NOTHING);
                        }
                );
        System.err.println(JSON.toJSONString(sku, true));

    }

    @Test
    public void collectTest() {
        HashMap<Integer, List<Sku>> collect = list.stream()
                .parallel()
                .collect(
                        () -> new HashMap<Integer, List<Sku>>(),
                        (HashMap<Integer, List<Sku>> map, Sku sku) -> {

                            System.out.println("合并");

                            //如果map中已经存在对应的数量
                            if (map.containsKey(sku.getTotalNum())) {
                                List<Sku> list = map.get(sku.getTotalNum());
                                list.add(sku);
                            } else {
                                //map中不存在对应数量的商品
                                List<Sku> list = new ArrayList<>();
                                list.add(sku);
                                map.put(sku.getTotalNum(), list);
                            }
                        },
                        (HashMap<Integer, List<Sku>> map1
                                , HashMap<Integer, List<Sku>> map2) -> {

                            System.out.println("汇总");

                            map2.forEach((key, valueList) -> {
                                map1.merge(key, valueList, (valueList1, valueList2) ->
                                        valueList1.stream()
                                                .collect(Collectors.toCollection(
                                                        () -> valueList2))
                                );
                            });
                        });

        System.out.println(JSON.toJSONString(collect, true));
    }
}
