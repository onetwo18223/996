package com.bhh.stream;

import com.alibaba.fastjson.JSON;
import com.bhh.SkuService;
import com.bhh.entity.Sku;
import com.bhh.enums.SkuEnum;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

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
    public void mapTest() {
        list.stream()

                //中间操作：map，函数式参数
                .map((t) -> {
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
    public void flatMapTest() {
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
     * peek操作（消费型接口）：提供对于流中每一个对象操作（对流进行遍历操作）
     */
    @Test
    public void peekTest() {
        list.stream()

                //中间操作
                .peek((sku) -> {
                    System.out.println(sku.getSkuName());
                })

                //终端操作
                .forEach(item -> {
                    System.err.println(JSON.toJSONString(item, true));
                });
    }

    /**
     * sorted操作：进行排序，参数是实现Comparator（大大正序，大小倒序）
     */
    @Test
    public void sortTest() {
        list.stream()

                //中间操作
                .sorted(new Comparator<Sku>() {
                    @Override
                    public int compare(Sku sku1, Sku sku2) {
                        if (sku1.getTotalPrice() > sku2.getTotalPrice()) {
                            return 1;
                        } else if (sku1.getTotalPrice() < sku2.getTotalPrice()) {
                            return -1;
                        } else {
                            return 0;
                        }
                    }
                })

                //终端操作
                .forEach(item -> {
                    System.err.println(JSON.toJSONString(item, true));
                });
    }

    /**
     * sorted+peek
     * peek处理之后会在sort处进行汇总，所以peek的执行顺序有了变化
     * 所以，无状态操作（peek）和有状态操作（sorted）对数据执行的先后会有所不同
     */
    @Test
    public void sortPeekTest() {
        list.stream()
                .peek(sku -> {
                    System.out.println(sku.getSkuName());
                })

                //中间操作
                .sorted(new Comparator<Sku>() {
                    @Override
                    public int compare(Sku sku1, Sku sku2) {
                        if (sku1.getTotalPrice() > sku2.getTotalPrice()) {
                            return 1;
                        } else if (sku1.getTotalPrice() < sku2.getTotalPrice()) {
                            return -1;
                        } else {
                            return 0;
                        }
                    }
                })

                //终端操作
                .forEach(item -> {
                    System.err.println(JSON.toJSONString(item, true));
                });
    }

    /**
     * distinct操作：去重处理
     */
    @Test
    public void distinctTest() {
        list.stream()

                .map(sku -> sku.getSkuCategory())

                //去重
                .distinct()

                //终端操作
                .forEach(item -> {
                    System.err.println(JSON.toJSONString(item, true));
                });
    }

    /**
     * skip操作：跳过流中的几个数据
     */
    @Test
    public void skipTest() {
        list.stream()
                .sorted(Comparator.comparing(
                        sku -> sku.getTotalPrice()))

                //跳过3个
                .skip(3)

                //终端操作
                .forEach(item -> {
                    System.err.println(JSON.toJSONString(item, true));
                });
    }

    /**
     * limit操作：限制数量
     */
    @Test
    public void limitTest() {
        list.stream()

                .sorted(Comparator.comparing(Sku::getTotalPrice))

                //限制数量为3个
                .limit(3)

                //终端操作
                .forEach(item -> {
                    System.err.println(JSON.toJSONString(item, true));
                });
    }

    /**
     * skip+limit：skip：跳过多少条数据，limit：限制输出数量
     * 配合食用可以达到分页的目的
     */
    @Test
    public void skipLimitTest() {
        list.stream()

                .sorted(Comparator.comparing(Sku::getTotalPrice))

                .skip(1 * 3)//第二页

                //限制数量为3个
                .limit(3)

                //终端操作
                .forEach(item -> {
                    System.err.println(JSON.toJSONString(item, true));
                });
    }

    //终端操作
    /**
     * allMatch（断言型函数）：判断流中数据是否符合条件返回ture和false,
     * 短路操作（执行代码时一有不符合就返回（全部符合条件返回ture））
     */
    @Test
    public void allMatchTest(){
        boolean judge = list.stream()

                .peek(sku -> {
                    System.out.println(sku.getSkuName());
                })

                .allMatch(sku -> sku.getTotalPrice() > 1000);

        System.out.println(judge);
    }

    /**
     * angMatch（断言型函数）：判断流中数据是否符合条件，
     * 一碰到符合条件的一个数据，就停止程序且返回ture，
     * 若是无符合条件的数据，就返回false
     */
    @Test
    public void anyMatchTest(){
        boolean judge = list.stream()

                .peek(sku -> {
                    System.out.println(sku.getSkuName());
                })

                .anyMatch(sku -> sku.getTotalPrice() > 10000);

        System.out.println(judge);
    }

    /**
     * noneMatch：一有符合就返回false（全部不符合返回ture）
     */
    @Test
    public void noneMatchTest(){
        boolean judge = list.stream()

                .peek(sku -> System.out.println(sku.getSkuName()))

                .noneMatch(sku -> sku.getTotalPrice() > 10000);

        System.out.println(judge);
    }

    /**
     * findFirst：获取流中的第一个数据
     */
    @Test
    public void findFirstTest(){
        Optional<Sku> sku = list.stream()
                .findFirst();

        System.out.println(JSON.toJSONString(sku.get(), true));
    }

    /**
     * findAny和findFirst：
     * 在并行的条件下：findAny表示获取任意元素，有随机性
     *              findFirst表示获取第一个元素，但是有限制性
     * 在串行的条件下：findAny和fingFirst表达的意思没有什么差别
     */
    @Test
    public void findAnyTest(){
        Optional<Sku> skuList = list.stream()
                .findAny();

        System.out.println(JSON.toJSONString(skuList.get(), true));
    }

    /**
     * max（）：获取最大值
     */
    @Test
    public void maxTest(){
        OptionalDouble optionalDouble = list.stream()
                //中间操作
                //mapToDouble：将数据映射为double类型
                .mapToDouble(Sku::getTotalPrice)

                .max();

        System.out.println(optionalDouble.getAsDouble());
    }

    /**
     * min（）：获取最小值
     */
    @Test
    public void minTest(){
        OptionalDouble optionalDouble = list.stream()
                .mapToDouble(Sku::getTotalPrice)
                .min();
        System.out.println(optionalDouble.getAsDouble());
    }

    /**
     * 获取元素总数
     */
    @Test
    public void countTest(){
        long count = list.stream()
                .count();

        System.out.println(count);
    }

}
