package com.tw;

import java.util.Arrays;
import java.util.List;

//Represents entry point for application
public class BibliotecaApp {

    public static void main(String[] args) {
        IO consoleIO = new ConsoleIO(System.out);
        Biblioteca biblioteca = new Biblioteca(listOfBooks(), consoleIO);
        biblioteca.run();
    }

    private static List<Book> listOfBooks() {
        Book halfGirlFriend = new Book("Half GirlFriend", "Chetan Bhagat", 2014);
        Book loveStory = new Book("Love Story", "Erich Segal", 1970);
        Book firstLove = new Book("First Love", "Ivan Turgenev", 1860);
        return Arrays.asList(halfGirlFriend, loveStory, firstLove);
    }

}
