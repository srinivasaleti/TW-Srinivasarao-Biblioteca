package com.tw;

import com.tw.controller.*;
import com.tw.model.*;
import com.tw.view.ConsoleIO;
import com.tw.view.IO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

//Represents entry point for application
public class App {

    public static void main(String[] args) {
        IO io = new ConsoleIO(System.out, new Scanner(System.in));
        Biblioteca biblioteca = new Biblioteca(listOfLibraryItems(), seedUsers());

        List<Command> commandsForGuestUser = seedCommandsForGuestUser(biblioteca, io);
        List<Command> commandsForLibraryMember = seedCommandsForLibraryMember(biblioteca, io);

        CommandFactory commandFactoryGuestUser = new CommandFactory(commandsForGuestUser, io);
        CommandFactory commandFactoryLibraryUser = new CommandFactory(commandsForLibraryMember, io);

        Menu menuForLibraryUser = new Menu(commandFactoryLibraryUser, io);
        LoginCommand loginCommand = new LoginCommand(biblioteca, io, menuForLibraryUser);
        Menu menuForGuestUser = new Menu(commandFactoryGuestUser, io);
        LogoutCommand logoutCommand = new LogoutCommand(io, menuForGuestUser);

        BibliotecaApp bibliotecaApp = new BibliotecaApp(io, commandsForGuestUser, commandsForLibraryMember, loginCommand, logoutCommand, menuForGuestUser);
        bibliotecaApp.run();
    }

    private static List<LibraryItem> listOfLibraryItems() {
        Book halfGirlFriend = new Book("Half GirlFriend", "Chetan Bhagat", 2014);
        Book loveStory = new Book("Love Story", "Erich Segal", 1970);
        Book firstLove = new Book("First Love", "Ivan Turgenev", 1860);
        Movie twilight = new Movie("Twilight", 2009, "HardWicke", "8");
        Movie titanic = new Movie("Titanic", 1997, "Cameron", "9");
        return Arrays.asList(halfGirlFriend, loveStory, firstLove, twilight, titanic);
    }

    private static ArrayList<Command> seedCommandsForLibraryMember(Biblioteca biblioteca, IO io) {
        ListBooksCommand listBooksCommand = new ListBooksCommand(biblioteca, io);
        ListMoviesCommand listMoviesCommand = new ListMoviesCommand(biblioteca, io);
        CheckoutBookCommand checkoutBookCommand = new CheckoutBookCommand(biblioteca, io);
        CheckoutMovieCommand checkoutMovieCommand = new CheckoutMovieCommand(biblioteca, io);
        ReturnBookCommand returnBookCommand = new ReturnBookCommand(biblioteca, io);
        ReturnMovieCommand returnMovieCommand = new ReturnMovieCommand(biblioteca, io);

        List<Command> commands = Arrays.asList(listBooksCommand, checkoutBookCommand,
                returnBookCommand, listMoviesCommand, checkoutMovieCommand, returnMovieCommand);
        return new ArrayList<>(commands);
    }

    private static ArrayList<Command> seedCommandsForGuestUser(Biblioteca biblioteca, IO io) {
        ListBooksCommand listBooksCommand = new ListBooksCommand(biblioteca, io);
        ListMoviesCommand listMoviesCommand = new ListMoviesCommand(biblioteca, io);
        return new ArrayList<>(Arrays.asList(listBooksCommand, listMoviesCommand));
    }

    private static List<User> seedUsers() {
        User user1 = new LibraryUser("srinu", "123-1234", "Xy2@password", "srinivas.aleti03@gmail.com", "9838918493");
        User user2 = new LibraryUser("ramu", "111-1111", "Ab3@password", "srinivas.aleti03@gmail.com", "9838918493");

        return Arrays.asList(user1, user2);
    }

}
