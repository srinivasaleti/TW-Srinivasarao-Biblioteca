package com.tw.controller;

import com.tw.view.IO;

import static com.tw.view.ConsoleIO.LINE_SEPARATOR;

//Represents menu for biblioteca
public class Menu {

    private static final String MENU_HEADER = "Menu::";
    private static final String ENTER_YOUR_OPTION = "Enter your option::";
    private static final String QUIT = "quit";

    private final IO io;
    private final CommandFactory commandFactory;

    public Menu(CommandFactory commandFactory, IO io) {
        this.io = io;
        this.commandFactory = commandFactory;
    }

    public void menuSelection() {
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
        displayMenuHeader();
        String menu = this.commandFactory.representationOfMenuBasedOnCommands();
        this.io.println(menu);
    }

    private void displayMenuHeader() {
        this.io.print(LINE_SEPARATOR);
        this.io.println(MENU_HEADER);
    }

}
