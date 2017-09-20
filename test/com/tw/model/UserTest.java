package com.tw.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserTest {

    private User user;

    @BeforeEach
    void beforeEach() {
        this.user = new User("srinu", "123-123424", "1234", "srinivas.aleti03@gmail.com", "9848981244");
    }

    @Test
    void shouldReturnTrueForValidCredentials() {
        assertTrue(user.hasSameCredentials("123-123424", "1234"));
    }

    @Test
    void shouldReturnFalseForInValidCredentials() {
        assertFalse(user.hasSameCredentials("123-123414", "1434"));
    }

    @Test
    void shouldReturnFalseWhenVerifyEqualityBetweenUserAndNull() {
        assertFalse(this.user.equals(null));
    }

    @Test
    void shouldReturnFalseWhenVerifyEqualityBetweenUserAndString() {
        assertFalse(this.user.equals("user"));
    }

    @Test
    void shouldReturnFalseWhenVerifyEqualityBetweenUserAndStringHimSelf() {
        assertTrue(this.user.equals(this.user));
    }

    @Test
    void shouldReturnFalseWhenVerifyEqualityBetweenUserAndAnotherSameUser() {
        User sameUser = new User("srinu", "123-123424", "1234", "srinivas.aleti03@gmail.com", "9848981244");
        assertTrue(this.user.equals(sameUser));
    }

}

