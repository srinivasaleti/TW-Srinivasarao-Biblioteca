package com.tw;

//Represents a factory which gives proper command based on user input
public class CommandFactory {

    private static final String LIST_BOOKS_OPTION = "1";
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
        if (option.equalsIgnoreCase(QUIT_OPTION)) {
            return new QuitCommand(this.io);
        }
        return new InvalidCommand(this.io);
    }

}
