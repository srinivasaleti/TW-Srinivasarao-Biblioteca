package com.tw.controller;

import com.tw.model.Biblioteca;
import com.tw.model.Movie;
import com.tw.view.IO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ReturnMovieCommandTest {

    private ReturnMovieCommand returnMovieCommand;
    private Biblioteca biblioteca;
    private IO io;

    @BeforeEach
    void setUp() {
        this.biblioteca = mock(Biblioteca.class);
        this.io = mock(IO.class);
        this.returnMovieCommand = new ReturnMovieCommand(this.biblioteca, this.io);
    }

    @Test
    void shouldReadMovieNameFromUserToReturnAMovie() {
        String message = "Enter Movie Name To Return::";

        this.returnMovieCommand.execute();

        verify(this.io).print(message);
        verify(this.io).getInput();
    }

    @Test
    void shouldReturnAMovieToLibrary() {
        String movieName = "MovieName";

        when(this.io.getInput()).thenReturn(movieName);
        this.returnMovieCommand.execute();

        verify(this.biblioteca).returnLibraryItem(Movie.class, movieName);
    }

    @Test
    void shouldDisplaySuccessMessageOnSuccessfulReturn() {
        String movieName = "returnMovie";
        String successMessage = "Thank you for returning the movie";

        when(this.io.getInput()).thenReturn(movieName);
        when(this.biblioteca.returnLibraryItem(Movie.class, movieName)).thenReturn(true);
        this.returnMovieCommand.execute();

        verify(this.io).println(successMessage);
    }

    @Test
    void shouldDisplayUnSuccessMessageOnUnSuccessfulReturn() {
        String movieName = "returnMovie";
        String unSuccessMessage = "This is not a valid movie to return";

        when(this.io.getInput()).thenReturn(movieName);
        when(this.biblioteca.returnLibraryItem(Movie.class, movieName)).thenReturn(false);
        this.returnMovieCommand.execute();

        verify(this.io).println(unSuccessMessage);
    }

    @Test
    void shouldReturnNameOfCommandAsReturnMovie() {
        String expected = "Return Movie";
        assertEquals(expected, this.returnMovieCommand.representation());
    }

}
