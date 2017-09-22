package com.tw.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryUserTest {

    private LibraryUser libraryUser;

    @BeforeEach
    void beforeEach() {
        this.libraryUser = new LibraryUser("srinu", "123-123424", "1234", "srinivas.aleti03@gmail.com", "9848981244");
    }

    @Test
    void shouldReturnTrueForValidCredentials() {
        assertTrue(libraryUser.hasSameCredentials("123-123424", "1234"));
    }

    @Test
    void shouldReturnFalseForInValidCredentials() {
        assertFalse(libraryUser.hasSameCredentials("123-123414", "1434"));
    }

    @Test
    void shouldReturnFalseWhenVerifyEqualityBetweenLibraryUserAndNull() {
        assertFalse(this.libraryUser.equals(null));
    }

    @Test
    void shouldReturnFalseWhenVerifyEqualityBetweenLibraryUserAndString() {
        assertFalse(this.libraryUser.equals("libraryUser"));
    }

    @Test
    void shouldReturnTrueWhenVerifyEqualityBetweenLibraryUserAndItSelf() {
        assertTrue(this.libraryUser.equals(this.libraryUser));
    }

    @Test
    void shouldReturnTrueWhenVerifyEqualityBetweenLibraryUserAndAnotherSameLibraryUser() {
        User sameUser = new LibraryUser("srinu", "123-123424", "1234", "srinivas.aleti03@gmail.com", "9848981244");
        assertTrue(this.libraryUser.equals(sameUser));
    }

    @Test
    void shouldReturnsRepresentationOfLibraryUser() {
        String phoneNo = "9848981244";
        String email = "srinivas.aleti03@gmail.com";
        String name = "srinu";
        String format = "%-35s %-35s %-35s";
        LibraryUser libraryUser = new LibraryUser(name, "123-123424", "1234", email, phoneNo);
        String representation = String.format(format, name, email, phoneNo);

        assertEquals(representation, libraryUser.representation());
    }

}
