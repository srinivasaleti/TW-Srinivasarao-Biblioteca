package com.tw.controller;

import com.tw.view.IO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class MenuTest {

    private Menu menu;
    private CommandFactory commandFactory;
    private IO io;

    @BeforeEach
    void setUp() {
        this.io = mock(IO.class);
        this.commandFactory = mock(CommandFactory.class);
        this.menu = new Menu(this.commandFactory, this.io);
    }

    @Test
    void shouldAskCommandFactoryToGetRepresentationOfMenu() {
        String quit = "quit";
        QuitCommand quitCommand = new QuitCommand(this.io);

        when(this.io.getInput()).thenReturn(quit);
        when(this.commandFactory.getCommand(quit)).thenReturn(quitCommand);
        this.menu.menuSelection();

        verify(this.commandFactory).representationOfMenuBasedOnCommands();
    }

    @Test
    void shouldDisplayMenu() {
        String quit = "quit";
        String menuRepresentation = "menuRepresentation";
        String header = "Menu::";
        QuitCommand quitCommand = new QuitCommand(this.io);

        when(this.io.getInput()).thenReturn(quit);
        when(this.commandFactory.getCommand(quit)).thenReturn(quitCommand);
        when(this.commandFactory.representationOfMenuBasedOnCommands()).thenReturn(menuRepresentation);
        this.menu.menuSelection();

        verify(this.io).println(header);
        verify(this.io).println(menuRepresentation);
    }

    @Test
    void readMenuOptionFromUser() {
        String enterMenuOption = "Enter your option::";
        String quit = "quit";
        QuitCommand quitCommand = new QuitCommand(this.io);

        when(this.io.getInput()).thenReturn(quit);
        when(this.commandFactory.getCommand(quit)).thenReturn(quitCommand);
        this.menu.menuSelection();

        verify(this.io).print(enterMenuOption);
        verify(this.io).getInput();
    }

    @Test
    void continuouslyChoosingOptionsUntilUserChooseToQuit() {
        String userOption = "invalid";
        String quit = "quit";
        InvalidCommand invalidCommand = new InvalidCommand(this.io);
        QuitCommand quitCommand = new QuitCommand(this.io);

        when(this.io.getInput()).thenReturn(userOption, userOption, quit);
        when(this.commandFactory.getCommand(userOption)).thenReturn(invalidCommand);
        when(this.commandFactory.getCommand(quit)).thenReturn(quitCommand);
        this.menu.menuSelection();

        verify(this.io, times(3)).getInput();
    }

    @Test
    void askCommandFactoryToGetCommand() {
        String quit = "quit";

        when(this.io.getInput()).thenReturn(quit);
        when(this.commandFactory.getCommand(quit)).thenReturn(new QuitCommand(this.io));
        this.menu.menuSelection();

        verify(this.commandFactory).getCommand(quit);
    }

    @Test
    void executeTheCommandGettingFromCommandFactory() {
        String quit = "quit";
        Command command = mock(QuitCommand.class);

        when(this.io.getInput()).thenReturn(quit);
        when(this.commandFactory.getCommand(quit)).thenReturn(command);
        this.menu.menuSelection();

        verify(command).execute();
    }

}
