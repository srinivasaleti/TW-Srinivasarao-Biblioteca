package com.tw;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.tw.ConsoleIO.LINE_SEPARATOR;

//Represents a library
public class Biblioteca {

    private final List<Book> books;
    private final List<Book> checkedOutBooks;

    public Biblioteca(List<Book> books) {
        if (books == null) {
            this.books = new ArrayList<>();
        } else {
            this.books = new ArrayList<>(books);
        }
        this.checkedOutBooks = new ArrayList<>();
    }

    public String representationOfAllBook() {
        return this.books
                .stream()
                .map(Book::representation)
                .collect(Collectors.joining(LINE_SEPARATOR));
    }

    public boolean isEmpty() {
        return this.books.isEmpty();
    }

    public Optional<Book> checkoutABook(String bookName) {
        Optional<Book> checkedOutBook = this.findBook(this.books, bookName);
        checkedOutBook.ifPresent(book -> this.moveBook(this.books, this.checkedOutBooks, book));
        return checkedOutBook;
    }

    public boolean returnBook(String bookName) {
        Optional<Book> optionalBook = this.findBook(this.checkedOutBooks, bookName);
        optionalBook.ifPresent(book -> moveBook(this.checkedOutBooks, this.books, book));
        return optionalBook.isPresent();
    }

    private void moveBook(List<Book> fromList, List<Book> toList, Book book) {
        fromList.remove(book);
        toList.add(book);
    }

    private Optional<Book> findBook(List<Book> books, String bookName) {
        return books.stream()
                .filter(book -> book.hasSameName(bookName))
                .findFirst();
    }

}
