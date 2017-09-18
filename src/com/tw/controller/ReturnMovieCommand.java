package com.tw.controller;

import com.tw.model.Biblioteca;
import com.tw.model.Movie;
import com.tw.view.IO;

//Represents a command which is responsible for return a movie
public class ReturnMovieCommand implements Command {

    private static final String ENTER_MOVIE_NAME_TO_RETURN = "Enter Movie Name To Return::";
    private static final String SUCCESS_MESSAGE = "Thank you for returning the movie";
    private static final String UN_SUCCESS_MESSAGE = "This is not a valid movie to return";

    private final Biblioteca biblioteca;
    private final IO io;

    public ReturnMovieCommand(Biblioteca biblioteca, IO io) {
        this.biblioteca = biblioteca;
        this.io = io;
    }

    @Override
    public void execute() {
        String movieName = readMovieName();
        boolean movieReturnToLibrary = this.biblioteca.returnLibraryItem(Movie.class, movieName);
        displayMessageBasedOn(movieReturnToLibrary);
    }

    private String readMovieName() {
        this.io.print(ENTER_MOVIE_NAME_TO_RETURN);
        return this.io.getInput();
    }

    private void displayMessageBasedOn(boolean movieReturnToLibrary) {
        if (movieReturnToLibrary) {
            this.io.println(SUCCESS_MESSAGE);
            return;
        }
        this.io.println(UN_SUCCESS_MESSAGE);
    }

}
