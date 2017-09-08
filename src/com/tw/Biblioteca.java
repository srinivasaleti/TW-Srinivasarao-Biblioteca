package com.tw;

//Represents a library
public class Biblioteca {

    private static final String WELCOME_MESSAGE = "Welcome to Bangalore Public Library";

    private final IO io;

    public Biblioteca(IO io) {
        this.io = io;
    }

    public void run() {
        this.displayWelcomeMessage();
    }

    private void displayWelcomeMessage() {
        this.io.println(WELCOME_MESSAGE);
    }

}
