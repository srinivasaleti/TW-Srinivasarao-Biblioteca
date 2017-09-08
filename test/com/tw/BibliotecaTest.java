package com.tw;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.tw.ConsoleIO.LINE_SEPARATOR;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

}
