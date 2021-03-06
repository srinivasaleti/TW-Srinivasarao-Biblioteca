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
        Command quitCommand = mock(Command.class);

        when(this.io.getInput()).thenReturn(quit);
        when(this.commandFactory.getCommand(quit)).thenReturn(quitCommand);
        when(this.commandFactory.isExitCommandForMenu(quitCommand)).thenReturn(true);
        this.menu.menuSelection();

        verify(this.commandFactory).representationOfMenuBasedOnCommands();
    }

    @Test
    void shouldDisplayMenu() {
        String quit = "quit";
        String menuRepresentation = "menuRepresentation";
        String header = "Menu::";
        Command quitCommand = mock(Command.class);

        when(this.io.getInput()).thenReturn(quit);
        when(this.commandFactory.getCommand(quit)).thenReturn(quitCommand);
        when(this.commandFactory.representationOfMenuBasedOnCommands()).thenReturn(menuRepresentation);
        when(this.commandFactory.isExitCommandForMenu(quitCommand)).thenReturn(true);
        this.menu.menuSelection();

        verify(this.io).println(header);
        verify(this.io).println(menuRepresentation);
    }

    @Test
    void readMenuOptionFromUser() {
        String enterMenuOption = "Enter your option::";
        String quit = "quit";
        Command quitCommand = mock(Command.class);

        when(this.io.getInput()).thenReturn(quit);
        when(this.commandFactory.getCommand(quit)).thenReturn(quitCommand);
        when(this.commandFactory.isExitCommandForMenu(quitCommand)).thenReturn(true);
        this.menu.menuSelection();

        verify(this.io).print(enterMenuOption);
        verify(this.io).getInput();
    }

    @Test
    void shouldAskCommandFactoryToVerifyGivenCommandIsExitCommandForMenuOrNot() {
        String quit = "quit";
        QuitCommand quitCommand = new QuitCommand(mock(IO.class));

        when(this.io.getInput()).thenReturn(quit);
        when(this.commandFactory.getCommand(quit)).thenReturn(quitCommand);
        when(this.commandFactory.isExitCommandForMenu(quitCommand)).thenReturn(true);
        this.menu.menuSelection();

        verify(this.commandFactory).isExitCommandForMenu(quitCommand);
    }

    @Test
    void shouldAskUserToContinuouslyChooseOptionsUntilHeChooseToQuit() {
        String userOption = "invalid";
        String quit = "quit";
        InvalidCommand invalidCommand = new InvalidCommand(this.io);
        QuitCommand quitCommand = new QuitCommand(this.io);

        when(this.io.getInput()).thenReturn(userOption, userOption, quit);
        when(this.commandFactory.getCommand(userOption)).thenReturn(invalidCommand, invalidCommand);
        when(this.commandFactory.getCommand(quit)).thenReturn(quitCommand);
        when(this.commandFactory.isExitCommandForMenu(invalidCommand)).thenReturn(false, false);
        when(this.commandFactory.isExitCommandForMenu(quitCommand)).thenReturn(true);
        this.menu.menuSelection();

        verify(this.io, times(3)).getInput();
    }

    @Test
    void shouldAskCommandFactoryToGetCommand() {
        String quit = "quit";
        Command quitCommand = mock(Command.class);

        when(this.io.getInput()).thenReturn(quit);
        when(this.commandFactory.getCommand(quit)).thenReturn(quitCommand);
        when(this.commandFactory.isExitCommandForMenu(quitCommand)).thenReturn(true);
        this.menu.menuSelection();

        verify(this.commandFactory).getCommand(quit);
    }

    @Test
    void shouldExecuteTheCommandGettingFromCommandFactory() {
        String quit = "quit";
        Command command = mock(Command.class);

        when(this.io.getInput()).thenReturn(quit);
        when(this.commandFactory.getCommand(quit)).thenReturn(command);
        when(this.commandFactory.isExitCommandForMenu(command)).thenReturn(true);
        this.menu.menuSelection();

        verify(command).execute();
    }

}
