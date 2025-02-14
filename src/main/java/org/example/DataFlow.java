package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataFlow {
    public static void main(String[] args) {
        // 1️⃣ Créer une liste vide pour Population<String, Integer>
        List<Population<String, Integer>> populationData = new ArrayList<>();

        // 2️⃣ Ajouter des pays dynamiquement
        populationData.add(new Population<>("FR", "France", "Paris", "Français", 67413000));
        populationData.add(new Population<>("JP", "Japon", "Tokyo", "Japonais", 125800000));
        populationData.add(new Population<>("CA", "Canada", "Ottawa", "Anglais", 38008005));
        populationData.add(new Population<>("US", "États-Unis", "Washington D.C.", "Anglais", 331000000));

        System.out.println("✅ Données après ajout :");
        populationData.forEach(System.out::println);

        // 3️⃣ Appliquer le filtre sur la langue (Anglais uniquement)
        List<Population<String, Integer>> filteredByLanguage = FilterByLanguage.filter(populationData);
        System.out.println("\n✅ Pays anglophones :");
        filteredByLanguage.forEach(System.out::println);

        // 4️⃣ Appliquer le filtre sur la population (> 30M)
        List<Population<String, Integer>> filteredByPopulation = FilterByPopulation.filter(filteredByLanguage);
        System.out.println("\n✅ Pays anglophones avec +30M d’habitants :");
        filteredByPopulation.forEach(System.out::println);

        // 5️⃣ Appliquer la transformation et récupérer la Map des catégories
        Map<String, String> categoryMap = CalculateColumns.getCategoryMap(filteredByPopulation);
        System.out.println("\n✅ Catégories associées aux pays :");
        categoryMap.forEach((key, value) ->
                System.out.println("Référence: " + key + " -> Catégorie: " + value));
    }
}