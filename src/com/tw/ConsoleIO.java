package com.tw;

import java.io.PrintStream;
import java.util.Scanner;

//Represents a console for input and output
public class ConsoleIO implements IO {

    public static final String LINE_SEPARATOR = "\n";

    private final PrintStream out;
    private final Scanner scanner;

    ConsoleIO(PrintStream out, Scanner scanner) {
        this.out = out;
        this.scanner = scanner;
    }

    @Override
    public void println(String message) {
        this.out.println(message);
    }

    @Override
    public void print(String message) {
        this.out.print(message);
    }

    @Override
    public String getInput() {
        return this.scanner.nextLine();
    }

}