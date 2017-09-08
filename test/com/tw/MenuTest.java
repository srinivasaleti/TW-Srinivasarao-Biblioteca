package com.tw;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.tw.ConsoleIO.LINE_SEPARATOR;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.*;

class MenuTest {

    private Menu menu;
    private Biblioteca biblioteca;
    private IO io;

    @BeforeEach
    void setUp() {
        this.io = mock(IO.class);
        this.biblioteca = mock(Biblioteca.class);
        this.menu = new Menu(this.biblioteca, this.io);
    }

    @Test
    void displayWelcomeMessage() {
        String welcomeMessage = "Welcome to Bangalore Public Library";
        String invalid = "Invalid";

        when(this.io.getInput()).thenReturn(invalid);
        this.menu.run();

        verify(this.io).println(welcomeMessage);
    }

    @Test
    void displayHeaderAssociatedWithDisplayingAllBooks() {
        String format = "%-35s %-35s %-35s";
        String books = "Books::";
        String header = String.format(format, "Name", "Author", "YearPublished");
        String userOption = "1";

        when(this.io.getInput()).thenReturn(userOption);
        this.menu.run();

        assertAll(() -> {
            verify(this.io, times(2)).print(LINE_SEPARATOR);
            verify(this.io).println(books);
            verify(this.io).println(header);
        });
    }

    @Test
    void askBibliotecaAboutRepresentationOfAllBooksToDisplayAllBooksInIt() {
        String userOption = "1";

        when(this.io.getInput()).thenReturn(userOption);
        this.menu.run();

        verify(biblioteca).representationOfAllBook();
    }

    @Test
    void displayAllBooksInTheBiblioteca() {
        String userOption = "1";
        String allBooks = "All book";

        when(this.io.getInput()).thenReturn(userOption);
        when(this.biblioteca.representationOfAllBook()).thenReturn(allBooks);
        this.menu.run();

        verify(this.io).println(allBooks);
    }

    @Test
    void displayMenu() {
        String userOption = "1";

        when(this.io.getInput()).thenReturn(userOption);
        this.menu.run();

        verify(this.io).println("Menu::");
        verify(this.io).println("1->List Books");
    }

    @Test
    void readMenuOptionFromUser() {
        String enterMenuOption = "Enter your option::";
        String userOption = "1";

        when(this.io.getInput()).thenReturn(userOption);
        this.menu.run();

        verify(this.io).print(enterMenuOption);
        verify(this.io).getInput();
    }

    @Test
    void shouldNotDisplayBooksInLibraryIfUserNotSelectedListBooksOption() {
        String notListBooksOption = "other";

        when(this.io.getInput()).thenReturn(notListBooksOption);
        this.menu.run();

        verify(this.io, never()).println(this.biblioteca.representationOfAllBook());
    }

    @Test
    void displaySelectValidOptionWhenWeChooseInvalidOption() {
        String userOption = "invalid";

        when(this.io.getInput()).thenReturn(userOption);
        this.menu.run();

        verify(this.io).println("Select a valid option!");
    }

}
