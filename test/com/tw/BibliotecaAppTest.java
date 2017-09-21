package com.tw;

import com.tw.controller.Command;
import com.tw.controller.LoginCommand;
import com.tw.controller.LogoutCommand;
import com.tw.controller.Menu;
import com.tw.view.IO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class BibliotecaAppTest {

    private IO io;
    private List<Command> commandsForGuestUser;
    private List<Command> commandsForLibraryUser;
    private LoginCommand loginCommand;
    private LogoutCommand logoutCommand;
    private Menu menuForGuestUser;
    private BibliotecaApp bibliotecaApp;

    @BeforeEach
    void setUp() {
        this.io = mock(IO.class);
        this.commandsForGuestUser = mock(List.class);
        this.commandsForLibraryUser = mock(List.class);
        this.loginCommand = mock(LoginCommand.class);
        this.logoutCommand = mock(LogoutCommand.class);
        this.menuForGuestUser = mock(Menu.class);
        this.bibliotecaApp = new BibliotecaApp(this.io,
                this.commandsForGuestUser, this.commandsForLibraryUser,
                this.loginCommand, this.logoutCommand,
                this.menuForGuestUser);
    }

    @Test
    void shouldDisplayWelcomeMessage() {
        String welcomeMessage = "Welcome to Bangalore Public Library";
        this.bibliotecaApp.run();

        verify(this.io).println(welcomeMessage);
    }

    @Test
    void shouldAddLoginCommandToGuestUserCommands() {
        this.bibliotecaApp.run();

        verify(this.commandsForGuestUser).add(this.loginCommand);
    }

    @Test
    void shouldAddLogoutCommandToLibraryUserCommands() {
        this.bibliotecaApp.run();
        verify(this.commandsForLibraryUser).add(this.logoutCommand);
    }

    @Test
    void shouldAllowGuestUserToSelectAMenuFromMenu() {
        this.bibliotecaApp.run();

        verify(this.menuForGuestUser).menuSelection();
    }

}
