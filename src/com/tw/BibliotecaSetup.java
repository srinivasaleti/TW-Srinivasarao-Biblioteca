package com.tw;

import com.tw.controller.*;
import com.tw.model.*;
import com.tw.view.IO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Represents setup for biblioteca
public class BibliotecaSetup {

    List<LibraryItem> listOfLibraryItems() {
        Book halfGirlFriend = new Book("Half GirlFriend", "Chetan Bhagat", 2014);
        Book loveStory = new Book("Love Story", "Erich Segal", 1970);
        Book firstLove = new Book("First Love", "Ivan Turgenev", 1860);
        Movie twilight = new Movie("Twilight", 2009, "HardWicke", "8");
        Movie titanic = new Movie("Titanic", 1997, "Cameron", "9");
        return Arrays.asList(halfGirlFriend, loveStory, firstLove, twilight, titanic);
    }

    List<User> users() {
        User user1 = new LibraryUser("srinu", "123-1234", "Xy2@password", "srinivas.aleti03@gmail.com", "9838918493");
        User user2 = new LibraryUser("ramu", "111-1111", "Ab3@password", "ramu.hello@gmail.com", "9838918493");
        return Arrays.asList(user1, user2);
    }

    ArrayList<Command> commandsAssociatedWithLibraryUser(Biblioteca biblioteca, IO io) {
        ListBooksCommand listBooksCommand = new ListBooksCommand(biblioteca, io);
        ListMoviesCommand listMoviesCommand = new ListMoviesCommand(biblioteca, io);
        CheckoutBookCommand checkoutBookCommand = new CheckoutBookCommand(biblioteca, io);
        CheckoutMovieCommand checkoutMovieCommand = new CheckoutMovieCommand(biblioteca, io);
        ReturnBookCommand returnBookCommand = new ReturnBookCommand(biblioteca, io);
        ReturnMovieCommand returnMovieCommand = new ReturnMovieCommand(biblioteca, io);
        DisplayUserDetailsCommand displayUserDetailsCommand = new DisplayUserDetailsCommand(biblioteca, io);

        List<Command> commands = Arrays.asList(listBooksCommand, checkoutBookCommand,
                returnBookCommand, listMoviesCommand, checkoutMovieCommand, returnMovieCommand, displayUserDetailsCommand);
        return new ArrayList<>(commands);
    }

    ArrayList<Command> commandsAssociatedWithGuestUser(Biblioteca biblioteca, IO io) {
        ListBooksCommand listBooksCommand = new ListBooksCommand(biblioteca, io);
        ListMoviesCommand listMoviesCommand = new ListMoviesCommand(biblioteca, io);

        return new ArrayList<>(Arrays.asList(listBooksCommand, listMoviesCommand));
    }

}
