package org.example;

import java.util.ArrayList;
import java.util.List;

public record SocialIndicators(String country, double povertyRate, double literacyRate) {

    /**
     * cette methode permet de recuperer
     * les données qu'on va manipuler lors de l'aggregation avec les données du record population
     * @return List<SocialIndicators>
     */
    public List<SocialIndicators> getListSocialIndicators() {
        List<SocialIndicators> listeIndicators = new ArrayList<>();

        listeIndicators.add(new SocialIndicators("France", 13.6, 99.0));
        listeIndicators.add(new SocialIndicators("Japon", 15.7, 99.0));
        listeIndicators.add(new SocialIndicators("Canada", 9.5, 99.0));
        listeIndicators.add(new SocialIndicators("Allemagne", 15.0, 99.0));
        listeIndicators.add(new SocialIndicators("Chili", 11.7, 97.0));
        listeIndicators.add(new SocialIndicators("Égypte", 27.8, 71.0));
        listeIndicators.add(new SocialIndicators("Nigeria", 46.0, 62.0));
        listeIndicators.add(new SocialIndicators("Vietnam", 9.8, 94.5));
        listeIndicators.add(new SocialIndicators("Danemark", 5.0, 99.0));
        listeIndicators.add(new SocialIndicators("Suède", 7.0, 99.0));
        listeIndicators.add(new SocialIndicators("Philippines", 16.6, 98.0));
        listeIndicators.add(new SocialIndicators("Argentine", 25.7, 98.1));
        listeIndicators.add(new SocialIndicators("États-Unis", 11.8, 99.0));

        return listeIndicators;
    }
}
