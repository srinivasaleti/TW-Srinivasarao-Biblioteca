package com.tw;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.tw.ConsoleIO.LINE_SEPARATOR;

//Represents a library
public class Biblioteca {

    private final List<Book> books;

    public Biblioteca(List<Book> books) {
        if (books == null) {
            this.books = new ArrayList<>();
        } else {
            this.books = new ArrayList<>(books);
        }
    }

    public String representationOfAllBook() {
        return this.books
                .stream()
                .map(Book::representation)
                .collect(Collectors.joining(LINE_SEPARATOR));
    }

    public Optional<Book> checkoutABook(String bookName) {
        Optional<Book> checkedOutBook = this.books
                .stream()
                .filter(book -> book.hasSameName(bookName))
                .findFirst();
        checkedOutBook.ifPresent(aBook -> this.books.removeIf(book -> book.equals(aBook)));
        return checkedOutBook;
    }

}
