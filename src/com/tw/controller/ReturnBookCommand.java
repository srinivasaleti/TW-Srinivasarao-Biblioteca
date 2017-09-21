package com.tw.controller;

import com.tw.model.Biblioteca;
import com.tw.model.Book;
import com.tw.view.IO;

//Responsible for returning a book to biblioteca
public class ReturnBookCommand extends ReturnItemAction implements Command {

    private static final String SUCCESSFUL_RETURN_MESSAGE = "Thank you for returning the book";
    private static final String BOOK_NOT_CHECKED_OUT_MESSAGE = "This book is not yet checked out";
    private static final String ENTER_BOOK_NAME = "Enter Book Name To Return::";
    private static final String REPRESENTATION = "Return Book";
    private static final String NOT_A_VALID_USER = "You are not a valid user to return this book";

    public ReturnBookCommand(Biblioteca biblioteca, IO io) {
        super(biblioteca, io, ENTER_BOOK_NAME, SUCCESSFUL_RETURN_MESSAGE, BOOK_NOT_CHECKED_OUT_MESSAGE, NOT_A_VALID_USER);
    }

    @Override
    public void execute() {
        super.execute(Book.class);
    }

    @Override
    public String representation() {
        return REPRESENTATION;
    }

}
