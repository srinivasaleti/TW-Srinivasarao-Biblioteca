package com.tw.controller;

import com.tw.model.Biblioteca;
import com.tw.view.IO;

//Responsible for returning a book to biblioteca
public class ReturnBookCommand implements Command {

    private static final String NO_BOOKS_AVAILABLE = "No Books Available";
    private static final String SUCCESSFUL_RETURN_MESSAGE = "Thank you for returning the book";
    private static final String UNSUCCESSFUL_RETURN_MESSAGE = "This is not a valid book to return";
    private static final String ENTER_BOOK_NAME = "Enter Book Name To Return::";

    private final Biblioteca biblioteca;
    private final IO io;

    public ReturnBookCommand(Biblioteca biblioteca, IO io) {
        this.biblioteca = biblioteca;
        this.io = io;
    }

    @Override
    public void execute() {
        if (this.biblioteca.isEmpty()) {
            this.io.println(NO_BOOKS_AVAILABLE);
            return;
        }
        this.returnBook();
    }

    private void returnBook() {
        boolean isReturn = this.biblioteca.returnBook(readBookName());
        if (isReturn) {
            this.io.println(SUCCESSFUL_RETURN_MESSAGE);
            return;
        }
        this.io.println(UNSUCCESSFUL_RETURN_MESSAGE);
    }

    private String readBookName() {
        this.io.print(ENTER_BOOK_NAME);
        return this.io.getInput();
    }

}
