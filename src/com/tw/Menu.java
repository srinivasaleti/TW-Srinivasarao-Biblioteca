package com.tw;

import static com.tw.ConsoleIO.LINE_SEPARATOR;

//Represents menu for biblioteca
public class Menu {

    private static final String WELCOME_MESSAGE = "Welcome to Bangalore Public Library";
    private static final String MENU_HEADER = "Menu::";
    private static final String LIST_BOOKS = "1->List Books";
    private static final String ENTER_YOUR_OPTION = "Enter your option::";
    private static final String TYPE_QUIT = "Type quit to Exit application";
    private static final String QUIT = "quit";

    private final IO io;
    private final CommandFactory commandFactory;

    Menu(CommandFactory commandFactory, IO io) {
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
        this.io.print(LINE_SEPARATOR);
        this.io.println(MENU_HEADER);
        this.io.println(LIST_BOOKS);
        this.io.println(TYPE_QUIT);
        this.io.print(LINE_SEPARATOR);
    }

    private void displayWelcomeMessage() {
        this.io.println(WELCOME_MESSAGE);
    }

}
