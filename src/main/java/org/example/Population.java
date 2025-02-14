package org.example;

public record Population<T,U>(T reference, T country, T capital, T language, U population) { }