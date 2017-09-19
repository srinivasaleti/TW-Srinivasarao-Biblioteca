package com.tw.controller;

import com.tw.model.Biblioteca;
import com.tw.model.Book;
import com.tw.view.IO;

//Responsible for returning a book to biblioteca
public class ReturnBookCommand extends ReturnItemAction implements Command {

    private static final String SUCCESSFUL_RETURN_MESSAGE = "Thank you for returning the book";
    private static final String UNSUCCESSFUL_RETURN_MESSAGE = "This is not a valid book to return";
    private static final String ENTER_BOOK_NAME = "Enter Book Name To Return::";

    public ReturnBookCommand(Biblioteca biblioteca, IO io) {
        super(biblioteca,io, ENTER_BOOK_NAME, SUCCESSFUL_RETURN_MESSAGE, UNSUCCESSFUL_RETURN_MESSAGE);
    }

    @Override
    public void execute() {
        super.execute(Book.class);
    }

}
