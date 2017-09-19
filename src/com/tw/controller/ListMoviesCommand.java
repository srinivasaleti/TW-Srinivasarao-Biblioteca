package com.tw.controller;

import com.tw.model.Biblioteca;
import com.tw.model.Movie;
import com.tw.view.IO;

//Represents a command which is responsible for list movies available in biblioteca
public class ListMoviesCommand extends ListItemsAction implements Command {

    private static final String NO_MOVIES_AVAILABLE = "No Movies Are Available";
    private static final String FORMAT = "%-35s %-35s %-10s %-10s";
    private static final String MOVIES = "Movies::";
    private static final String HEADER = String.format(FORMAT, "Name", "Director", "Year", "Rating");
    private static final String REPRESENTATION = "List Movies";

    public ListMoviesCommand(Biblioteca biblioteca, IO io) {
        super(biblioteca, io, NO_MOVIES_AVAILABLE, MOVIES, HEADER);
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
