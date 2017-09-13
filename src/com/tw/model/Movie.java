package com.tw.model;

//Represents a film
public class Movie implements LibraryItem {

    private static final String format = "%-35s %-35s %-10d %-10s";

    private final String name;
    private final String director;
    private final String rating;
    private final int yearReleased;

    public Movie(String name, int yearReleased, String director, String rating) {
        this.name = name;
        this.yearReleased = yearReleased;
        this.director = director;
        this.rating = rating;
    }

    @Override
    public String representation() {
        return String.format(format, this.name, this.director, this.yearReleased, this.rating);
    }

    @Override
    public boolean hasSameName(String name) {
        return this.name.equalsIgnoreCase(name);
    }

}
