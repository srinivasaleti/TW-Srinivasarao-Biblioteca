package com.tw.controller;

import com.tw.model.Biblioteca;
import com.tw.model.Movie;
import com.tw.view.IO;

//Represents a command which is responsible for return a movie
public class ReturnMovieCommand extends ReturnItem implements Command {

    private static final String ENTER_MOVIE_NAME_TO_RETURN = "Enter Movie Name To Return::";
    private static final String SUCCESS_MESSAGE = "Thank you for returning the movie";
    private static final String UN_SUCCESS_MESSAGE = "This is not a valid movie to return";

    public ReturnMovieCommand(Biblioteca biblioteca, IO io) {
        super(biblioteca, io, ENTER_MOVIE_NAME_TO_RETURN, SUCCESS_MESSAGE, UN_SUCCESS_MESSAGE);
    }

    @Override
    public void execute() {
        super.execute(Movie.class);
    }

}
