package com.tw.controller;

import com.tw.model.Biblioteca;
import com.tw.model.Movie;
import com.tw.view.IO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class ListMoviesCommandTest {

    private Biblioteca biblioteca;
    private IO io;
    private ListMoviesCommand listMoviesCommand;

    @BeforeEach
    void setUp() {
        this.biblioteca = mock(Biblioteca.class);
        this.io = mock(IO.class);
        this.listMoviesCommand = new ListMoviesCommand(this.biblioteca, this.io);
    }

    @Test
    void shouldAskBibliotecaWhetherItHasMoviesOrNot() {
        when(this.biblioteca.isEmpty(Movie.class)).thenReturn(true);
        this.listMoviesCommand.execute();

        verify(this.biblioteca).isEmpty(Movie.class);
    }

    @Test
    void shouldDisplayNoMoviesAvailableIfThereAreNoMoviesInBiblioteca() {
        String noMoviesAvailable = "No Movies Are Available";

        when(this.biblioteca.isEmpty(Movie.class)).thenReturn(true);
        this.listMoviesCommand.execute();

        verify(this.io).println(noMoviesAvailable);
    }

    @Test
    void shouldDisplayHeaderIfMoviesAvailableInBiblioteca() {
        String format = "%-35s %-35s %-10s %-10s";
        String movies = "Movies::";
        String header = String.format(format, "Name", "Director", "Year", "Rating");

        when(this.biblioteca.isEmpty(Movie.class)).thenReturn(false);
        this.listMoviesCommand.execute();

        verify(this.io).println(movies);
        verify(this.io).println(header);
    }

    @Test
    void shouldAskBibliotecaAboutAllMoviesRepresentationIfItHasMovies() {
        when(this.biblioteca.isEmpty(Movie.class)).thenReturn(false);
        this.listMoviesCommand.execute();

        verify(this.biblioteca).representationOfAllLibraryItems(Movie.class);
    }

    @Test
    void shouldDisplayAllMoviesInBibliotecaIfItHasMovies() {
        String moviesRepresentation = "All Movies representation";

        when(this.biblioteca.isEmpty(Movie.class)).thenReturn(false);
        when(this.biblioteca.representationOfAllLibraryItems(Movie.class)).thenReturn(moviesRepresentation);
        this.listMoviesCommand.execute();

        verify(this.io).println(moviesRepresentation);
    }

}
