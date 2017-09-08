package com.tw;

//Represents list books command for biblioteca
public class ListBooksCommand implements Command {

    private static final String BOOKS = "Books::";
    private static final String FORMAT = "%-35s %-35s %-35s";
    private static final String HEADER = String.format(FORMAT, "Name", "Author", "YearPublished");

    private final Biblioteca biblioteca;
    private final IO io;

    public ListBooksCommand(Biblioteca biblioteca, IO io) {
        this.biblioteca = biblioteca;
        this.io = io;
    }

    @Override
    public void execute() {
        this.io.println(BOOKS);
        this.io.println(HEADER);
        this.io.println(this.biblioteca.representationOfAllBook());
    }

}
