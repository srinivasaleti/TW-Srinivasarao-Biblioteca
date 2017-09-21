package com.tw.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.tw.view.ConsoleIO.LINE_SEPARATOR;
import static org.junit.jupiter.api.Assertions.*;

class BibliotecaTest {

    @Test
    void shouldReturnNothingWhenThereAreNoLibraryItemsInLibrary() {
        Biblioteca biblioteca = new Biblioteca(null, null);
        String nothing = "";

        assertEquals(nothing, biblioteca.representationOfAllLibraryItems(Book.class));
    }

    @Test
    void shouldReturnOnlyRepresentationOfBooksInBiblioteca() {
        Book aBook = new Book("book1", "author1", 1996);
        Book anotherBook = new Book("book2", "author2", 1996);
        Movie aMovie = new Movie("Movie1", 1996, "Movie2", "1");
        List<LibraryItem> libraryItems = Arrays.asList(aBook, anotherBook, aMovie);
        Biblioteca biblioteca = new Biblioteca(libraryItems, null);

        String expected = aBook.representation() + LINE_SEPARATOR + anotherBook.representation();

        assertAll(() -> {
            assertEquals(expected, biblioteca.representationOfAllLibraryItems(Book.class));
            assertFalse(aBook.representation().contains(aMovie.representation()));
        });
    }

    @Test
    void shouldReturnOnlyRepresentationOfMoviesInBiblioteca() {
        Book aBook = new Book("book2", "author2", 1996);
        Movie aMovie = new Movie("Movie1", 1996, "Movie2", "1");
        List<LibraryItem> libraryItems = Arrays.asList(aBook, aMovie);
        Biblioteca biblioteca = new Biblioteca(libraryItems, null);

        String allMoviesRepresentation = biblioteca.representationOfAllLibraryItems(Movie.class);

        assertAll(() -> {
            assertEquals(allMoviesRepresentation, aMovie.representation());
            assertNotEquals(allMoviesRepresentation, aBook.representation());
        });
    }

    @Test
    void shouldCheckoutALibraryItemIfItAvailableInBiblioteca() {
        LibraryItem aLibraryItem = new Book("book1", "author1", 1996);
        LibraryItem anotherLibraryItem = new Book("book1", "author2", 1996);
        List<LibraryItem> libraryItems = Arrays.asList(aLibraryItem, anotherLibraryItem);
        Biblioteca biblioteca = new Biblioteca(libraryItems, null);

        assertEquals(Optional.of(aLibraryItem), biblioteca.checkoutALibraryItem(Book.class, "book1"));
    }

    @Test
    void shouldNotCheckoutALibraryItemIfItIsNotAvailableInBiblioteca() {
        LibraryItem aLibraryItem = new Book("book1", "author1", 1996);
        LibraryItem anotherLibraryItem = new Book("book2", "author2", 1996);
        List<LibraryItem> libraryItems = Arrays.asList(aLibraryItem, anotherLibraryItem);
        Biblioteca biblioteca = new Biblioteca(libraryItems, null);

        assertEquals(Optional.empty(), biblioteca.checkoutALibraryItem(Book.class, "book3"));
    }

    @Test
    void shouldNotCheckoutAMovieInsteadOfBookEvenIfNameMatches() {
        String titanic = "titanic";
        LibraryItem book = new Book(titanic, "author1", 1996);
        LibraryItem movie = new Movie(titanic, 1996, "author2", "3");
        List<LibraryItem> libraryItems = Arrays.asList(book, movie);
        Biblioteca biblioteca = new Biblioteca(libraryItems, null);

        Optional<LibraryItem> checkoutItem = biblioteca.checkoutALibraryItem(Book.class, "titanic");

        assertAll(() -> {
            assertNotEquals(Optional.of(movie), checkoutItem);
            assertEquals(Optional.of(book), checkoutItem);
        });
    }

    @Test
    void shouldRemoveLibraryItemFromAllLibraryItemsForSuccessfulCheckout() {
        LibraryItem aLibraryItem = new Book("book1", "author1", 1996);
        LibraryItem anotherLibraryItem = new Book("book2", "author2", 1996);
        List<LibraryItem> libraryItems = Arrays.asList(aLibraryItem, anotherLibraryItem);
        Biblioteca biblioteca = new Biblioteca(libraryItems, null);

        biblioteca.checkoutALibraryItem(Book.class, "book1");

        assertEquals(anotherLibraryItem.representation(), biblioteca.representationOfAllLibraryItems(Book.class));
    }

    @Test
    void shouldReturnTrueIfNoLibraryItemsInBiblioteca() {
        Biblioteca biblioteca = new Biblioteca(null, null);

        assertTrue(biblioteca.isEmpty(Book.class));
    }

    @Test
    void shouldReturnFalseIfBooksAreAvailableInBiblioteca() {
        LibraryItem aLibraryItem = new Book("book1", "author1", 1996);
        LibraryItem anotherLibraryItem = new Book("book2", "author2", 1996);
        List<LibraryItem> libraryItems = Arrays.asList(aLibraryItem, anotherLibraryItem);
        Biblioteca biblioteca = new Biblioteca(libraryItems, null);

        assertFalse(biblioteca.isEmpty(Book.class));
    }

