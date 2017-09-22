package com.tw.controller;

import com.tw.model.Biblioteca;
import com.tw.model.User;
import com.tw.view.IO;

//Display user information
public class DisplayUserDetailsCommand implements Command {

    public static final String REPRESENTATION = "User information";
    private Biblioteca biblioteca;
    private IO io;

    public DisplayUserDetailsCommand(Biblioteca biblioteca, IO io) {
        this.biblioteca = biblioteca;
        this.io = io;
    }

    @Override
    public void execute() {
        User user = this.biblioteca.currentUser();
        this.io.println(user.representation());
    }

    @Override
    public String representation() {
        return REPRESENTATION;
    }

}
