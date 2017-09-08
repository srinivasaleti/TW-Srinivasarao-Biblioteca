package com.tw;

import java.util.ArrayList;
import java.util.List;

//Represents a library
public class Biblioteca {

    private static final String WELCOME_MESSAGE = "Welcome to Bangalore Public Library";

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
        for (Book book : this.books) {
            this.io.println(book.representation());
        }
    }

}
