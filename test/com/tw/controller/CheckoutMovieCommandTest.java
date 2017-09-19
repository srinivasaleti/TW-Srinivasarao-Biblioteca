package com.tw.controller;

import com.tw.model.Biblioteca;
import com.tw.model.Movie;
import com.tw.view.IO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CheckoutMovieCommandTest {

    private Biblioteca biblioteca;
    private IO io;
    private CheckoutMovieCommand checkoutMovieCommand;

    @BeforeEach
    void setUp() {
        this.io = mock(IO.class);
        this.biblioteca = mock(Biblioteca.class);
        this.checkoutMovieCommand = new CheckoutMovieCommand(this.biblioteca, this.io);
    }

    @Test
    void shouldAskBibliotecaToVerifyWhetherMoviesAreAvailableOrNot() {
        when(this.biblioteca.isEmpty(Movie.class)).thenReturn(true);
        this.checkoutMovieCommand.execute();

        verify(this.biblioteca).isEmpty(Movie.class);
    }

    @Test
    void shouldDisplayNoMoviesAvailableIfBibliotecaHasNoMovies() {
        String message = "No Movies Available";
        when(this.biblioteca.isEmpty(Movie.class)).thenReturn(true);
        this.checkoutMovieCommand.execute();

        verify(this.io).println(message);
    }

    @Test
    void shouldReadMovieNameFromUserToCheckout() {
        String name = "movieName";

        when(this.biblioteca.isEmpty(Movie.class)).thenReturn(false);
        when(this.io.getInput()).thenReturn(name);
        when(this.biblioteca.checkoutALibraryItem(Movie.class, name)).thenReturn(Optional.empty());
        this.checkoutMovieCommand.execute();

        verify(this.io).print("Enter movie name to checkout::");
        verify(this.io).getInput();
    }

    @Test
    void shouldAskBibliotecaToCheckoutAMovie() {
        String name = "movieName";

        when(this.biblioteca.isEmpty(Movie.class)).thenReturn(false);
        when(this.io.getInput()).thenReturn(name);
        when(this.biblioteca.checkoutALibraryItem(Movie.class, name)).thenReturn(Optional.empty());
        this.checkoutMovieCommand.execute();

        verify(this.biblioteca).checkoutALibraryItem(Movie.class, name);
    }

    @Test
    void shouldDisplayMessageAssociatedWithSuccessfulCheckout() {
        String name = "movie1";
        String successMessage = "Thank You Enjoy The Movie";
        Movie movie = new Movie("movie1", 1234, "author1", "1");

        when(this.biblioteca.isEmpty(Movie.class)).thenReturn(false);
        when(this.io.getInput()).thenReturn(name);
        when(this.biblioteca.checkoutALibraryItem(Movie.class, name)).thenReturn(Optional.of(movie));
        this.checkoutMovieCommand.execute();

        verify(this.io).println(successMessage);
    }

    @Test
    void shouldDisplayMessageAssociatedWithUnSuccessfulCheckout() {
        String name = "movieName";
        String unSuccessMessage = "This movie is not available";

        when(this.biblioteca.isEmpty(Movie.class)).thenReturn(false);
        when(this.io.getInput()).thenReturn(name);
        when(this.biblioteca.checkoutALibraryItem(Movie.class, name)).thenReturn(Optional.empty());
        this.checkoutMovieCommand.execute();

        verify(this.io).println(unSuccessMessage);
    }

    @Test
    void shouldReturnsCheckoutBookAsNameOfCommand() {
        String expected = "Checkout Movie";
        assertEquals(expected, this.checkoutMovieCommand.representation());
    }

}
