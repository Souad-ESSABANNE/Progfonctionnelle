package org.example;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FixData {
    public static <T, U extends Number, V> List<String> normalizePopulationList(
            List<String> aggregatedData, Function<U, V> valueTransformer) {
        return aggregatedData.parallelStream()
                .map(data -> {
                    String[] parts = data.split(", ");
                    String country = parts[0].split(": ")[0];
                    String populationStr = parts[0].split("=")[1].trim();
                    int population = Integer.parseInt(populationStr);

                    // Normalisation en millions
                    String normalizedPopulation = valueTransformer.apply((U) Integer.valueOf(population)).toString();

                    return country + " - Population: " + normalizedPopulation + ", " + parts[1] + ", " + parts[2];
                })
                .collect(Collectors.toList());
    }
}