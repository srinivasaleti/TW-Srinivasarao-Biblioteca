package com.tw.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest {

    @Test
    void shouldReturnRepresentationOfTitanicMovie() {
        String format = "%-35s %-35s %-10d %-10s";
        Movie aMovie = new Movie("Titanic", 1997, "Cameron", "9");
        String name = "Titanic";
        int yearReleased = 1997;
        String director = "Cameron";
        String rating = "9";
        String expected = String.format(format, name, director, yearReleased, rating);

        assertEquals(expected, aMovie.representation());
    }

    @Test
    void shouldReturnRepresentationOfTwilightMovie() {
        String format = "%-35s %-35s %-10d %-10s";
        Movie aMovie = new Movie("Twilight", 2009, "HardWicke", "8");
        String name = "Twilight";
        int yearReleased = 2009;
        String director = "HardWicke";
        String rating = "8";
        String expected = String.format(format, name, director, yearReleased, rating);

        assertEquals(expected, aMovie.representation());
    }

    @Test
    void shouldReturnTrueIfGivenNameIsEqualToMovieName() {
        String givenName = "Titanic";
        String titanic = "Titanic";
        int yearReleased = 1997;
        String director = "Cameron";
        String rating = "9";
        Movie aMovie = new Movie(titanic, yearReleased, director, rating);

        assertTrue(aMovie.hasSameName(givenName));
    }

    @Test
    void shouldReturnFalseIfGivenNameIsNotEqualToMovieName() {
        String givenName = "Love fail";
        String titanic = "Titanic";
        int yearReleased = 1997;
        String director = "Cameron";
        String rating = "9";
        Movie aMovie = new Movie(titanic, yearReleased, director, rating);

        assertFalse(aMovie.hasSameName(givenName));
    }

}
