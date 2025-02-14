package org.example;

import java.util.List;
import java.util.stream.Collectors;

public class ColumnSelector {
    public static <T, U> List<Population<T, U>> selectColumns(List<Population<T, U>> data) {
        return data.stream()
                .map(p -> new Population<>(p.reference(), p.country(), null, null, p.population())) // Sélectionne seulement `reference`, `country`, `population`
                .collect(Collectors.toList());
    }
}