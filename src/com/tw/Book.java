package com.tw;

//Represent a Written or printed work consisting of pages.
public class Book {

    private static final String BOOK_REPRESENTATION_FORMAT = "%-35s %-35s %-10d";

    private final String name;
    private final String author;
    private final int yearPublished;

    Book(String name, String author, int yearPublished) {
        this.name = name;
        this.author = author;
        this.yearPublished = yearPublished;
    }

    String representation() {
        return String.format(BOOK_REPRESENTATION_FORMAT, this.name, this.author, this.yearPublished);
    }

}
