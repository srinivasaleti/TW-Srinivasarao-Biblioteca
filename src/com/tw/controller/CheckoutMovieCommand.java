package com.tw.controller;

import com.tw.model.Biblioteca;
import com.tw.model.Movie;
import com.tw.view.IO;

//Represents a command which is responsible for checkout a movie from biblioteca
public class CheckoutMovieCommand extends CheckoutItemAction implements Command {

    private static final String NO_MOVIES_AVAILABLE = "No Movies Available";
    private static final String ENTER_MOVIE_NAME_TO_CHECKOUT = "Enter movie name to checkout::";
    private static final String SUCCESS_MESSAGE = "Thank You Enjoy The Movie";
    private static final String UN_SUCCESS_MESSAGE = "This movie is not available";
    private static final String REPRESENTATION = "Checkout Movie";

    public CheckoutMovieCommand(Biblioteca biblioteca, IO io) {
        super(biblioteca, io, NO_MOVIES_AVAILABLE, ENTER_MOVIE_NAME_TO_CHECKOUT, SUCCESS_MESSAGE, UN_SUCCESS_MESSAGE);
    }

    public void execute() {
        super.execute(Movie.class);
    }

    @Override
    public String representation() {
        return REPRESENTATION;
    }

}
