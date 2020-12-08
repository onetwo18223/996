package com.bhh.guava;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author bhh
 * @description
 * @date Created in 2020-12-07 22:23
 * @modified By
 */
public class SetsTest {
    /**
     * TODO:Sets常用方法+Lists常用方法
     * Sets工具类常用的方法：
     * 并集 / 交集 / 差价 / 分解集合中的所有子集 / 求俩个集合得笛卡尔积
     * <p>
     * Lists工具集常用的方法：
     * 反转 / 拆分
     */
    private Set set1 = Sets.newHashSet(1, 2, 3);
    private Set set2 = Sets.newHashSet(4, 5, 6);

    /**
     * 并集
     */
    @Test
    public void unionTest() {
        Set<Integer> set = Sets.union(set1, set2);
        System.out.println(set);
    }

    /**
     * 交集
     */
    @Test
    public void intersectionTest() {
        Set<Integer> set = Sets.intersection(set1, set2);
        System.out.println(set);
    }

    /**
     * 差集：属于集合a但是不属于b
     */
    @Test
    public void differenceTest() {
        Set<Integer> set = Sets.difference(set1, set2);
        System.out.println(set);

        //相对差集：属于集合a，不属于集合b 或者 属于集合b，不属于集合a
        set = Sets.symmetricDifference(set1, set2);
        System.out.println(set);
    }

    /**
     * 获取全部子集
     */
    @Test
    public void powerTest() {
        Set<Set<Integer>> set = Sets.powerSet(set1);

        System.out.println(JSON.toJSONString(set, true));
    }

    /**
     * 获取笛卡尔积
     */
    @Test
    public void cartesianTest() {
        Set<List<Integer>> set = Sets.cartesianProduct(set1, set2);
        System.out.println(set);
    }

    /**
     * 拆分
     */
    @Test
    public void partitionTest() {
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5, 7);

        List<List<Integer>> lists = Lists.partition(list, 4);
        System.out.println(lists);
    }

    /**
     * 反转
     */
    @Test
    public void reverseTest(){
        ArrayList<Object> list = Lists.newArrayList();
        list.add(1);
        list.add(2);
        list.add(3);

        List<Object> list1 = Lists.reverse(list);
        System.out.println(list1);
    }
}
