package com.tw.controller;

import com.tw.model.Biblioteca;
import com.tw.model.LibraryItem;
import com.tw.model.Movie;
import com.tw.view.IO;

import java.util.Optional;

public class CheckoutMovieCommand implements Command {

    private static final String NO_MOVIES_AVAILABLE = "No Movies Available";
    private static final String ENTER_MOVIE_NAME_TO_CHECKOUT = "Enter movie name to checkout::";
    private static final String SUCCESS_MESSAGE = "Thank You Enjoy The Movie";
    private static final String UN_SUCCESS_MESSAGE = "This movie is not available";

    private Biblioteca biblioteca;
    private IO io;

    public CheckoutMovieCommand(Biblioteca biblioteca, IO io) {
        this.biblioteca = biblioteca;
        this.io = io;
    }

    public void execute() {
        if (this.biblioteca.isEmpty(Movie.class)) {
            this.io.println(NO_MOVIES_AVAILABLE);
            return;
        }
        this.checkout();
    }

    private void checkout() {
        Optional<LibraryItem> movie = this.biblioteca.checkoutALibraryItem(Movie.class, this.readMovieName());
        if (movie.isPresent()) {
            this.io.println(SUCCESS_MESSAGE);
            return;
        }
        this.io.println(UN_SUCCESS_MESSAGE);
    }

    private String readMovieName() {
        this.io.print(ENTER_MOVIE_NAME_TO_CHECKOUT);
        return this.io.getInput();
    }

}
