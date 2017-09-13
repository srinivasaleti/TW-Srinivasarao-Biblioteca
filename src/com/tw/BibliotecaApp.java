package com.tw;

import com.tw.controller.CommandFactory;
import com.tw.controller.Menu;
import com.tw.model.Biblioteca;
import com.tw.model.Book;
import com.tw.model.LibraryItem;
import com.tw.model.Movie;
import com.tw.view.ConsoleIO;
import com.tw.view.IO;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

//Represents entry point for application
public class BibliotecaApp {

    public static void main(String[] args) {
        IO consoleIO = new ConsoleIO(System.out, new Scanner(System.in));
        Biblioteca biblioteca = new Biblioteca(listOfLibraryItems());
        CommandFactory commandFactory = new CommandFactory(biblioteca, consoleIO);
        Menu menu = new Menu(commandFactory, consoleIO);
        menu.run();
    }

    private static List<LibraryItem> listOfLibraryItems() {
        Book halfGirlFriend = new Book("Half GirlFriend", "Chetan Bhagat", 2014);
        Book loveStory = new Book("Love Story", "Erich Segal", 1970);
        Book firstLove = new Book("First Love", "Ivan Turgenev", 1860);
        Movie twilight = new Movie("Twilight", 2009, "HardWicke", "8");
        Movie titanic = new Movie("Titanic", 1997, "Cameron", "9");
        return Arrays.asList(halfGirlFriend, loveStory, firstLove, twilight, titanic);
    }

}
