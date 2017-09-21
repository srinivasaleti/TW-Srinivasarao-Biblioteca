package com.tw.controller;

import com.tw.view.IO;

//Represents invalid command for biblioteca
public class InvalidCommand implements Command {

    private static final String SELECT_VALID_OPTION = "Select a valid option!";
    private static final String EMPTY = "";

    private final IO io;

    InvalidCommand(IO io) {
        this.io = io;
    }

    @Override
    public void execute() {
        this.io.println(SELECT_VALID_OPTION);
    }

    @Override
    public String representation() {
        return EMPTY;
    }

}
