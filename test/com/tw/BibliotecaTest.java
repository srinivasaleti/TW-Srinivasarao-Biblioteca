package com.tw;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.tw.ConsoleIO.LINE_SEPARATOR;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class BibliotecaTest {

    @Test
    void expectedNothingWhenThereAreNoBooksInLibrary() {
        Biblioteca biblioteca = new Biblioteca(null);
        String nothing = "";

        assertEquals(nothing, biblioteca.representationOfAllBook());
    }

    @Test
    void askRepresentationOfBookInTheBibliotecaToDisplayIt() {
        Book bookInTheLibrary = mock(Book.class);
        List<Book> books = Collections.singletonList(bookInTheLibrary);
        Biblioteca biblioteca = new Biblioteca(books);

        biblioteca.representationOfAllBook();

        verify(bookInTheLibrary).representation();
    }

    @Test
    void askRepresentationOfEveryBookInBibliotecaToDisplayAllBooks() {
        Book bookInTheLibrary = mock(Book.class);
        Book anotherBookInTheLibrary = mock(Book.class);
        List<Book> books = Arrays.asList(bookInTheLibrary, anotherBookInTheLibrary);
        Biblioteca biblioteca = new Biblioteca(books);

        biblioteca.representationOfAllBook();

        verify(bookInTheLibrary).representation();
        verify(anotherBookInTheLibrary).representation();
    }

    @Test
    void expectedRepresentationOfAllBooksInBiblioteca() {
        Book aBook = mock(Book.class);
        Book anotherBook = mock(Book.class);
        List<Book> books = Arrays.asList(aBook, anotherBook);
        Biblioteca biblioteca = new Biblioteca(books);
        String aBookRepresentation = "Book1";
        String anotherBookRepresentation = "Book2";
        String expected = aBookRepresentation + LINE_SEPARATOR + anotherBookRepresentation;

        when(aBook.representation()).thenReturn(aBookRepresentation);
        when(anotherBook.representation()).thenReturn(anotherBookRepresentation);
        biblioteca.representationOfAllBook();

        assertEquals(expected, biblioteca.representationOfAllBook());
    }

    @Test
    void shouldCheckoutABookFromBibliotecaIfItIsAvailable() {
        Book aBook = new Book("book1", "author1", 1996);
        Book anotherBook = new Book("book2", "author2", 1996);
        List<Book> books = Arrays.asList(aBook, anotherBook);
        Biblioteca biblioteca = new Biblioteca(books);

        assertEquals(Optional.of(aBook), biblioteca.checkoutABook("book1"));
    }

    @Test
    void shouldNotCheckoutABookIfItIsNotAvailableInBiblioteca() {
        Book aBook = new Book("book1", "author1", 1996);
        Book anotherBook = new Book("book2", "author2", 1996);
        List<Book> books = Arrays.asList(aBook, anotherBook);
        Biblioteca biblioteca = new Biblioteca(books);

        assertEquals(Optional.empty(), biblioteca.checkoutABook("book3"));
    }

    @Test
    void shouldRemoveBookFromAllBooks() {
        Book aBook = new Book("book1", "author1", 1996);
        Book anotherBook = new Book("book2", "author2", 1996);
        List<Book> books = Arrays.asList(aBook, anotherBook);
        Biblioteca biblioteca = new Biblioteca(books);

        biblioteca.checkoutABook("book1");

        assertEquals(anotherBook.representation(), biblioteca.representationOfAllBook());
    }

    @Test
    void shouldReturnTrueIfNoBooksInBiblioteca() {
        Biblioteca biblioteca = new Biblioteca(null);

        assertTrue(biblioteca.isEmpty());
    }

    @Test
    void shouldReturnFalseIfBooksAvailableInBiblioteca() {
        Book aBook = new Book("book1", "author1", 1996);
        Book anotherBook = new Book("book2", "author2", 1996);
        List<Book> books = Arrays.asList(aBook, anotherBook);
        Biblioteca biblioteca = new Biblioteca(books);

        assertFalse(biblioteca.isEmpty());
    }

}
