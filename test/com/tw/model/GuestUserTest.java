package com.tw.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GuestUserTest {

    private GuestUser guestUser;

    @BeforeEach
    void setUp() {
        this.guestUser = new GuestUser();
    }

    @Test
    void shouldReturnFalseWhenVerifyEqualityBetweenGuestUserAndNull() {
        assertFalse(this.guestUser.equals(null));
    }

    @Test
    void shouldReturnFalseWhenVerifyEqualityBetweenGuestUserAndString() {
        assertFalse(this.guestUser.equals("guest user"));
    }

    @Test
    void shouldReturnTrueWhenVerifyEqualityBetweenGuestUserAndItSelf() {
        assertTrue(this.guestUser.equals(this.guestUser));
    }

    @Test
    void shouldReturnTrueWhenVerifyEqualityBetweenGuestUserAndAnotherGuestUser() {
        GuestUser sameUser = new GuestUser();
        assertTrue(this.guestUser.equals(sameUser));
    }

    @Test
    void shouldReturnFalseForVerifyingGuestUserHasCredentials() {
        assertFalse(this.guestUser.hasSameCredentials("any", "any"));
    }

    @Test
    void shouldReturnsRepresentationOfGuestUser() {
        String expected = "Guest user";
        assertEquals(expected, this.guestUser.representation());
    }

}
