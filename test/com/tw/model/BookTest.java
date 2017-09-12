package com.tw.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    void expectedRepresentationOfHalfGirlFriend() {
        String name = "Half GirlFriend";
        String author = "Chetan Bhagat";
        int yearPublished = 2014;
        String format = "%-35s %-35s %-10d";
        String halfGirlFriendRepresentation = String.format(format, name, author, yearPublished);
        Book halfGirlFriend = new Book(name, author, yearPublished);

        assertEquals(halfGirlFriend.representation(), halfGirlFriendRepresentation);
    }

    @Test
    void expectedRepresentationOfTrueLove() {
        String name = "True Love";
        String author = "Chetan Bhagat";
        int yearPublished = 2014;
        String format = "%-35s %-35s %-10d";
        String trueLoveRepresentation = String.format(format, name, author, yearPublished);
        Book trueLove = new Book(name, author, yearPublished);

        assertEquals(trueLove.representation(), trueLoveRepresentation);
    }

    @Test
    void bookIsNotEqualToNull() {
        Book aBook = new Book("True Love", "ChetanBhagat", 2014);

        assertFalse(aBook.equals(null));
    }

    @Test
    void bookIsNotEqualToString() {
        Book aBook = new Book("True Love", "ChetanBhagat", 2014);

        assertFalse(aBook.equals("aBook"));
    }

    @Test
    void bookIsEqualToItSelf() {
        Book aBook = new Book("True Love", "ChetanBhagat", 2014);

        assertTrue(aBook.equals(aBook));
    }

    @Test
    void bookIsEqualToSameBook() {
        Book aBook = new Book("True Love", "ChetanBhagat", 2014);
        Book sameBook = new Book("True Love", "ChetanBhagat", 2014);

        assertTrue(aBook.equals(sameBook));
    }

    @Test
    void shouldReturnTrueIfGivenNameIsEqualToBookName() {
        Book aBook = new Book("True Love", "ChetanBhagat", 2014);
        String bookName = "True Love";

        assertTrue(aBook.hasSameName(bookName));
    }

    @Test
    void shouldReturnFalseIfGivenNameIsNotEqualToBookName() {
        Book aBook = new Book("True Love", "ChetanBhagat", 2014);
        String bookName = "First Love";

        assertFalse(aBook.hasSameName(bookName));
    }

}
