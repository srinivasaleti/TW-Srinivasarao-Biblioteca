package com.tw.controller;

import com.tw.model.Biblioteca;
import com.tw.model.User;
import com.tw.view.IO;

import java.util.Optional;

//Represents a command which is responsible for login
public class LoginCommand implements Command {

    private static final String REPRESENTATION = "Login";
    private static final String ENTER_LIBRARY_NUMBER = "Enter library number:: ";
    private static final String ENTER_PASSWORD = "Enter password:: ";
    private static final String INVALID_CREDENTIALS_MESSAGE = "Invalid credentials";

    private Menu menuForLibraryUser;
    private final IO io;
    private final Biblioteca biblioteca;
    private boolean loginSuccessful;

    public LoginCommand(Biblioteca biblioteca, IO io, Menu menuForLibraryUser) {
        this.biblioteca = biblioteca;
        this.io = io;
        this.menuForLibraryUser = menuForLibraryUser;
        this.loginSuccessful = false;
    }

    @Override
    public void execute() {
        this.loginSuccessful = false;
        String libraryNo = readInputFromUser(ENTER_LIBRARY_NUMBER);
        String password = readInputFromUser(ENTER_PASSWORD);
        Optional<User> user = this.biblioteca.userWithGivenCredentials(libraryNo, password);
        if (!user.isPresent()) {
            this.io.println(INVALID_CREDENTIALS_MESSAGE);
            return;
        }
        this.menuForLibraryUser.menuSelection();
        this.loginSuccessful = true;
        this.biblioteca.setCurrentUser(user.get());
    }

    @Override
    public String representation() {
        return REPRESENTATION;
    }

    private String readInputFromUser(String message) {
        this.io.print(message);
        return this.io.getInput();
    }

    public boolean loginSuccessful() {
        return this.loginSuccessful;
    }

}
