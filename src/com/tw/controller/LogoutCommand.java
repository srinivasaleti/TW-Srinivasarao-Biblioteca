package com.tw.controller;

import com.tw.model.Biblioteca;
import com.tw.model.GuestUser;
import com.tw.view.IO;

//Represents a command which is responsible for logout
public class LogoutCommand implements Command {

    private static final String REPRESENTATION = "Logout";
    private static final String THANKS_FOR_YOUR_VALUABLE_TIME = "Thanks for your valuable time";

    private final IO io;
    private final Menu menuForGuestUser;
    private final Biblioteca biblioteca;

    public LogoutCommand(Biblioteca biblioteca, IO io, Menu menuForGuestUser) {
        this.io = io;
        this.menuForGuestUser = menuForGuestUser;
        this.biblioteca = biblioteca;
    }

    @Override
    public void execute() {
        if (this.biblioteca.changeCurrentUser(new GuestUser())) {
            this.io.println(THANKS_FOR_YOUR_VALUABLE_TIME);
            this.menuForGuestUser.menuSelection();
        }
    }

    @Override
    public String representation() {
        return REPRESENTATION;
    }

}
