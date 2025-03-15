package org.example;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class Aggregator {

    public static <T, U, R> List<R> aggregate(List<Population<T, U>> populationList, List<SocialIndicators<T, U>> indicatorsList, BiFunction<Population<T, U>, SocialIndicators<T, U>, R> aggregator) {
        return populationList.parallelStream()
                .peek(item -> System.out.println("Thread " + Thread.currentThread().getName() + " traite : " + item))
                .flatMap(pop -> indicatorsList.stream()
                        .filter(ind -> pop.country().equals(ind.country()))
                        .map(ind -> aggregator.apply(pop, ind))
                )
                .collect(Collectors.toList());
    }
}
