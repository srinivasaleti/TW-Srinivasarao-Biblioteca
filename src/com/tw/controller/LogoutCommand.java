package com.tw.controller;

import com.tw.view.IO;

public class LogoutCommand implements Command {

    public static final String REPRESENTATION = "Logout";
    public static final String THANKS_FOR_YOUR_VALUABLE_TIME = "Thanks for your valuable time";

    private final IO io;
    private final Menu menuForGuestUser;

    public LogoutCommand(IO io, Menu menuForGuestUser) {
        this.io = io;
        this.menuForGuestUser = menuForGuestUser;
    }

    @Override
    public void execute() {
        this.io.println(THANKS_FOR_YOUR_VALUABLE_TIME);
        this.menuForGuestUser.menuSelection();
    }

    @Override
    public String representation() {
        return REPRESENTATION;
    }

}
