package com.tw.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.tw.view.ConsoleIO.LINE_SEPARATOR;
import static org.junit.jupiter.api.Assertions.*;

class BibliotecaTest {

    @Test
    void expectedNothingWhenThereAreNoLibraryItemsInLibrary() {
        Biblioteca biblioteca = new Biblioteca(null);
        String nothing = "";

        assertEquals(nothing, biblioteca.representationOfAllLibraryItems(Book.class));
    }

    @Test
    void shouldReturnOnlyRepresentationOfOnlyBooksInBiblioteca() {
        Book aBook = new Book("book1", "author1", 1996);
        Book anotherBook = new Book("book2", "author2", 1996);
        Movie aMovie = new Movie("Movie1", 1996, "Movie2", "1");
        List<LibraryItem> libraryItems = Arrays.asList(aBook, anotherBook, aMovie);
        Biblioteca biblioteca = new Biblioteca(libraryItems);

        String expected = aBook.representation() + LINE_SEPARATOR + anotherBook.representation();

        assertAll(() -> {
            assertEquals(expected, biblioteca.representationOfAllLibraryItems(Book.class));
            assertFalse(aBook.representation().contains(aMovie.representation()));
        });
    }

    @Test
    void shouldReturnOnlyRepresentationOfMoviesInLibrary() {
        Book aBook = new Book("book2", "author2", 1996);
        Movie aMovie = new Movie("Movie1", 1996, "Movie2", "1");
        List<LibraryItem> libraryItems = Arrays.asList(aBook, aMovie);
        Biblioteca biblioteca = new Biblioteca(libraryItems);

        String allMoviesRepresentation = biblioteca.representationOfAllLibraryItems(Movie.class);

        assertAll(() -> {
            assertEquals(allMoviesRepresentation, aMovie.representation());
            assertNotEquals(allMoviesRepresentation, aBook.representation());
        });
    }

    @Test
    void shouldCheckoutALibraryItemFromBibliotecaIfItIsAvailable() {
        LibraryItem aLibraryItem = new Book("book1", "author1", 1996);
        LibraryItem anotherLibraryItem = new Book("book1", "author2", 1996);
        List<LibraryItem> libraryItems = Arrays.asList(aLibraryItem, anotherLibraryItem);
        Biblioteca biblioteca = new Biblioteca(libraryItems);

        assertEquals(Optional.of(aLibraryItem), biblioteca.checkoutALibraryItem("book1"));
    }

    @Test
    void shouldNotCheckoutALibraryItemIfItIsNotAvailableInBiblioteca() {
        LibraryItem aLibraryItem = new Book("book1", "author1", 1996);
        LibraryItem anotherLibraryItem = new Book("book2", "author2", 1996);
        List<LibraryItem> libraryItems = Arrays.asList(aLibraryItem, anotherLibraryItem);
        Biblioteca biblioteca = new Biblioteca(libraryItems);

        assertEquals(Optional.empty(), biblioteca.checkoutALibraryItem("book3"));
    }

    @Test
    void shouldRemoveLibraryItemFromAllLibraryItems() {
        LibraryItem aLibraryItem = new Book("book1", "author1", 1996);
        LibraryItem anotherLibraryItem = new Book("book2", "author2", 1996);
        List<LibraryItem> libraryItems = Arrays.asList(aLibraryItem, anotherLibraryItem);
        Biblioteca biblioteca = new Biblioteca(libraryItems);

        biblioteca.checkoutALibraryItem("book1");

        assertEquals(anotherLibraryItem.representation(), biblioteca.representationOfAllLibraryItems(Book.class));
    }

    @Test
    void shouldReturnTrueIfNoLibraryItemsInBiblioteca() {
        Biblioteca biblioteca = new Biblioteca(null);

        assertTrue(biblioteca.isEmpty());
    }

    @Test
    void shouldReturnFalseIfLibraryItemsAvailableInBiblioteca() {
        LibraryItem aLibraryItem = new Book("book1", "author1", 1996);
        LibraryItem anotherLibraryItem = new Book("book2", "author2", 1996);
        List<LibraryItem> libraryItems = Arrays.asList(aLibraryItem, anotherLibraryItem);
        Biblioteca biblioteca = new Biblioteca(libraryItems);

        assertFalse(biblioteca.isEmpty());
    }

    @Test
    void shouldReturnACheckedOutLibraryItem() {
        LibraryItem aLibraryItem = new Book("book1", "author1", 1996);
        LibraryItem anotherLibraryItem = new Book("book2", "author2", 1996);
        List<LibraryItem> libraryItems = Arrays.asList(aLibraryItem, anotherLibraryItem);
        Biblioteca biblioteca = new Biblioteca(libraryItems);

        biblioteca.checkoutALibraryItem("book1");

        assertTrue(biblioteca.returnLibraryItem("book1"));
    }

    @Test
    void shouldNotReturnAUnCheckedOutLibraryItem() {
        LibraryItem aLibraryItem = new Book("book1", "author1", 1996);
        LibraryItem anotherLibraryItem = new Book("book2", "author2", 1996);
        List<LibraryItem> libraryItems = Arrays.asList(aLibraryItem, anotherLibraryItem);
        Biblioteca biblioteca = new Biblioteca(libraryItems);

        biblioteca.checkoutALibraryItem("book1");

        assertFalse(biblioteca.returnLibraryItem("book2"));
    }

    @Test
    void shouldAddLibraryItemToBibliotecaAfterReturningALibraryItem() {
        LibraryItem aLibraryItem = new Book("book1", "author1", 1996);
        LibraryItem anotherLibraryItem = new Book("book2", "author2", 1996);
        List<LibraryItem> libraryItems = Arrays.asList(aLibraryItem, anotherLibraryItem);
        Biblioteca biblioteca = new Biblioteca(libraryItems);
        String actualRepresentation = anotherLibraryItem.representation() + LINE_SEPARATOR + aLibraryItem.representation();

        biblioteca.checkoutALibraryItem("book1");
        biblioteca.returnLibraryItem("book1");

        assertEquals(biblioteca.representationOfAllLibraryItems(Book.class), actualRepresentation);
    }

}
