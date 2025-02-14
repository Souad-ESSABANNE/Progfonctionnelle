package org.example;

import java.util.List;

public class FilterByLanguage {
    public static <T, U> List<Population<T, U>> filter(List<Population<T, U>> data) {
        return GenericFilter.filter(data, p -> p.language().equals("Anglais"));
    }
}