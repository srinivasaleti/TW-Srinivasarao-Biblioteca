package com.tw.controller;

import com.tw.model.Biblioteca;
import com.tw.model.LibraryUser;
import com.tw.model.User;
import com.tw.view.IO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoginCommandTest {

    private LoginCommand loginCommand;
    private IO io;
    private Biblioteca biblioteca;
    private Menu menuForLibraryUser;
    private User user;

    @BeforeEach
    void setUp() {
        this.io = mock(IO.class);
        this.menuForLibraryUser = mock(Menu.class);
        this.biblioteca = mock(Biblioteca.class);
        this.loginCommand = new LoginCommand(this.biblioteca, this.io, this.menuForLibraryUser);
        this.user = new LibraryUser("name", "libraryNo", "password", "email", "phoneNo");

    }

    @Test
    void shouldAskUserForUserNameAndPassword() {
        String libraryNo = "1234";
        String password = "1234";
        when(this.io.getInput()).thenReturn(libraryNo, password);
        when(this.biblioteca.userWithGivenCredentials(libraryNo, password)).thenReturn(Optional.empty());

        this.loginCommand.execute();

        verify(this.io).print("Enter library number:: ");
        verify(this.io).print("Enter password:: ");
        verify(this.io, times(2)).getInput();
    }

    @Test
    void shouldAskBibliotecaToGetUserWithGivenCredentials() {
        String libraryNo = "libraryNo";
        String password = "password";

        when(this.io.getInput()).thenReturn(libraryNo, password);
        when(this.biblioteca.userWithGivenCredentials(libraryNo, password)).thenReturn(Optional.empty());
        this.loginCommand.execute();

        verify(this.biblioteca).userWithGivenCredentials(libraryNo, password);
    }

    @Test
    void shouldNotDisplayNewMenuForUnSuccessfulLogin() {
        String libraryNo = "libraryNo";
        String password = "password";

        when(this.io.getInput()).thenReturn(libraryNo, password);
        when(this.biblioteca.userWithGivenCredentials(libraryNo, password)).thenReturn(Optional.empty());
        this.loginCommand.execute();

        verify(this.menuForLibraryUser, never()).menuSelection();
    }

    @Test
    void shouldDisplayInvalidCredentialsMessageForUnSuccessfulLogin() {
        String libraryNo = "libraryNo";
        String password = "password";

        when(this.io.getInput()).thenReturn(libraryNo, password);
        when(this.biblioteca.userWithGivenCredentials(libraryNo, password)).thenReturn(Optional.empty());
        this.loginCommand.execute();

        verify(this.io).println("Invalid credentials");
    }

    @Test
    void shouldReturnRepresentationOfLoginCommand() {
        String login = "Login";
        assertEquals(login, this.loginCommand.representation());
    }

    @Test
    void shouldChangeCurrentUserOfBibliotecaAfterSuccessfulLogin() {
        String libraryNo = "libraryNo";
        String password = "password";

        when(this.io.getInput()).thenReturn(libraryNo, password);
        when(this.biblioteca.userWithGivenCredentials(libraryNo, password)).thenReturn(Optional.of(this.user));
        this.loginCommand.execute();

        verify(this.biblioteca).changeCurrentUser(this.user);
    }

    @Test
    void shouldNotChangeCurrentUserOfBibliotecaOnUnSuccessfulLogin() {
        String libraryNo = "libraryNo";
        String password = "password";

        when(this.io.getInput()).thenReturn(libraryNo, password);
        when(this.biblioteca.userWithGivenCredentials(libraryNo, password)).thenReturn(Optional.empty());
        this.loginCommand.execute();

        verify(this.biblioteca, never()).changeCurrentUser(this.user);
    }

    @Test
    void shouldInvokeMenuForLibraryUserAfterSuccessfullyChangingCurrentUserOfBiblioteca() {
        String libraryNo = "libraryNo";
        String password = "password";

        when(this.io.getInput()).thenReturn(libraryNo, password);
        when(this.biblioteca.userWithGivenCredentials(libraryNo, password)).thenReturn(Optional.of(this.user));
        when(this.biblioteca.changeCurrentUser(this.user)).thenReturn(true);
        this.loginCommand.execute();

        verify(this.menuForLibraryUser).menuSelection();
    }

    @Test
    void shouldNotInvokeMenuForLibraryUserIfBibliotecaUserCannotChange() {
        String libraryNo = "libraryNo";
        String password = "password";

        when(this.io.getInput()).thenReturn(libraryNo, password);
        when(this.biblioteca.userWithGivenCredentials(libraryNo, password)).thenReturn(Optional.of(this.user));
        when(this.biblioteca.changeCurrentUser(this.user)).thenReturn(false);
        this.loginCommand.execute();

        verify(this.menuForLibraryUser, never()).menuSelection();
    }

    @Test
    void shouldReturnTrueForSuccessfulLogin() {
        String libraryNo = "libraryNo";
        String password = "password";

        when(this.io.getInput()).thenReturn(libraryNo, password);
        when(this.biblioteca.userWithGivenCredentials(libraryNo, password)).thenReturn(Optional.of(this.user));
        when(this.biblioteca.changeCurrentUser(this.user)).thenReturn(true);
        this.loginCommand.execute();

        assertTrue(this.loginCommand.loginSuccessful());
    }

    @Test
    void shouldReturnFalseForUnSuccessfulLogin() {
        String libraryNo = "libraryNo";
        String password = "password";

        when(this.io.getInput()).thenReturn(libraryNo, password);
        when(this.biblioteca.userWithGivenCredentials(libraryNo, password)).thenReturn(Optional.empty());
        this.loginCommand.execute();

        assertFalse(this.loginCommand.loginSuccessful());
    }

}
