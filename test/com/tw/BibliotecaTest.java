package com.tw;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
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
        String invalid = "invalid";

        when(this.io.getInput()).thenReturn(invalid);
        this.biblioteca.run();

        verify(this.io).println(welcomeMessage);
    }

    @Test
    void displayHeaderAssociatedWithDisplayingAllBooks() {
        String format = "%-35s %-35s %-35s";
        String books = "Books::";
        String header = String.format(format, "Name", "Author", "YearPublished");
        String listBooks = "1";

        when(this.io.getInput()).thenReturn(listBooks);
        this.biblioteca.run();

        assertAll(() -> {
            verify(this.io).println(books);
            verify(this.io).println(header);
        });
    }

    @Test
    void askRepresentationOfBookInTheBibliotecaToDisplayIt() {
        Book bookInTheLibrary = mock(Book.class);
        List<Book> books = Collections.singletonList(bookInTheLibrary);
        Biblioteca biblioteca = new Biblioteca(books, this.io);
        String listBooks = "1";

        when(this.io.getInput()).thenReturn(listBooks);
        biblioteca.run();

        verify(bookInTheLibrary).representation();
    }

    @Test
    void askRepresentationOfEveryBookInBibliotecaToDisplayAllBooks() {
        Book bookInTheLibrary = mock(Book.class);
        Book anotherBookInTheLibrary = mock(Book.class);
        List<Book> books = Arrays.asList(bookInTheLibrary, anotherBookInTheLibrary);
        Biblioteca biblioteca = new Biblioteca(books, this.io);
        String listBooks = "1";

        when(this.io.getInput()).thenReturn(listBooks);
        biblioteca.run();

        verify(bookInTheLibrary).representation();
        verify(anotherBookInTheLibrary).representation();
    }

    @Test
    void displaySingleBookInBiblioteca() {
        Book aBook = mock(Book.class);
        List<Book> books = Collections.singletonList(aBook);
        Biblioteca biblioteca = new Biblioteca(books, this.io);
        String listBooks = "1";

        when(this.io.getInput()).thenReturn(listBooks);
        biblioteca.run();

        verify(this.io).println(aBook.representation());
    }

    @Test
    void displayAllBooksInTheBiblioteca() {
        Book bookInTheLibrary = mock(Book.class);
        Book anotherBookInTheLibrary = mock(Book.class);
        List<Book> books = Arrays.asList(bookInTheLibrary, anotherBookInTheLibrary);
        Biblioteca biblioteca = new Biblioteca(books, this.io);
        String listBooks = "1";

        when(bookInTheLibrary.representation()).thenReturn("Book1");
        when(anotherBookInTheLibrary.representation()).thenReturn("Book2");
        when(this.io.getInput()).thenReturn(listBooks);
        biblioteca.run();

        verify(this.io).println(bookInTheLibrary.representation());
        verify(this.io).println(anotherBookInTheLibrary.representation());
    }

    @Test
    void displayMenu() {
        String empty = "";
        String menu = "Menu::";
        String listBooks = "1->List Books";
        String invalid = "invalid";

        when(this.io.getInput()).thenReturn(invalid);
        this.biblioteca.run();

        assertAll(() -> {
            verify(this.io).println(menu);
            verify(this.io).println(listBooks);
            verify(this.io).println(empty);
        });
    }

    @Test
    void readMenuOptionFromUser() {
        String enterMenuOption = "Enter your option::";
        String listBooks = "1";

        when(this.io.getInput()).thenReturn(listBooks);
        this.biblioteca.run();

        verify(this.io).print(enterMenuOption);
        verify(this.io).getInput();
    }

    @Test
    void shouldNotDisplayAllBooksIfUserNotSelectedListBooksOption() {
        String format = "%-35s %-35s %-35s";
        String books = "Books::";
        String header = String.format(format, "Name", "Author", "YearPublished");
        String notListBooksOption = "other";

        when(this.io.getInput()).thenReturn(notListBooksOption);
        this.biblioteca.run();

        assertAll(() -> {
            verify(this.io, never()).println(books);
            verify(this.io, never()).println(header);
        });
    }

}
