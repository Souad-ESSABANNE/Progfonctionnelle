package org.example;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class GenericFilter {
    public static <T> List<T> filter(List<T> data, Predicate<T> condition) {
        return data.parallelStream()
                .peek(item -> System.out.println("Thread " + Thread.currentThread().getName() + " traite : " + item))
                .filter(condition)
                .collect(Collectors.toList());
    }
}