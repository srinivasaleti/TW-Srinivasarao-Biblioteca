package com.tw;

import com.tw.controller.LoginCommand;
import com.tw.controller.Menu;
import com.tw.view.IO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class BibliotecaAppTest {

    private IO io;
    private List commandsForGuestUser;
    private Menu menuForGuestUser;
    private LoginCommand loginCommand;
    private BibliotecaApp bibliotecaApp;

    @BeforeEach
    void setUp() {
        this.io = mock(IO.class);
        this.commandsForGuestUser = mock(List.class);
        this.menuForGuestUser = mock(Menu.class);
        this.loginCommand = mock(LoginCommand.class);
        this.bibliotecaApp = new BibliotecaApp(this.io, this.commandsForGuestUser, this.loginCommand, this.menuForGuestUser);
    }

    @Test
    void displayWelcomeMessage() {
        String welcomeMessage = "Welcome to Bangalore Public Library";
        this.bibliotecaApp.run();

        verify(this.io).println(welcomeMessage);
    }

    @Test
    void shouldAddLoginCommandToGuestUserCommand() {
        this.bibliotecaApp.run();

        verify(this.commandsForGuestUser).add(this.loginCommand);
    }

    @Test
    void shouldAllowGuestUserToSelectAMenuFromMenu() {
        this.bibliotecaApp.run();

        verify(this.menuForGuestUser).menuSelection();
    }

}
