package com.tw;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
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
    void shouldDisplayNoBooksAvailableIfBibliotecaIsEmpty() {
        String noBooksAvailable = "No Books Available";

        when(this.biblioteca.isEmpty()).thenReturn(true);
        this.listBooks.execute();

        verify(this.io).println(noBooksAvailable);
    }

    @Test
    void displayHeaderAssociatedWithDisplayingAllBooks() {
        String format = "%-35s %-35s %-35s";
        String books = "Books::";
        String header = String.format(format, "Name", "Author", "YearPublished");

        when(this.biblioteca.isEmpty()).thenReturn(false);
        this.listBooks.execute();

        assertAll(() -> {
            verify(this.io).println(books);
            verify(this.io).println(header);
        });
    }

    @Test
    void askBibliotecaAboutRepresentationOfAllBooksToDisplayAllBooksInIt() {
        when(this.biblioteca.isEmpty()).thenReturn(false);
        this.listBooks.execute();

        verify(biblioteca).representationOfAllBook();
    }

    @Test
    void displayAllBooksInTheBiblioteca() {
        String allBooks = "All book";

        when(this.biblioteca.isEmpty()).thenReturn(false);
        when(this.biblioteca.representationOfAllBook()).thenReturn(allBooks);
        this.listBooks.execute();

        verify(this.io).println(allBooks);
    }

}
