package com.tw.controller;

import com.tw.model.Biblioteca;
import com.tw.model.Book;
import com.tw.view.IO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ListBooksCommandTest {

    private ListBooksCommand listBooks;
    private IO io;
    private Biblioteca biblioteca;

    @BeforeEach
    void setUp() {
        this.biblioteca = mock(Biblioteca.class);
        this.io = mock(IO.class);
        this.listBooks = new ListBooksCommand(this.biblioteca, this.io);
    }

    @Test
    void shouldDisplayNoBooksAvailableIfThereAreNoBooksInBiblioteca() {
        String noBooksAvailable = "No Books Available";

        when(this.biblioteca.isEmpty(Book.class)).thenReturn(true);
        this.listBooks.execute();

        verify(this.io).println(noBooksAvailable);
    }

    @Test
    void shouldDisplayHeader() {
        String format = "%-35s %-35s %-35s";
        String books = "Books::";
        String header = String.format(format, "Name", "Author", "YearPublished");

        when(this.biblioteca.isEmpty(Book.class)).thenReturn(false);
        this.listBooks.execute();

        assertAll(() -> {
            verify(this.io).println(books);
            verify(this.io).println(header);
        });
    }

    @Test
    void shouldAskBibliotecaAboutRepresentationOfAllBooks() {
        when(this.biblioteca.isEmpty(Book.class)).thenReturn(false);
        this.listBooks.execute();

        verify(biblioteca).representationOfAllLibraryItems(Book.class);
    }

    @Test
    void shouldDisplayAllBooksInTheBiblioteca() {
        String allBooks = "All book";

        when(this.biblioteca.isEmpty(Book.class)).thenReturn(false);
        when(this.biblioteca.representationOfAllLibraryItems(Book.class)).thenReturn(allBooks);
        this.listBooks.execute();

        verify(this.io).println(allBooks);
    }

    @Test
    void shouldReturnRepresentationOfListBookCommand() {
        String expected = "List Books";
        assertEquals(expected, this.listBooks.representation());
    }

}
