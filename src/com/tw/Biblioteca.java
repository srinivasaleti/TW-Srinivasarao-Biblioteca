package com.tw;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.tw.ConsoleIO.LINE_SEPARATOR;

//Represents a library
public class Biblioteca {

    private List<Book> books;

    public Biblioteca(List<Book> books) {
        if (books == null) {
            this.books = new ArrayList<>();
        } else {
            this.books = books;
        }
    }

    public String representationOfAllBook() {
        return this.books
                .stream()
                .map(Book::representation)
                .collect(Collectors.joining(LINE_SEPARATOR));
    }

}
