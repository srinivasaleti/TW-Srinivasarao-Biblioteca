package com.tw.controller;

import com.tw.view.IO;

public class LoginCommand implements Command {

    private static final String REPRESENTATION = "Login";
    private static final String ENTER_LIBRARY_NUMBER = "Enter library number:: ";
    private static final String ENTER_PASSWORD = "Enter password:: ";
    private static final String INVALID_CREDENTIALS_MESSAGE = "Invalid credentials";
    private static final String DEFAULT_USER_NAME = "1234";
    private static final String DEFAULT_PASSWORD = "1234";

    private Menu menuForLibraryUser;
    private final IO io;

    public LoginCommand(IO io, Menu menuForLibraryUser) {
        this.io = io;
        this.menuForLibraryUser = menuForLibraryUser;
    }

    @Override
    public void execute() {
        String userName = readInputFromUser(ENTER_LIBRARY_NUMBER);
        String password = readInputFromUser(ENTER_PASSWORD);
        if (!validUserCredentials(userName, password)) {
            this.io.println(INVALID_CREDENTIALS_MESSAGE);
            return;
        }
        this.menuForLibraryUser.menuSelection();
    }

    @Override
    public String representation() {
        return REPRESENTATION;
    }

    private boolean validUserCredentials(String userName, String password) {
        return userName.equals(DEFAULT_USER_NAME) && password.equals(DEFAULT_PASSWORD);
    }

    private String readInputFromUser(String message) {
        this.io.print(message);
        return this.io.getInput();
    }

}
