package com.bhh.stream;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 演示stream流的四种构造形式
 */
public class StreamConstructor {

    /**
     * 由数值直接构建流
     */
    @Test
    public void streanFormValue() {
        Stream stream = Stream.of(1, 2, 3, 4, 5);
        stream.forEach(System.err::println);
    }

    /**
     * 通过数组构建流
     */
    @Test
    public void streamFormArray() {
        int[] numbers = {1, 2, 3, 4, 5};
        IntStream stream =  Arrays.stream(numbers);
        stream.forEach(System.err::println);
    }

    /**
     * 通过文件来生成stream流
     * @throws IOException
     */
    @Test
    public void streamFormFile() throws IOException {
        Stream<String> stream = Files.lines(
                Paths.get("F:\\maker\\JAVA\\JavaProject\\" +
                "workspace01\\996\\src\\test\\java" +
                "\\com\\bhh\\stream\\StreamConstructor.java"));
        stream.forEach(System.err::println);
    }

    /**
     * 通过Iterate()方法来获取Stream流
     */
    @Test
    public void streamFormIterate(){
        Stream<Integer> stream = Stream.iterate(0, integer -> integer + 2);
        stream.limit(100)
                .forEach(System.err::println);
    }

    /**
     * 通过Generate()方法来获取Stream流
     */
    @Test
    public void streamFormGenerate(){
        Stream<String> stream = Stream.generate(
                () -> UUID.randomUUID().toString().substring(0, 8));
        stream.limit(100)
                .forEach(System.err::println);
    }

}
