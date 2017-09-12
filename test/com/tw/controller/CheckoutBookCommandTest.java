package com.tw.controller;

import com.tw.model.Biblioteca;
import com.tw.model.Book;
import com.tw.view.IO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.Mockito.*;

class CheckoutBookCommandTest {

    private Biblioteca biblioteca;
    private IO io;
    private CheckoutBookCommand checkoutBookCommand;

    @BeforeEach
    void setUp() {
        this.io = mock(IO.class);
        this.biblioteca = mock(Biblioteca.class);
        this.checkoutBookCommand = new CheckoutBookCommand(this.biblioteca, this.io);
    }

    @Test
    void shouldDisplayNoBooksAvailableIfBibliotecaIsEmpty() {
        String message = "No Books Available";
        when(this.biblioteca.isEmpty()).thenReturn(true);
        this.checkoutBookCommand.execute();

        verify(this.io).println(message);
    }

    @Test
    void shouldReadBookNameFromUserToCheckout() {
        String name = "book1";

        when(this.biblioteca.isEmpty()).thenReturn(false);
        when(this.io.getInput()).thenReturn(name);
        when(this.biblioteca.checkoutABook(name)).thenReturn(Optional.empty());
        this.checkoutBookCommand.execute();

        verify(this.io).print("Enter book name to checkout::");
        verify(this.io).getInput();
    }

    @Test
    void shouldAskBibliotecaToCheckoutABook() {
        String name = "book1";

        when(this.biblioteca.isEmpty()).thenReturn(false);
        when(this.io.getInput()).thenReturn(name);
        when(this.biblioteca.checkoutABook(name)).thenReturn(Optional.empty());
        this.checkoutBookCommand.execute();

        verify(this.biblioteca).checkoutABook(name);
    }

    @Test
    void shouldDisplayMessageAssociatedWithSuccessfulCheckout() {
        String name = "book1";
        String successMessage = "Thank You Enjoy The Book";
        Book book = new Book("book1", "author1", 1234);

        when(this.biblioteca.isEmpty()).thenReturn(false);
        when(this.io.getInput()).thenReturn(name);
        when(this.biblioteca.checkoutABook(name)).thenReturn(Optional.of(book));
        this.checkoutBookCommand.execute();

        verify(this.io).println(successMessage);
    }

    @Test
    void shouldDisplayMessageAssociatedWithUnSuccessfulCheckout() {
        String name = "bookName";
        String unSuccessMessage = "This book is not available";

        when(this.biblioteca.isEmpty()).thenReturn(false);
        when(this.io.getInput()).thenReturn(name);
        when(this.biblioteca.checkoutABook(name)).thenReturn(Optional.empty());
        this.checkoutBookCommand.execute();

        verify(this.io).println(unSuccessMessage);
    }

}
