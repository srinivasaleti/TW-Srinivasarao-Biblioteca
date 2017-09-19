package com.tw;

import com.tw.controller.*;
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
        CommandFactory commandFactory = new CommandFactory(seedCommands(biblioteca, consoleIO), consoleIO);
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

    private static List<Command> seedCommands(Biblioteca biblioteca, IO io) {
        ListBooksCommand listBooksCommand = new ListBooksCommand(biblioteca, io);
        ListMoviesCommand listMoviesCommand = new ListMoviesCommand(biblioteca, io);
        CheckoutBookCommand checkoutBookCommand = new CheckoutBookCommand(biblioteca, io);
        CheckoutMovieCommand checkoutMovieCommand = new CheckoutMovieCommand(biblioteca, io);
        ReturnBookCommand returnBookCommand = new ReturnBookCommand(biblioteca, io);
        ReturnMovieCommand returnMovieCommand = new ReturnMovieCommand(biblioteca, io);

        return Arrays.asList(listBooksCommand, checkoutBookCommand, returnBookCommand, listMoviesCommand, checkoutMovieCommand, returnMovieCommand);
    }

}
