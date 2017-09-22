package com.tw;

import com.tw.controller.*;
import com.tw.model.*;
import com.tw.view.IO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class BibliotecaSetupTest {

    private BibliotecaSetup bibliotecaSetup;

    @BeforeEach
    void setUp() {
        this.bibliotecaSetup = new BibliotecaSetup();
    }

    @Test
    void shouldReturnListOfLibraryItemsOfBiblioteca() {
        Book halfGirlFriend = new Book("Half GirlFriend", "Chetan Bhagat", 2014);
        Book loveStory = new Book("Love Story", "Erich Segal", 1970);
        Book firstLove = new Book("First Love", "Ivan Turgenev", 1860);
        Movie twilight = new Movie("Twilight", 2009, "HardWicke", "8");
        Movie titanic = new Movie("Titanic", 1997, "Cameron", "9");
        List<LibraryItem> expected = Arrays.asList(halfGirlFriend, loveStory, firstLove, twilight, titanic);

        assertArrayEquals(expected.toArray(), this.bibliotecaSetup.listOfLibraryItems().toArray());
    }

    @Test
    void shouldReturnsListOfUsersOfBiblioteca() {
        User user1 = new LibraryUser("srinu", "123-1234", "Xy2@password", "srinivas.aleti03@gmail.com", "9838918493");
        User user2 = new LibraryUser("ramu", "111-1111", "Ab3@password", "ramu.hello@gmail.com", "9838918493");
        List<User> expected = Arrays.asList(user1, user2);

        assertEquals(expected, this.bibliotecaSetup.users());
    }

    @Test
    void shouldReturnsCommandsAssociatedWithGuestUsers() {
        Biblioteca biblioteca = mock(Biblioteca.class);
        IO io = mock(IO.class);
        ListBooksCommand listBooksCommand = new ListBooksCommand(biblioteca, io);
        ListMoviesCommand listMoviesCommand = new ListMoviesCommand(biblioteca, io);
        List<Command> expected = Arrays.asList(listBooksCommand, listMoviesCommand);

        List<Command> actual = this.bibliotecaSetup.commandsAssociatedWithGuestUser(biblioteca, io);

        assertAll(() -> {
            assertEquals(expected.size(), actual.size());

            Iterator<Command> expectedIterator = expected.iterator();
            Iterator<Command> actualIterator = actual.iterator();

            while (expectedIterator.hasNext() && actualIterator.hasNext()) {
                Command actualCommand = actualIterator.next();
                Command expectedCommand = expectedIterator.next();
                assertEquals(actualCommand.getClass(), expectedCommand.getClass());
            }
        });
    }

    @Test
    void shouldReturnsCommandsAssociatedWithLibraryUsers() {
        Biblioteca biblioteca = mock(Biblioteca.class);
        IO io = mock(IO.class);
        ListBooksCommand listBooksCommand = new ListBooksCommand(biblioteca, io);
        ListMoviesCommand listMoviesCommand = new ListMoviesCommand(biblioteca, io);
        CheckoutBookCommand checkoutBookCommand = new CheckoutBookCommand(biblioteca, io);
        CheckoutMovieCommand checkoutMovieCommand = new CheckoutMovieCommand(biblioteca, io);
        ReturnBookCommand returnBookCommand = new ReturnBookCommand(biblioteca, io);
        ReturnMovieCommand returnMovieCommand = new ReturnMovieCommand(biblioteca, io);
        DisplayUserDetailsCommand displayUserDetailsCommand = new DisplayUserDetailsCommand(biblioteca, io);

        List<Command> actual = Arrays.asList(listBooksCommand, checkoutBookCommand,
                returnBookCommand, listMoviesCommand, checkoutMovieCommand, returnMovieCommand,
                displayUserDetailsCommand);

        List<Command> expected = this.bibliotecaSetup.commandsAssociatedWithLibraryUser(biblioteca, io);

        assertAll(() -> {
            assertEquals(expected.size(), actual.size());

            Iterator<Command> expectedIterator = expected.iterator();
            Iterator<Command> actualIterator = actual.iterator();

            while (expectedIterator.hasNext() && actualIterator.hasNext()) {
                Command actualCommand = actualIterator.next();
                Command expectedCommand = expectedIterator.next();
                assertEquals(actualCommand.getClass(), expectedCommand.getClass());
            }
        });
    }

}
