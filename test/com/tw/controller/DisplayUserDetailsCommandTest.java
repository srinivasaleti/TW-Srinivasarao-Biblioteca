package com.tw.controller;

import com.tw.model.Biblioteca;
import com.tw.model.User;
import com.tw.view.IO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class DisplayUserDetailsCommandTest {

    private IO io;
    private Biblioteca biblioteca;
    private DisplayUserDetailsCommand displayUserDetailsCommand;

    @BeforeEach
    void setUp() {
        this.io = mock(IO.class);
        this.biblioteca = mock(Biblioteca.class);
        this.displayUserDetailsCommand = new DisplayUserDetailsCommand(this.biblioteca, this.io);
    }

    @Test
    void shouldAskBibliotecaAboutCurrentUser() {
        User currentUser = mock(User.class);

        when(this.biblioteca.currentUser()).thenReturn(currentUser);
        this.displayUserDetailsCommand.execute();

        verify(this.biblioteca).currentUser();
    }

    @Test
    void shouldAskCurrentUserAboutItsRepresentation() {
        User currentUser = mock(User.class);

        when(this.biblioteca.currentUser()).thenReturn(currentUser);
        this.displayUserDetailsCommand.execute();

        verify(currentUser).representation();
    }

    @Test
    void shouldDisplayUserDetails() {
        User currentUser = mock(User.class);
        String userRepresentation = "User Representation";

        when(this.biblioteca.currentUser()).thenReturn(currentUser);
        when(currentUser.representation()).thenReturn(userRepresentation);
        this.displayUserDetailsCommand.execute();

        verify(this.io).println(userRepresentation);
    }

    @Test
    void shouldReturnRepresentationOfDisplayUserDetailsCommand() {
        String expected = "User information";
        assertEquals(expected, this.displayUserDetailsCommand.representation());
    }

}
