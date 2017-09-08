package com.tw;

//Represents entry point for application
public class BibliotecaApp {

    public static void main(String[] args) {
        IO consoleIO = new ConsoleIO(System.out);
        Biblioteca biblioteca = new Biblioteca(consoleIO);
        biblioteca.run();
    }

}
