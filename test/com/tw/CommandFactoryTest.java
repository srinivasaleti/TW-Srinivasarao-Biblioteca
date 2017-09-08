package com.tw;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class CommandFactoryTest {

    private CommandFactory commandFactory;

    @BeforeEach
    void setUp() {
        Biblioteca biblioteca = mock(Biblioteca.class);
        IO io = mock(IO.class);
        this.commandFactory = new CommandFactory(biblioteca, io);
    }

    @Test
    void expectedListBooksCommand() {
        String option = "1";
        assertEquals(ListBooksCommand.class, this.commandFactory.getCommand(option).getClass());
    }

    @Test
    void expectedQuitCommand() {
        String quit = "quit";
        assertEquals(QuitCommand.class, this.commandFactory.getCommand(quit).getClass());
    }

    @Test
    void expectedInvalidCommand(){
        String option = "invalid";
        assertEquals(InvalidCommand.class, this.commandFactory.getCommand(option).getClass());
    }

}
