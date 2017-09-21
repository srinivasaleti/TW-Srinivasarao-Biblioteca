package com.tw.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    void shouldReturnFalseWhenVerifyEqualityBetweenUserAndNull() {
        assertFalse(this.libraryUser.equals(null));
    }

    @Test
    void shouldReturnFalseWhenVerifyEqualityBetweenUserAndString() {
        assertFalse(this.libraryUser.equals("libraryUser"));
    }

    @Test
    void shouldReturnFalseWhenVerifyEqualityBetweenUserAndStringHimSelf() {
        assertTrue(this.libraryUser.equals(this.libraryUser));
    }

    @Test
    void shouldReturnFalseWhenVerifyEqualityBetweenUserAndAnotherSameUser() {
        User sameUser = new LibraryUser("srinu", "123-123424", "1234", "srinivas.aleti03@gmail.com", "9848981244");
        assertTrue(this.libraryUser.equals(sameUser));
    }

}
