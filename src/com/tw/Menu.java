package com.tw;

import static com.tw.ConsoleIO.LINE_SEPARATOR;

//Represents menu for biblioteca
public class Menu {

    private static final String WELCOME_MESSAGE = "Welcome to Bangalore Public Library";
    private static final String BOOKS_HEADER = "Books::";
    private static final String HEADER_FORMAT = "%-35s %-35s %-35s";
    private static final String YEAR_PUBLISHED = "YearPublished";
    private static final String AUTHOR = "Author";
    private static final String NAME = "Name";
    private static final String MENU_HEADER = "Menu::";
    private static final String LIST_BOOKS = "1->List Books";
    private static final String LIST_BOOKS_OPTION = "1";
    private static final String ENTER_YOUR_OPTION = "Enter your option::";

    private final IO io;
    private final Biblioteca biblioteca;

    Menu(Biblioteca biblioteca, IO io) {
        this.io = io;
        this.biblioteca = biblioteca;
    }

    public void run() {
        this.displayWelcomeMessage();
        this.displayMenu();
        this.menuSelection();
    }

    private void menuSelection() {
        this.io.print(LINE_SEPARATOR);
        String option = readMenuOptionFromUser();
        if (option.equals(LIST_BOOKS_OPTION)) {
            listAllBooksHeader();
            this.io.println(this.biblioteca.representationOfAllBook());
        }
    }

    private String readMenuOptionFromUser() {
        this.io.print(ENTER_YOUR_OPTION);
        return this.io.getInput();
    }

    private void displayMenu() {
        this.io.println(MENU_HEADER);
        this.io.println(LIST_BOOKS);
    }

    private void displayWelcomeMessage() {
        this.io.println(WELCOME_MESSAGE);
    }

    private void listAllBooksHeader() {
        this.io.print(LINE_SEPARATOR);
        this.io.println(BOOKS_HEADER);
        this.io.println(String.format(HEADER_FORMAT, NAME, AUTHOR, YEAR_PUBLISHED));
    }

}
