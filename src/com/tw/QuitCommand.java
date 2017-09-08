package com.tw;

//Represents quit command for biblioteca
public class QuitCommand implements Command {

    private static final String MESSAGE = "Thank you for your valuable time";
    private final IO io;

    public QuitCommand(IO io) {
        this.io = io;
    }

    @Override
    public void execute() {
        this.io.println(MESSAGE);
    }

}
