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
        String quit = "quit";

        when(this.io.getInput()).thenReturn(quit);
        this.menu.run();

        verify(this.io).println(welcomeMessage);
    }

    @Test
    void displayHeaderAssociatedWithDisplayingAllBooks() {
        String format = "%-35s %-35s %-35s";
        String books = "Books::";
        String header = String.format(format, "Name", "Author", "YearPublished");
        String listBooksOption = "1";
        String quit = "quit";

        when(this.io.getInput()).thenReturn(listBooksOption, quit);
        this.menu.run();

        assertAll(() -> {
            verify(this.io, times(3)).print(LINE_SEPARATOR);
            verify(this.io).println(books);
            verify(this.io).println(header);
        });
    }

    @Test
    void askBibliotecaAboutRepresentationOfAllBooksToDisplayAllBooksInIt() {
        String listBooksOption = "1";
        String quit = "quit";

        when(this.io.getInput()).thenReturn(listBooksOption, quit);
        this.menu.run();

        verify(biblioteca).representationOfAllBook();
    }

    @Test
    void displayAllBooksInTheBiblioteca() {
        String listBooksOption = "1";
        String allBooks = "All book";
        String quit = "quit";

        when(this.io.getInput()).thenReturn(listBooksOption, quit);
        when(this.biblioteca.representationOfAllBook()).thenReturn(allBooks);
        this.menu.run();

        verify(this.io).println(allBooks);
    }

    @Test
    void displayMenu() {
        String quit = "quit";

        when(this.io.getInput()).thenReturn(quit);
        this.menu.run();

        verify(this.io).println("Menu::");
        verify(this.io).println("1->List Books");
        verify(this.io).println("Type quit to Exit application");
    }

    @Test
    void readMenuOptionFromUser() {
        String enterMenuOption = "Enter your option::";
        String quit = "quit";

        when(this.io.getInput()).thenReturn(quit);
        this.menu.run();

        verify(this.io).print(enterMenuOption);
        verify(this.io).getInput();
    }

    @Test
    void shouldNotDisplayBooksInLibraryIfUserNotSelectedListBooksOption() {
        String notListBooksOption = "other";
        String quit = "quit";

        when(this.io.getInput()).thenReturn(notListBooksOption, quit);
        this.menu.run();

        verify(this.io, never()).println(this.biblioteca.representationOfAllBook());
    }

    @Test
    void displaySelectValidOptionWhenWeChooseInvalidOption() {
        String userOption = "invalid";
        String quit = "quit";
        String selectValidOption = "Select a valid option!";

        when(this.io.getInput()).thenReturn(userOption, quit);
        this.menu.run();

        verify(this.io).println(selectValidOption);
    }

    @Test
    void continuouslyChoosingOptionsUntilUserChooseToQuit() {
        String userOption = "2";
        String quit = "quit";

        when(this.io.getInput()).thenReturn(userOption, userOption, quit);
        this.menu.run();

        verify(this.io, times(3)).getInput();
    }

    @Test
    void displayThankYouMessageWhenUserQuitTheApplication() {
        String userOption = "2";
        String quit = "quit";
        String thankYouMessage = "Thank you for your valuable time";

        when(this.io.getInput()).thenReturn(userOption, userOption, quit);
        this.menu.run();

        verify(this.io).println(thankYouMessage);
    }

}
