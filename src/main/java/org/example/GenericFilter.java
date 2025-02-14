package org.example;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class GenericFilter {
    public static <T> List<T> filter(List<T> data, Predicate<T> condition) {
        return data.stream()
                .filter(condition)
                .collect(Collectors.toList());
    }
}