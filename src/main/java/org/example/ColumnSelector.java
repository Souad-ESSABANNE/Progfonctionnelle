package org.example;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ColumnSelector {
    public static <T, R> List<R> selectColumn(List<T> data, Function<T, R> columnExtractor) {
        return data.parallelStream()
                .peek(item -> {
                    synchronized (System.out) { // 🔹 Assure un affichage propre
                        System.out.println("🔄 Thread " + Thread.currentThread().getName() + " traite : " + item);
                    }
                })
                .map(columnExtractor) // Extrait la colonne demandée
                .collect(Collectors.toList());
    }
}