package com.tw;

import java.util.ArrayList;
import java.util.List;

//Represents a library
public class Biblioteca {

    private static final String WELCOME_MESSAGE = "Welcome to Bangalore Public Library";
    private static final String BOOKS_HEADER = "Books::";
    private static final String HEADER_FORMAT = "%-35s %-35s %-35s";
    private static final String YEAR_PUBLISHED = "YearPublished";
    private static final String AUTHOR = "Author";
    private static final String NAME = "Name";
    private static final String EMPTY = "";

    private final IO io;
    private List<Book> books;

    public Biblioteca(List<Book> books, IO io) {
        this.io = io;
        if (books == null) {
            this.books = new ArrayList<>();
        } else {
            this.books = books;
        }
    }

    public void run() {
        this.displayWelcomeMessage();
        this.displayAllBooks();
    }

    private void displayWelcomeMessage() {
        this.io.println(WELCOME_MESSAGE);
    }

    private void displayAllBooks() {
        this.listAllBooksHeader();
        for (Book book : this.books) {
            this.io.println(book.representation());
        }
    }

    private void listAllBooksHeader() {
        this.io.println(EMPTY);
        this.io.println(BOOKS_HEADER);
        this.io.println(String.format(HEADER_FORMAT, NAME, AUTHOR, YEAR_PUBLISHED));
    }

}
