package com.tw;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

class BibliotecaTest {

    private IO io;
    private Biblioteca biblioteca;

    @BeforeEach
    void setUp() {
        this.io = mock(IO.class);
        this.biblioteca = new Biblioteca(null, this.io);
    }

    @Test
    void displayWelcomeMessage() {
        String welcomeMessage = "Welcome to Bangalore Public Library";

        this.biblioteca.run();

        verify(this.io).println(welcomeMessage);
    }

    @Test
    void askRepresentationOfBookInTheBibliotecaToDisplayIt() {
        Book bookInTheLibrary = mock(Book.class);
        List<Book> books = Collections.singletonList(bookInTheLibrary);
        Biblioteca biblioteca = new Biblioteca(books, this.io);

        biblioteca.run();

        verify(bookInTheLibrary).representation();
    }

    @Test
    void askRepresentationOfEveryBookInBibliotecaToDisplayAllBooks() {
        Book bookInTheLibrary = mock(Book.class);
        Book anotherBookInTheLibrary = mock(Book.class);
        List<Book> books = Arrays.asList(bookInTheLibrary, anotherBookInTheLibrary);
        Biblioteca biblioteca = new Biblioteca(books, this.io);

        biblioteca.run();

        verify(bookInTheLibrary).representation();
        verify(anotherBookInTheLibrary).representation();
    }

    @Test
    void displaySingleBookInBiblioteca() {
        Book aBook = mock(Book.class);
        List<Book> books = Collections.singletonList(aBook);
        Biblioteca biblioteca = new Biblioteca(books, this.io);

        biblioteca.run();

        verify(this.io).println(aBook.representation());
    }

    @Test
    void displayAllBooksInTheBiblioteca() {
        Book bookInTheLibrary = mock(Book.class);
        Book anotherBookInTheLibrary = mock(Book.class);
        List<Book> books = Arrays.asList(bookInTheLibrary, anotherBookInTheLibrary);
        Biblioteca biblioteca = new Biblioteca(books, this.io);

        when(bookInTheLibrary.representation()).thenReturn("Book1");
        when(anotherBookInTheLibrary.representation()).thenReturn("Book2");

        biblioteca.run();

        verify(this.io).println(bookInTheLibrary.representation());
        verify(this.io).println(anotherBookInTheLibrary.representation());
    }

}
