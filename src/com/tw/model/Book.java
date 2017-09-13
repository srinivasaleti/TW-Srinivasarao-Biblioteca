package com.tw.model;

//Represent a Written or printed work consisting of pages.
public class Book implements LibraryItem {

    private static final String BOOK_REPRESENTATION_FORMAT = "%-35s %-35s %-10d";

    private final String name;
    private final String author;
    private final int yearPublished;

    public Book(String name, String author, int yearPublished) {
        this.name = name;
        this.author = author;
        this.yearPublished = yearPublished;
    }

    public String representation() {
        return String.format(BOOK_REPRESENTATION_FORMAT, this.name, this.author, this.yearPublished);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        Book that = (Book) obj;
        boolean sameName = this.name.equalsIgnoreCase(that.name);
        boolean sameAuthor = this.author.equalsIgnoreCase(that.author);
        boolean samePublishedYear = this.yearPublished == that.yearPublished;
        return sameName && sameAuthor && samePublishedYear;
    }

    public boolean hasSameName(String name) {
        return this.name.equalsIgnoreCase(name);
    }

}
