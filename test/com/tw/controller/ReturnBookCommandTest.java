package com.tw.controller;

import com.tw.model.Biblioteca;
import com.tw.model.Book;
import com.tw.view.IO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class ReturnBookCommandTest {

    private ReturnBookCommand returnBookCommand;
    private Biblioteca biblioteca;
    private IO io;

    @BeforeEach
    void setUp() {
        this.biblioteca = mock(Biblioteca.class);
        this.io = mock(IO.class);
        this.returnBookCommand = new ReturnBookCommand(this.biblioteca, this.io);
    }

    @Test
    void shouldReadBookNameFromUserToReturnABook() {
        String message = "Enter Book Name To Return::";

        this.returnBookCommand.execute();

        verify(this.io).print(message);
        verify(this.io).getInput();
    }

    @Test
    void shouldReturnABookToLibrary() {
        String bookName = "BookName";

        when(this.io.getInput()).thenReturn(bookName);
        this.returnBookCommand.execute();

        verify(this.biblioteca).returnLibraryItem(Book.class, bookName);
    }

    @Test
    void shouldDisplaySuccessMessageOnSuccessfulReturn() {
        String bookName = "returnBook";
        String successMessage = "Thank you for returning the book";

        when(this.io.getInput()).thenReturn(bookName);
        when(this.biblioteca.returnLibraryItem(Book.class, bookName)).thenReturn(true);
        this.returnBookCommand.execute();

        verify(this.io).println(successMessage);
    }

    @Test
    void shouldDisplayUnSuccessMessageOnUnSuccessfulReturn() {
        String bookName = "returnBook";
        String unSuccessMessage = "This is not a valid book to return";

        when(this.io.getInput()).thenReturn(bookName);
        when(this.biblioteca.returnLibraryItem(Book.class, bookName)).thenReturn(false);
        this.returnBookCommand.execute();

        verify(this.io).println(unSuccessMessage);
    }

}
