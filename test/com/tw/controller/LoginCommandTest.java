package com.tw.controller;

import com.tw.view.IO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class LoginCommandTest {

    private LoginCommand loginCommand;
    private IO io;
    private Menu menuForLibraryUser;

    @BeforeEach
    void setUp() {
        this.io = mock(IO.class);
        this.menuForLibraryUser = mock(Menu.class);
        this.loginCommand = new LoginCommand(this.io, this.menuForLibraryUser);
    }

    @Test
    void shouldAskUserForUserNameAndPassword() {
        when(this.io.getInput()).thenReturn("1234", "1234");

        this.loginCommand.execute();

        verify(this.io).print("Enter library number:: ");
        verify(this.io).print("Enter password:: ");
        verify(this.io, times(2)).getInput();
    }

    @Test
    void shouldDisplayNewMenuAfterSuccessfulLogin() {
        when(this.io.getInput()).thenReturn("1234", "1234");

        this.loginCommand.execute();

        verify(this.menuForLibraryUser).menuSelection();
    }

    @Test
    void shouldNotDisplayNewMenuForUnSuccessfulLogin() {
        when(this.io.getInput()).thenReturn("124", "124");

        this.loginCommand.execute();

        verify(this.menuForLibraryUser, never()).menuSelection();
    }

    @Test
    void shouldDisplayInvalidCredentialsMessageForUnSuccessfulLogin() {
        when(this.io.getInput()).thenReturn("124", "124");

        this.loginCommand.execute();

        verify(this.io).println("Invalid credentials");
    }

    @Test
    void shouldReturnRepresentationOfLoginCommand() {
        String login = "Login";
        assertEquals(login, this.loginCommand.representation());
    }

}