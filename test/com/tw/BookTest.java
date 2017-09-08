package com.tw;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

}
