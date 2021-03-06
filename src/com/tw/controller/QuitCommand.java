package com.tw.controller;

import com.tw.view.IO;

//Represents quit command for biblioteca
public class QuitCommand implements Command {

    private static final String MESSAGE = "Thank you for your valuable time";
    private static final String REPRESENTATION = "Quit";

    private final IO io;

    QuitCommand(IO io) {
        this.io = io;
    }

    @Override
    public void execute() {
        this.io.println(MESSAGE);
    }

    @Override
    public String representation() {
        return REPRESENTATION;
    }

}
