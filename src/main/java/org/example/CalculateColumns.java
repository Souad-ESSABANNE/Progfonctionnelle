package org.example;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CalculateColumns {
    public static <T, R, U extends Number> Map<T, R> transform(List<Population<T, U>> data, Function<Population<T, U>, T> keyExtractor, Function<Population<T, U>, R> valueExtractor) {
        return data.stream()
                .collect(Collectors.toMap(
                        keyExtractor,  // Clé = Référence du pays
                        valueExtractor // Valeur = Catégorie calculée
                ));
    }

    private static String categorizePopulation(Number population) {
        int pop = population.intValue(); // Convertit `U` en `int`
        if (pop < 50000000) {
            return "Small";
        } else if (pop <= 150000000) {
            return "Medium";
        } else {
            return "Large";
        }
    }

    public static <T> Map<T, String> getCategoryMap(List<Population<T, Integer>> data) {
        return transform(
                data,
                Population::reference,  // Clé = Référence du pays
                p -> categorizePopulation(p.population()) // Valeur = Catégorie calculée
        );
    }
}