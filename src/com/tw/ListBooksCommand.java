package com.tw;

//Represents list books command for biblioteca
public class ListBooksCommand implements Command {

    private static final String BOOKS = "Books::";
    private static final String FORMAT = "%-35s %-35s %-35s";
    private static final String HEADER = String.format(FORMAT, "Name", "Author", "YearPublished");
    private static final String NO_BOOKS_AVAILABLE = "No Books Available";

    private final Biblioteca biblioteca;
    private final IO io;

    public ListBooksCommand(Biblioteca biblioteca, IO io) {
        this.biblioteca = biblioteca;
        this.io = io;
    }

    @Override
    public void execute() {
        if (this.biblioteca.isEmpty()) {
            this.io.println(NO_BOOKS_AVAILABLE);
            return;
        }
        this.io.println(BOOKS);
        this.io.println(HEADER);
        this.io.println(this.biblioteca.representationOfAllBook());
    }

}
