package com.tw.controller;

import com.tw.model.Biblioteca;
import com.tw.model.Movie;
import com.tw.view.IO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ReturnMovieCommandTest {

    private ReturnMovieCommand returnMovieCommand;
    private Biblioteca biblioteca;
    private IO io;

    @BeforeEach
    void setUp() {
        this.io = mock(IO.class);
        this.biblioteca = mock(Biblioteca.class);
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
    void shouldAskBibliotecaToVerifyGivenMovieIsCheckoutOrNot() {
        String movieName = "Movie Name";

        when(this.io.getInput()).thenReturn(movieName);
        this.returnMovieCommand.execute();

        verify(this.biblioteca).isItemCheckedOut(Movie.class, movieName);
    }

    @Test
    void shouldNotReturnMovieItIsNotYetCheckedOut() {
        String movieName = "MovieName";

        when(this.io.getInput()).thenReturn(movieName);
        when(this.biblioteca.isItemCheckedOut(Movie.class, movieName)).thenReturn(false);
        this.returnMovieCommand.execute();

        assertAll(() -> {
            verify(this.biblioteca, never()).currentUserCheckedOutItem(Movie.class, movieName);
            verify(this.biblioteca, never()).returnLibraryItem(Movie.class, movieName);
        });
    }

    @Test
    void shouldDisplayMovieNotCheckedOutMessageIfMovieIsNotYetCheckedOut() {
        String movieName = "MovieName";

        when(this.io.getInput()).thenReturn(movieName);
        when(this.biblioteca.isItemCheckedOut(Movie.class, movieName)).thenReturn(false);
        this.returnMovieCommand.execute();

        verify(this.io).println("This movie is not yet checked out");
    }

    @Test
    void shouldAskBibliotecaToVerifyCurrentUserCheckedOutMovieOrNot() {
        String movieName = "MovieName";

        when(this.io.getInput()).thenReturn(movieName);
        when(this.biblioteca.isItemCheckedOut(Movie.class, movieName)).thenReturn(true);
        when(this.biblioteca.currentUserCheckedOutItem(Movie.class, movieName)).thenReturn(false);
        this.returnMovieCommand.execute();

        verify(this.biblioteca).currentUserCheckedOutItem(Movie.class, movieName);
    }

    @Test
    void shouldNotReturnMovieIfCurrentUserNotCheckedOutMovie() {
        String movieName = "MovieName";

        when(this.io.getInput()).thenReturn(movieName);
        when(this.biblioteca.isItemCheckedOut(Movie.class, movieName)).thenReturn(true);
        when(this.biblioteca.currentUserCheckedOutItem(Movie.class, movieName)).thenReturn(false);
        this.returnMovieCommand.execute();

        verify(this.biblioteca, never()).returnLibraryItem(Movie.class, movieName);
    }

    @Test
    void shouldDisplayNotValidUserMessageIfCurrentUserNotCheckedOutMovie() {
        String movieName = "MovieName";

        when(this.io.getInput()).thenReturn(movieName);
        when(this.biblioteca.isItemCheckedOut(Movie.class, movieName)).thenReturn(true);
        when(this.biblioteca.currentUserCheckedOutItem(Movie.class, movieName)).thenReturn(false);
        this.returnMovieCommand.execute();

        verify(this.io).println("You are not a valid user to return this movie");
    }

    @Test
    void shouldReturnAMovieToLibrary() {
        String movieName = "MovieName";

        when(this.io.getInput()).thenReturn(movieName);
        when(this.biblioteca.isItemCheckedOut(Movie.class, movieName)).thenReturn(true);
        when(this.biblioteca.currentUserCheckedOutItem(Movie.class, movieName)).thenReturn(true);
        this.returnMovieCommand.execute();

        verify(this.biblioteca).returnLibraryItem(Movie.class, movieName);
    }

    @Test
    void shouldDisplaySuccessMessageOnSuccessfulReturn() {
        String movieName = "returnMovie";
        String successMessage = "Thank you for returning the movie";

        when(this.io.getInput()).thenReturn(movieName);
        when(this.biblioteca.isItemCheckedOut(Movie.class, movieName)).thenReturn(true);
        when(this.biblioteca.currentUserCheckedOutItem(Movie.class, movieName)).thenReturn(true);
        when(this.biblioteca.returnLibraryItem(Movie.class, movieName)).thenReturn(true);
        this.returnMovieCommand.execute();

        verify(this.io).println(successMessage);
    }

    @Test
    void shouldReturnRepresentationOfReturnMovie() {
        String expected = "Return Movie";
        assertEquals(expected, this.returnMovieCommand.representation());
    }

}
