package org.example;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ColumnSelector {
    public static <T, R> List<R> selectColumn(List<T> data, Function<T, R> columnExtractor) {
        return data.parallelStream()
                .map(columnExtractor) // Extrait la colonne demandée
                .collect(Collectors.toList());
    }
}