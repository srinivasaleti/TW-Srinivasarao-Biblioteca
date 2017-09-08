package com.tw;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class InvalidCommandTest {

    @Test
    void displaySelectValidOptionWhenWeChooseInvalidOption() {
        IO io = mock(IO.class);
        String selectValidOption = "Select a valid option!";
        InvalidCommand invalidCommand = new InvalidCommand(io);

        invalidCommand.execute();

        verify(io).println(selectValidOption);
    }

}
