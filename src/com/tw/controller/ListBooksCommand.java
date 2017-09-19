package com.tw.controller;

import com.tw.model.Biblioteca;
import com.tw.model.Book;
import com.tw.view.IO;

//Represents list books command for biblioteca
public class ListBooksCommand extends ListItemsAction implements Command {

    private static final String BOOKS = "Books::";
    private static final String FORMAT = "%-35s %-35s %-35s";
    private static final String HEADER = String.format(FORMAT, "Name", "Author", "YearPublished");
    private static final String NO_BOOKS_AVAILABLE = "No Books Available";

    public ListBooksCommand(Biblioteca biblioteca, IO io) {
        super(biblioteca, io, NO_BOOKS_AVAILABLE, BOOKS, HEADER);
    }

    @Override
    public void execute() {
        super.execute(Book.class);
    }

}
