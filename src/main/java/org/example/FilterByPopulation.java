package org.example;

import java.util.List;

public class FilterByPopulation {
    public static <T, U extends Number> List<Population<T, U>> filter(List<Population<T, U>> data) {
        return GenericFilter.filter(data, p -> p.population().longValue() > 30000000);
    }
}