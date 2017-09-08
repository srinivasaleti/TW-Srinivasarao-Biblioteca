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
    private static final String MENU_HEADER = "Menu::";
    private static final String LIST_BOOKS = "1->List Books";
    private static final String LIST_BOOKS_OPTION = "1";
    private static final String ENTER_YOUR_OPTION = "Enter your option::";

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
        this.menuSelection();
    }

    private void displayWelcomeMessage() {
        this.io.println(WELCOME_MESSAGE);
    }

    private String readMenuOptionFromUser() {
        this.io.print(ENTER_YOUR_OPTION);
        return this.io.getInput();
    }

    private void menuSelection() {
        this.displayMenu();
        String option = readMenuOptionFromUser();
        if (option.equals(LIST_BOOKS_OPTION)) {
            this.displayAllBooks();
        }
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

    private void displayMenu() {
        this.io.println(EMPTY);
        this.io.println(MENU_HEADER);
        this.io.println(LIST_BOOKS);
    }

}
