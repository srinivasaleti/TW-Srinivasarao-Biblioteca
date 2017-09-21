package com.tw.controller;

import com.tw.model.Biblioteca;
import com.tw.model.Movie;
import com.tw.view.IO;

//Represents a command which is responsible for return a movie
public class ReturnMovieCommand extends ReturnItemAction implements Command {

    private static final String ENTER_MOVIE_NAME_TO_RETURN = "Enter Movie Name To Return::";
    private static final String SUCCESS_MESSAGE = "Thank you for returning the movie";
    private static final String NOT_CHECKED_OUT_MESSAGE = "This movie is not yet checked out";
    private static final String NOT_VALID_USER_MESSAGE = "You are not a valid user to return this movie";
    private static final String REPRESENTATION = "Return Movie";

    public ReturnMovieCommand(Biblioteca biblioteca, IO io) {
        super(biblioteca, io, ENTER_MOVIE_NAME_TO_RETURN, SUCCESS_MESSAGE, NOT_CHECKED_OUT_MESSAGE, NOT_VALID_USER_MESSAGE);
    }

    @Override
    public void execute() {
        super.execute(Movie.class);
    }

    @Override
    public String representation() {
        return REPRESENTATION;
    }

}
