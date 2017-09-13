package com.tw.controller;

import com.tw.model.Biblioteca;
import com.tw.model.LibraryItem;
import com.tw.view.IO;

import java.util.Optional;

//Represents a command which is responsible for checkout a book from library
public class CheckoutBookCommand implements Command {

    private static final String ENTER_BOOK_NAME_TO_CHECKOUT = "Enter book name to checkout::";
    private static final String SUCCESS_MESSAGE = "Thank You Enjoy The Book";
    private static final String UN_SUCCESS_MESSAGE = "This book is not available";
    private static final String NO_BOOKS_AVAILABLE = "No Books Available";

    private final Biblioteca biblioteca;
    private final IO io;

    public CheckoutBookCommand(Biblioteca biblioteca, IO io) {
        this.biblioteca = biblioteca;
        this.io = io;
    }

    @Override
    public void execute() {
        if (!this.biblioteca.isEmpty()) {
            this.checkout();
            return;
        }
        this.io.println(NO_BOOKS_AVAILABLE);
    }

    private void checkout() {
        Optional<LibraryItem> checkoutBook = this.biblioteca.checkoutALibraryItem(readBookName());
        if (checkoutBook.isPresent()) {
            this.io.println(SUCCESS_MESSAGE);
            return;
        }
        this.io.println(UN_SUCCESS_MESSAGE);
    }

    private String readBookName() {
        this.io.print(ENTER_BOOK_NAME_TO_CHECKOUT);
        return this.io.getInput();
    }

}
