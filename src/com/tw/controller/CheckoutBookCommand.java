package com.tw.controller;

import com.tw.model.Biblioteca;
import com.tw.model.Book;
import com.tw.view.IO;

//Represents a command which is responsible for checkout a book from library
public class CheckoutBookCommand extends CheckoutItemAction implements Command {

    private static final String ENTER_BOOK_NAME_TO_CHECKOUT = "Enter book name to checkout::";
    private static final String SUCCESS_MESSAGE = "Thank You Enjoy The Book";
    private static final String UN_SUCCESS_MESSAGE = "This book is not available";
    private static final String NO_BOOKS_AVAILABLE = "No Books Available";

    public CheckoutBookCommand(Biblioteca biblioteca, IO io) {
        super(biblioteca, io, NO_BOOKS_AVAILABLE, ENTER_BOOK_NAME_TO_CHECKOUT, SUCCESS_MESSAGE, UN_SUCCESS_MESSAGE);
    }

    @Override
    public void execute() {
        super.execute(Book.class);
    }

}
