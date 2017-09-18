package com.tw.controller;

import com.tw.model.Biblioteca;
import com.tw.model.Movie;
import com.tw.view.IO;

//Represents a command which is responsible for list movies available in biblioteca
public class ListMoviesCommand implements Command {

    private static final String NO_MOVIES_AVAILABLE = "No Movies Are Available";
    private static final String FORMAT = "%-35s %-35s %-10s %-10s";
    private static final String MOVIES = "Movies::";
    private static final String HEADER = String.format(FORMAT, "Name", "Director", "Year", "Rating");

    private final Biblioteca biblioteca;
    private final IO io;

    public ListMoviesCommand(Biblioteca biblioteca, IO io) {
        this.biblioteca = biblioteca;
        this.io = io;
    }

    @Override
    public void execute() {
        if (this.biblioteca.isEmpty(Movie.class)) {
            this.io.println(NO_MOVIES_AVAILABLE);
            return;
        }
        this.displayMovies();
    }

    private void displayMovies() {
        this.io.println(MOVIES);
        this.io.println(HEADER);
        String allMoviesRepresentation = this.biblioteca.representationOfAllLibraryItems(Movie.class);
        this.io.println(allMoviesRepresentation);
    }

}
