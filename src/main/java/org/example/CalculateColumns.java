package org.example;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CalculateColumns {
    public static <T, R, U> List<Pair<T, R>> transform(
            List<U> data,
            Function<U, T> keyExtractor,
            Function<U, R> valueExtractor) {
        return data.parallelStream()
                .map(item -> new Pair<>(keyExtractor.apply(item), valueExtractor.apply(item)))
                .collect(Collectors.toList());
    }

    // Cette méthode crée une liste de catégories basée sur les populations
    public static <T> List<Pair<T, String>> getCategoryList(
            List<Population<T, Integer>> data) {
        return transform(
                data,
                Population::reference,  // Clé = Référence du pays
                p -> categorizePopulation(p.population()) // Valeur = Catégorie calculée
        );
    }

    // Logique pour catégoriser les populations en fonction de leur taille
    private static String categorizePopulation(Number population) {
        int pop = population.intValue();
        if (pop < 50000000) {
            return "Small";
        } else if (pop <= 150000000) {
            return "Medium";
        } else {
            return "Large";
        }
    }
}