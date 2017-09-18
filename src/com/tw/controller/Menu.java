package com.tw.controller;

import com.tw.view.IO;

import java.util.Arrays;
import java.util.List;

import static com.tw.view.ConsoleIO.LINE_SEPARATOR;

//Represents menu for biblioteca
public class Menu {

    private static final String WELCOME_MESSAGE = "Welcome to Bangalore Public Library";
    private static final String MENU_HEADER = "Menu::";
    private static final String LIST_BOOKS = "1->List Books";
    private static final String CHECKOUT_BOOK = "2->Checkout A Book";
    private static final String ENTER_YOUR_OPTION = "Enter your option::";
    private static final String RETURN_A_BOOK = "3->Return A Book";
    private static final String LIST_MOVIES = "4->List Movies";
    private static final String CHECKOUT_MOVIE = "5->Checkout Movie";
    private static final String RETURN_MOVIE = "6->Return Movie";
    private static final String TYPE_QUIT = "Type quit to Exit application";
    private static final String QUIT = "quit";

    private final IO io;
    private final CommandFactory commandFactory;

    public Menu(CommandFactory commandFactory, IO io) {
        this.io = io;
        this.commandFactory = commandFactory;
    }

    public void run() {
        this.displayWelcomeMessage();
        this.menuSelection();
    }

    private void menuSelection() {
        String option;
        do {
            this.displayMenu();
            option = this.readMenuOptionFromUser();
            Command command = this.commandFactory.getCommand(option);
            command.execute();
        } while (!option.equalsIgnoreCase(QUIT));
    }

    private String readMenuOptionFromUser() {
        this.io.print(ENTER_YOUR_OPTION);
        return this.io.getInput();
    }

    private void displayMenu() {
        List<String> options = Arrays.asList(LIST_BOOKS, CHECKOUT_BOOK, RETURN_A_BOOK,
                LIST_MOVIES, CHECKOUT_MOVIE, RETURN_MOVIE, TYPE_QUIT);
        this.displayMenuHeader();
        options.forEach(this.io::println);
        this.io.println(LINE_SEPARATOR);
    }

    private void displayMenuHeader() {
        this.io.print(LINE_SEPARATOR);
        this.io.println(MENU_HEADER);
    }

    private void displayWelcomeMessage() {
        this.io.println(WELCOME_MESSAGE);
    }

}
