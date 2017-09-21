package com.tw.controller;

import com.tw.view.IO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class InvalidCommandTest {

    private InvalidCommand invalidCommand;
    private IO io;

    @BeforeEach
    void setUp() {
        io = mock(IO.class);
        this.invalidCommand = new InvalidCommand(this.io);
    }

    @Test
    void shouldDisplaySelectValidOptionWhenWeChooseInvalidOption() {
        String selectValidOption = "Select a valid option!";

        invalidCommand.execute();

        verify(this.io).println(selectValidOption);
    }


    @Test
    void shouldReturnRepresentationOfInvalidCommand() {
        String name = "";
        assertEquals(name, this.invalidCommand.representation());
    }

}
