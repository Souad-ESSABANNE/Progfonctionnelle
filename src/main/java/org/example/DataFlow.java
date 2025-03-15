package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DataFlow {
    public static void main(String[] args) {

        List<Population<String, Integer>> populationData = new ArrayList<>();

        // Ajouter des pays dynamiquement
        populationData.add(new Population<>("FR", "France", "Paris", "Français", 67413000));
        populationData.add(new Population<>("JP", "Japon", "Tokyo", "Japonais", 125800000));
        populationData.add(new Population<>("CA", "Canada", "Ottawa", "Anglais", 38008005));
        populationData.add(new Population<>("US", "États-Unis", "Washington D.C.", "Anglais", 331000000));

        System.out.println("✅ Données Initiales :");
        populationData.forEach(System.out::println);

        // Appliquer le filtre sur la langue (Anglais uniquement)
        List<Population<String, Integer>> filteredByLanguage = FilterByLanguage.filter(populationData);
        System.out.println("\n✅ Données filitrés selon la langue : anglais :");
        filteredByLanguage.forEach(System.out::println);

        // 4️⃣ Appliquer le filtre sur la population (> 30M)
        List<Population<String, Integer>> filteredByPopulation = FilterByPopulation.filter(filteredByLanguage);
        System.out.println("\n Pays anglophones avec +30M d’habitants :");
        filteredByPopulation.forEach(System.out::println);

        // 5️⃣ Appliquer la transformation et récupérer la liste des catégories
        List<Pair<String, String>> categoryList = CalculateColumns.getCategoryList(filteredByPopulation);
        System.out.println("\n✅ Catégories associées aux pays :");
        categoryList.forEach(pair ->
                System.out.println("Référence: " + pair.key() + " -> Catégorie: " + pair.valeur()));
        System.out.println("Donnees a aggreger avec les données initiales");
        //ajout des indicateurs socials
        List<SocialIndicators<String, Integer>> socialIndicators = new ArrayList<>();
        socialIndicators.add(new SocialIndicators("France", 13.6, 90.0));
        socialIndicators.add(new SocialIndicators("Japon", 15.7, 92.0));
        socialIndicators.add(new SocialIndicators("Canada", 9.5, 99.0));
        socialIndicators.add( new SocialIndicators("Vietnam", 9.8, 94.5));
        socialIndicators.add( new SocialIndicators("Danemark", 5.0, 95.0));
        socialIndicators.add(new SocialIndicators("Suède", 7.0, 99.0));
        socialIndicators.add( new SocialIndicators("États-Unis", 11.8, 99.0));
        System.out.println("✅aDonnees apres aggregation");
        List<String> aggregatedData = Aggregator.aggregate(
                populationData,
                socialIndicators,
                (pop, ind) -> pop.country() + ": Population = " + pop.population() + ", Poverty Rate = " + ind.povertyRate() + ", Literacy Rate = " + ind.literacyRate()
        );
        aggregatedData.forEach(System.out::println);

        //Normaliser les données de population (convertir en millions)--> fixData
        List<String> normalizedData = FixData.normalizePopulationList(
                aggregatedData,
                pop -> String.format("%.2fM", pop.doubleValue() / 1_000_000) // Conversion en millions
        );

        System.out.println("\n✅ Population Normalisée (avec Literacy Rate)--> :");
        normalizedData.forEach(System.out::println);




    // Extraction de la colonne Literacy Rate-->ColumnSelector
        List<String> selectedLiteracyRate = ColumnSelector.selectColumn(
                normalizedData,
                data -> {
                    String[] parts = data.split(", ");
                    return parts[2].split("=")[1].trim(); // Extraction de Literacy Rate
                });

        System.out.println("\n✅ Colonnes Literacy Rate Sélectionnées :");
        selectedLiteracyRate.forEach(System.out::println);

        //  Affichage final en mentionnant que `normalizedData` est aussi `enhancedData`
        System.out.println("\n✅ Enhanced Data (data apres Normalized Data) :");
        normalizedData.forEach(System.out::println);

    }

    }