package com.tw;

import com.tw.controller.Command;
import com.tw.controller.LoginCommand;
import com.tw.controller.Menu;
import com.tw.view.IO;

import java.util.List;

//Represents a set up for application
public class BibliotecaApp {

    private static final String WELCOME_MESSAGE = "Welcome to Bangalore Public Library";

    private final IO io;
    private final List<Command> commandsForGuestUser;
    private final LoginCommand loginCommand;
    private final Menu menuForGuestUser;

    BibliotecaApp(IO io, List<Command> commandsForGuestUser, LoginCommand loginCommand, Menu menuForGuestUser) {
        this.io = io;
        this.commandsForGuestUser = commandsForGuestUser;
        this.loginCommand = loginCommand;
        this.menuForGuestUser = menuForGuestUser;
    }

    void run() {
        this.displayWelcomeMessage();
        this.commandsForGuestUser.add(this.loginCommand);
        this.menuForGuestUser.menuSelection();
    }

    private void displayWelcomeMessage() {
        this.io.println(WELCOME_MESSAGE);
    }

}
