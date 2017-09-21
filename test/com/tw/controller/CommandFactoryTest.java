package com.tw.controller;

import com.tw.model.Biblioteca;
import com.tw.view.ConsoleIO;
import com.tw.view.IO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static com.tw.view.ConsoleIO.LINE_SEPARATOR;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CommandFactoryTest {

    private CommandFactory commandFactory;
    private List<Command> commands;

    @BeforeEach
    void setUp() {
        IO io = mock(IO.class);
        this.commands = mock(List.class);
        this.commandFactory = new CommandFactory(this.commands, io);
    }

    @Test
    void shouldReturnInvalidCommand() {
        String option = "invalid";
        assertEquals(InvalidCommand.class, this.commandFactory.getCommand(option).getClass());
    }

    @Test
    void shouldReturnInvalidCommandForEmptyOption() {
        String emptyOption = "";
        assertEquals(InvalidCommand.class, this.commandFactory.getCommand(emptyOption).getClass());
    }

    @Test
    void shouldReturnQuitCommand() {
        String quit = "quit";
        assertEquals(QuitCommand.class, this.commandFactory.getCommand(quit).getClass());
    }

    @Test
    void shouldAskSizeOfCommandsIfGivenOptionIsValid() {
        String option = "1";

        this.commandFactory.getCommand(option);

        verify(this.commands).size();
    }

    @Test
    void shouldAskCommandsToGetCommandBasedOnOptionIfItIsValid() {
        String option = "1";
        int optionIndex = 0;

        when(this.commands.size()).thenReturn(1);
        this.commandFactory.getCommand(option);

        verify(this.commands).get(optionIndex);
    }

    @Test
    void shouldReturnInvalidCommandIfValueOfAValidOptionIsLessThanZero() {
        String option = "7";
        int noOfCommands = 5;

        when(this.commands.size()).thenReturn(noOfCommands);
        this.commandFactory.getCommand(option);

        assertEquals(InvalidCommand.class, this.commandFactory.getCommand(option).getClass());
    }

    @Test
    void shouldGetAValidCommandIfGivenOptionIsValid() {
        String option = "1";
        ListBooksCommand command = new ListBooksCommand(new Biblioteca(null, null), new ConsoleIO(System.out, new Scanner(System.in)));

        when(this.commands.size()).thenReturn(1);
        when(this.commands.get(0)).thenReturn(command);

        assertAll(() -> {
            assertEquals(this.commandFactory.getCommand(option).getClass(), ListBooksCommand.class);
            assertNotEquals(this.commandFactory.getCommand(option).getClass(), QuitCommand.class);
            assertNotEquals(this.commandFactory.getCommand(option).getClass(), InvalidCommand.class);
        });
    }

    @Test
    void shouldAskRepresentationOfACommandToGetMenuRepresentation() {
        Command command = mock(Command.class);
        IO io = mock(IO.class);
        List<Command> commands = Collections.singletonList(command);
        CommandFactory commandFactory = new CommandFactory(commands, io);

        commandFactory.representationOfMenuBasedOnCommands();

        verify(command).representation();
    }

    @Test
    void shouldAskRepresentationOfEveryCommandToGetMenuRepresentation() {
        Command command = mock(Command.class);
        Command anotherCommand = mock(Command.class);
        IO io = mock(IO.class);
        List<Command> commands = Arrays.asList(command, anotherCommand);
        CommandFactory commandFactory = new CommandFactory(commands, io);

        commandFactory.representationOfMenuBasedOnCommands();

        assertAll(() -> {
            verify(command).representation();
            verify(anotherCommand).representation();
        });
    }

    @Test
    void shouldReturnRepresentationOfMenuWithSingleCommand() {
        Command command = mock(Command.class);
        IO io = mock(IO.class);
        List<Command> commands = Collections.singletonList(command);
        CommandFactory commandFactory = new CommandFactory(commands, io);
        String representation = "Command";
        String menu = "1->" + representation +
                LINE_SEPARATOR + "Type Quit To Exit"
                + LINE_SEPARATOR;

        when(command.representation()).thenReturn(representation);
        commandFactory.representationOfMenuBasedOnCommands();

        assertEquals(menu, commandFactory.representationOfMenuBasedOnCommands());
    }

    @Test
    void shouldReturnRepresentationOfMenuBasedOnListOfCommands() {
        Command command = mock(Command.class);
        Command anotherCommand = mock(Command.class);
        IO io = mock(IO.class);
        List<Command> commands = Arrays.asList(command, anotherCommand);
        CommandFactory commandFactory = new CommandFactory(commands, io);
        String representationOfCommand = "Command";
        String representationOfAnotherCommand = "anotherCommand";
        String menu = "1->" + representationOfCommand
                + LINE_SEPARATOR + "2->" + representationOfAnotherCommand
                + LINE_SEPARATOR + "Type Quit To Exit"
                + LINE_SEPARATOR;

        when(command.representation()).thenReturn(representationOfCommand);
        when(anotherCommand.representation()).thenReturn(representationOfAnotherCommand);
        commandFactory.representationOfMenuBasedOnCommands();

        assertEquals(menu, commandFactory.representationOfMenuBasedOnCommands());
    }

    @Test
    void shouldReturnTrueForVerifyingWhetherQuitCommandIsExitCommandOfAMenuOrNot() {
        assertTrue(this.commandFactory.isExitCommandForMenu(new QuitCommand(mock(IO.class))));
    }

    @Test
    void shouldLoginCommandActsAsExitCommandOfAMenuUponASuccessfulLogin() {
        LoginCommand loginCommand = mock(com.tw.controller.LoginCommand.class);

        when(loginCommand.loginSuccessful()).thenReturn(true);

        assertTrue(this.commandFactory.isExitCommandForMenu(loginCommand));
    }

    @Test
    void shouldReturnTrueForVerifyingWhetherLogoutCommandIsExistCommandOfMenuOrNot() {
        assertTrue(this.commandFactory.isExitCommandForMenu(new LogoutCommand(mock(IO.class), mock(Menu.class))));
    }

    @Test
    void shouldReturnFalseForANonExitCommandOfMenu() {
        Biblioteca biblioteca = mock(Biblioteca.class);
        IO io = mock(IO.class);
        assertAll(() -> {
            assertFalse(this.commandFactory.isExitCommandForMenu(new InvalidCommand(io)));
            assertFalse(this.commandFactory.isExitCommandForMenu(new ListBooksCommand(biblioteca, io)));
            assertFalse(this.commandFactory.isExitCommandForMenu(new ListMoviesCommand(biblioteca, io)));
            assertFalse(this.commandFactory.isExitCommandForMenu(new CheckoutMovieCommand(biblioteca, io)));
            assertFalse(this.commandFactory.isExitCommandForMenu(new CheckoutBookCommand(biblioteca, io)));
            assertFalse(this.commandFactory.isExitCommandForMenu(new ReturnMovieCommand(biblioteca, io)));
            assertFalse(this.commandFactory.isExitCommandForMenu(new ReturnBookCommand(biblioteca, io)));
        });
    }

}
