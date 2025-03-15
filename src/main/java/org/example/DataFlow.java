package org.example;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class DataFlow {
    public static void main(String[] args) {

        //  Début du monitoring de la mémoire
        Runtime runtime = Runtime.getRuntime();
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("\nMémoire AVANT exécution : " + memoryBefore / 1_000_000 + " MB");

        long startTime = System.nanoTime(); // Début du monitoring

        List<Population<String, Integer>> populationData = new ArrayList<>();

        // Ajouter des pays dynamiquement
        populationData.add(new Population<>("FR", "France", "Paris", "Français", 67413000));
        populationData.add(new Population<>("JP", "Japon", "Tokyo", "Japonais", 125800000));
        populationData.add(new Population<>("CA", "Canada", "Ottawa", "Anglais", 38008005));
        populationData.add(new Population<>("US", "États-Unis", "Washington D.C.", "Anglais", 331000000));

        System.out.println("✅ Données Initiales :");
        populationData.forEach(System.out::println);

        long filterStart = System.nanoTime();

        // Appliquer le filtre sur la langue en parallèle

        List<Population<String, Integer>> filteredByLanguage = FilterByLanguage.filter(populationData);
        System.out.println("\n✅ Données filtrées selon la langue : anglais :");
        filteredByLanguage.forEach(System.out::println);
        System.out.println("Temps pour filtrer par langue : " + (System.nanoTime() - filterStart) / 1_000_000 + " ms");


        long popFilterStart = System.nanoTime();
        // Appliquer le filtre sur la population (> 30M) en parallèle
        List<Population<String, Integer>> filteredByPopulation = FilterByPopulation.filter(filteredByLanguage);
        System.out.println("\n✅ Pays anglophones avec +30M d’habitants :");
        filteredByPopulation.forEach(System.out::println);
        System.out.println(" Temps pour filtrer par population : " + (System.nanoTime() - popFilterStart) / 1_000_000 + " ms");

        //  Récupérer la liste des catégories en parallèle
        long categoryStart = System.nanoTime();
        List<Pair<String, String>> categoryList = CalculateColumns.getCategoryList(filteredByPopulation);
        System.out.println("\n✅ Catégories associées aux pays :");
        System.out.println("   🟢 Small  -> < 50M habitants");
        System.out.println("   🟡 Medium -> >= 50M et <= 150M habitants");
        System.out.println("   🔴 Large  -> > 150M habitants");

        categoryList.forEach(System.out::println);
        System.out.println(" Temps pour catégorisation : " + (System.nanoTime() - categoryStart) / 1_000_000 + " ms");

        // Ajout des indicateurs sociaux
        List<SocialIndicators<String, Integer>> socialIndicators = new ArrayList<>();
        socialIndicators.add(new SocialIndicators("France", 13.6, 90.0));
        socialIndicators.add(new SocialIndicators("Japon", 15.7, 92.0));
        socialIndicators.add(new SocialIndicators("Canada", 9.5, 99.0));
        socialIndicators.add( new SocialIndicators("Vietnam", 9.8, 94.5));
        socialIndicators.add( new SocialIndicators("Danemark", 5.0, 95.0));
        socialIndicators.add(new SocialIndicators("Suède", 7.0, 99.0));
        socialIndicators.add( new SocialIndicators("États-Unis", 11.8, 99.0));

        // Afficher les Social Indicators avant l'agrégation
        System.out.println("\n✅ Social Indicators Disponibles :");
        socialIndicators.forEach(System.out::println);
        //  Agrégation des données avec `CompletableFuture` pour exécution parallèle
        long aggregationStart = System.nanoTime();
        List<String> aggregatedData = Aggregator.aggregate(
                filteredByPopulation,
                socialIndicators,
                (pop, ind) -> pop.country() + ": Population = " + pop.population() +
                        ", Poverty Rate = " + ind.povertyRate() +
                        ", Literacy Rate = " + ind.literacyRate()
        );


        System.out.println("\n✅ Données après agrégation :");
        aggregatedData.forEach(System.out::println);
        System.out.println(" Temps pour agrégation : " + (System.nanoTime() - aggregationStart) / 1_000_000 + " ms");

        // Normalisation des données de population (convertir en millions) en parallèle


        long normalizationStart = System.nanoTime();
        List<String> normalizedData = FixData.normalizePopulationList(
                aggregatedData,
                pop -> String.format("%.2fM", pop.doubleValue() / 1_000_000) // Conversion en millions
        );

        System.out.println("\n✅ Enhanced Data (Normalized Data Normalisée avec Literacy Rate)  :");
        normalizedData.forEach(System.out::println);
        System.out.println(" Temps pour normalisation : " + (System.nanoTime() - normalizationStart) / 1_000_000 + " ms");

        //  Extraction de la colonne Literacy Rate avec `ColumnSelector`
        long selectionStart = System.nanoTime();
        List<String> selectedLiteracyRate = ColumnSelector.selectColumn(
                normalizedData,
                data -> {
                    String[] parts = data.split(", ");
                    return parts[2].split("=")[1].trim(); // Extraction de Literacy Rate
                });

        System.out.println("\n✅ Colonnes Literacy Rate Sélectionnées :");
        selectedLiteracyRate.forEach(System.out::println);
        System.out.println(" Temps pour extraction de la colonne : " + (System.nanoTime() - selectionStart) / 1_000_000 + " ms");


        //Monitoring de la Mémoire après exécution
        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("\n Mémoire APRÈS exécution : " + memoryAfter / 1_000_000 + " MB");
        System.out.println("\n Mémoire utilisée par le programme : " + (memoryAfter - memoryBefore) / 1_000_000 + " MB");

        // Temps total d'exécution
        long totalExecutionTime = System.nanoTime() - startTime;
        System.out.println("\n✅ Temps total d'exécution : " + totalExecutionTime / 1_000_000 + " ms");



    }
}