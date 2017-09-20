package com.tw.controller;

import com.tw.model.Biblioteca;
import com.tw.model.User;
import com.tw.view.IO;

import java.util.Optional;

public class LoginCommand implements Command {

    private static final String REPRESENTATION = "Login";
    private static final String ENTER_LIBRARY_NUMBER = "Enter library number:: ";
    private static final String ENTER_PASSWORD = "Enter password:: ";
    private static final String INVALID_CREDENTIALS_MESSAGE = "Invalid credentials";

    private Menu menuForLibraryUser;
    private final IO io;
    private final Biblioteca biblioteca;

    public LoginCommand(Biblioteca biblioteca, IO io, Menu menuForLibraryUser) {
        this.biblioteca = biblioteca;
        this.io = io;
        this.menuForLibraryUser = menuForLibraryUser;
    }

    @Override
    public void execute() {
        String libraryNo = readInputFromUser(ENTER_LIBRARY_NUMBER);
        String password = readInputFromUser(ENTER_PASSWORD);
        Optional<User> user = this.biblioteca.userWithGivenCredentials(libraryNo, password);
        if (!user.isPresent()) {
            this.io.println(INVALID_CREDENTIALS_MESSAGE);
            return;
        }
        this.menuForLibraryUser.menuSelection();
    }

    @Override
    public String representation() {
        return REPRESENTATION;
    }

    private String readInputFromUser(String message) {
        this.io.print(message);
        return this.io.getInput();
    }

}
