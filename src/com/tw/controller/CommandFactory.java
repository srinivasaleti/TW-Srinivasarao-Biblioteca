package com.tw.controller;

import com.tw.model.Biblioteca;
import com.tw.view.IO;

//Represents a factory which gives proper command based on user input
public class CommandFactory {

    private static final String LIST_BOOKS_OPTION = "1";
    private static final String CHECK_OUT_BOOK_OPTION = "2";
    private static final String RETURN_BOOK_OPTION = "3";
    private static final String LIST_MOVIES_OPTION = "4";
    private static final String QUIT_OPTION = "quit";

    private final Biblioteca biblioteca;
    private final IO io;

    public CommandFactory(Biblioteca bibliotecaClass, IO io) {
        this.biblioteca = bibliotecaClass;
        this.io = io;
    }

    public Command getCommand(String option) {
        if (option.equalsIgnoreCase(LIST_BOOKS_OPTION)) {
            return new ListBooksCommand(this.biblioteca, this.io);
        }
        if (option.equalsIgnoreCase(CHECK_OUT_BOOK_OPTION)) {
            return new CheckoutBookCommand(this.biblioteca, this.io);
        }
        if (option.equalsIgnoreCase(RETURN_BOOK_OPTION)) {
            return new ReturnBookCommand(this.biblioteca, this.io);
        }
        if (option.equalsIgnoreCase(LIST_MOVIES_OPTION)) {
            return new ListMoviesCommand(this.biblioteca, this.io);
        }
        if (option.equalsIgnoreCase(QUIT_OPTION)) {
            return new QuitCommand(this.io);
        }
        return new InvalidCommand(this.io);
    }

}
