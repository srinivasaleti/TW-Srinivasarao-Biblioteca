package com.tw;

import java.io.PrintStream;

//Represents a console for input and output
public class ConsoleIO implements IO {

    private final PrintStream out;

    ConsoleIO(PrintStream out) {
        this.out = out;
    }

    @Override
    public void println(String message) {
        this.out.println(message);
    }

}