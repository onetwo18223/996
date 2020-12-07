package com.bhh.guava;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bhh
 * @description
 * @date Created in 2020-12-07 21:03
 * @modified By
 */
public class immutableTest {

    @Test
    public void immutableTest1() {

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        /*创建immutableList*/
        //根据list创建immutableList
        ImmutableList<Integer> integers = ImmutableList.copyOf(list);

        //根据方法of创建
        ImmutableList<Integer> integers1 = ImmutableList.of(1, 2, 3, 4);

        int a[] = {1, 2};
        ImmutableList.builder()
                .add(1)
                .addAll(Lists.newArrayList(2, 3))
                .build()
                .forEach(System.err::println);


    }
}
