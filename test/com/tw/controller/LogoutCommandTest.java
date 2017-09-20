package com.tw.controller;

import com.tw.view.IO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class LogoutCommandTest {

    private LogoutCommand logoutCommand;
    private Menu menuForGuestUser;
    private IO io;

    @BeforeEach
    void setUp() {
        this.io = mock(IO.class);
        this.menuForGuestUser = mock(Menu.class);
        this.logoutCommand = new LogoutCommand(this.io, this.menuForGuestUser);
    }

    @Test
    void shouldReturnRepresentationsOfLogoutCommand() {
        String logout = "Logout";
        assertEquals(logout, this.logoutCommand.representation());
    }

    @Test
    void shouldDisplayThankYouMessage() {
        String thankYouMessage = "Thanks for your valuable time";
        this.logoutCommand.execute();

        verify(this.io).println(thankYouMessage);
    }

    @Test
    void shouldInvokeMenuForGuestUser() {
        this.logoutCommand.execute();

        verify(this.menuForGuestUser).menuSelection();
    }

}