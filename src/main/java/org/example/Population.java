package org.example;

import java.util.ArrayList;
import java.util.List;

public record Population(String reference, String country, String capital, String language, int population) {
    public List<Population> getListPopulation() { List<Population> listePopulation = new ArrayList<>();

        // Ajouter des instances de Population à la liste
        listePopulation.add(new Population("FR", "France", "Paris", "Français", 67413000));
        listePopulation.add(new Population("JP", "Japon", "Tokyo", "Japonais", 125800000));
        listePopulation.add(new Population("CA", "Canada", "Ottawa", "Anglais", 38008005));
        listePopulation.add(new Population("DE", "Allemagne", "Berlin", "Allemand", 83019200));
        listePopulation.add(new Population("CL", "Chili", "Santiago", "Espagnol", 19200000));
        listePopulation.add(new Population("EG", "Égypte", "Le Caire", "Arabe", 104124440));
        listePopulation.add(new Population("NG", "Nigeria", "Abuja", "Anglais", 223800000));
        listePopulation.add(new Population("VN", "Vietnam", "Hanoï", "Vietnamien", 98100000));
        listePopulation.add(new Population("DK", "Danemark", "Copenhague", "Danois", 5800000));
        listePopulation.add(new Population("SE", "Suède", "Stockholm", "Suédois", 10300000));
        listePopulation.add(new Population("PH", "Philippines", "Manille", "Filipino", 113000000));
        listePopulation.add(new Population("AR", "Argentine", "Buenos Aires", "Espagnol", 46200000));
        listePopulation.add(new Population("US", "États-Unis", "Washington D.C.", "Anglais", 331000000));
        return listePopulation;
    }

}