    @Test
    void shouldReturnTrueIfNoMoviesAreAvailableInBiblioteca() {
        LibraryItem aLibraryItem = new Book("book1", "author1", 1996);
        LibraryItem anotherLibraryItem = new Book("book2", "author2", 1996);
        List<LibraryItem> libraryItems = Arrays.asList(aLibraryItem, anotherLibraryItem);
        Biblioteca biblioteca = new Biblioteca(libraryItems, null);

        assertTrue(biblioteca.isEmpty(Movie.class));
    }

    @Test
    void shouldReturnACheckedOutLibraryItem() {
        LibraryItem aLibraryItem = new Book("book1", "author1", 1996);
        LibraryItem anotherLibraryItem = new Book("book2", "author2", 1996);
        List<LibraryItem> libraryItems = Arrays.asList(aLibraryItem, anotherLibraryItem);
        Biblioteca biblioteca = new Biblioteca(libraryItems, null);

        biblioteca.checkoutALibraryItem(Book.class, "book1");

        assertTrue(biblioteca.returnLibraryItem(Book.class, "book1"));
    }

    @Test
    void shouldNotReturnAUnCheckedOutLibraryItem() {
        LibraryItem aLibraryItem = new Book("book1", "author1", 1996);
        LibraryItem anotherLibraryItem = new Book("book2", "author2", 1996);
        List<LibraryItem> libraryItems = Arrays.asList(aLibraryItem, anotherLibraryItem);
        Biblioteca biblioteca = new Biblioteca(libraryItems, null);

        biblioteca.checkoutALibraryItem(Book.class, "book1");

        assertFalse(biblioteca.returnLibraryItem(Book.class, "book2"));
    }

    @Test
    void shouldNotReturnAMovieInsteadOfBookEvenIfNameMatches() {
        String name = "half girlfriend";
        LibraryItem book = new Book(name, "author1", 1996);
        LibraryItem movie = new Movie(name, 1996, "director", "3");
        List<LibraryItem> libraryItems = Arrays.asList(book, movie);
        Biblioteca biblioteca = new Biblioteca(libraryItems, null);

        biblioteca.checkoutALibraryItem(Book.class, name);
        biblioteca.checkoutALibraryItem(Movie.class, name);

        assertFalse(biblioteca.returnLibraryItem(Movie.class, name));
    }

    @Test
    void shouldAddLibraryItemToBibliotecaAfterReturningALibraryItem() {
        LibraryItem aLibraryItem = new Book("book1", "author1", 1996);
        LibraryItem anotherLibraryItem = new Book("book2", "author2", 1996);
        List<LibraryItem> libraryItems = Arrays.asList(aLibraryItem, anotherLibraryItem);
        Biblioteca biblioteca = new Biblioteca(libraryItems, null);
        String actualRepresentation = anotherLibraryItem.representation() + LINE_SEPARATOR + aLibraryItem.representation();

        biblioteca.checkoutALibraryItem(Book.class, "book1");
        biblioteca.returnLibraryItem(Book.class, "book1");

        assertEquals(biblioteca.representationOfAllLibraryItems(Book.class), actualRepresentation);
    }

    @Test
    void shouldReturnValidUserForValidCredentials() {
        User user1 = new LibraryUser("srinu", "123-1234", "Xy2@password", "srinivas.aleti03@gmail.com", "9838918493");
        User user2 = new LibraryUser("ramu", "111-1111", "Ab3@password", "srinivas.aleti03@gmail.com", "9838918493");
        List<User> users = Arrays.asList(user1, user2);
        Biblioteca biblioteca = new Biblioteca(null, users);

        assertTrue(biblioteca.userWithGivenCredentials("111-1111", "Ab3@password").equals(Optional.of(user2)));
    }

    @Test
    void shouldNotReturnAnyUserForInvalidCredentials() {
        User user1 = new LibraryUser("srinu", "123-1234", "Xy2@password", "srinivas.aleti03@gmail.com", "9838918493");
        User user2 = new LibraryUser("ramu", "111-1111", "Ab3@password", "srinivas.aleti03@gmail.com", "9838918493");
        List<User> users = Arrays.asList(user1, user2);
        Biblioteca biblioteca = new Biblioteca(null, users);

        assertTrue(biblioteca.userWithGivenCredentials("invalidLibraryNo", "invalidPassword").equals(Optional.empty()));
    }

    @Test
    void shouldChangeCurrentUser() {
        User user = new LibraryUser("srinu", "123-1234", "Xy2@password", "srinivas.aleti03@gmail.com", "9838918493");
        List<User> users = Collections.singletonList(user);
        Biblioteca biblioteca = new Biblioteca(null, users);

        assertTrue(biblioteca.changeCurrentUser(user));
    }

    @Test
    void shouldNotChangeCurrentUserOfBibliotecaIfHeIsNotInUserList() {
        User existingUser = new LibraryUser("ramu", "111-1111", "Ab3@password", "srinivas.aleti03@gmail.com", "9838918493");
        User notExistingUser = new LibraryUser("srinu", "123-1234", "Xy2@password", "srinivas.aleti03@gmail.com", "9838918493");

        List<User> users = Collections.singletonList(existingUser);
        Biblioteca biblioteca = new Biblioteca(null, users);

        assertFalse(biblioteca.changeCurrentUser(notExistingUser));
    }

    @Test
    void shouldChangeCurrentUserOfBibliotecaToGuestUser() {
        Biblioteca biblioteca = new Biblioteca(null, null);

        assertTrue(biblioteca.changeCurrentUser(new GuestUser()));
    }

}
