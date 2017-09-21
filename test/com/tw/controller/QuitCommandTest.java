package com.tw.controller;

import com.tw.view.IO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class QuitCommandTest {

    @Test
    void shouldDisplayThankYouMessageWhenUserQuitTheApplication() {
        IO io = mock(IO.class);
        QuitCommand quitCommand = new QuitCommand(io);
        String thankYouMessage = "Thank you for your valuable time";

        quitCommand.execute();

        verify(io).println(thankYouMessage);
    }

    @Test
    void shouldReturnRepresentationOfQuitCommand() {
        IO io = mock(IO.class);
        QuitCommand quitCommand = new QuitCommand(io);
        String quit = "Quit";

        assertEquals(quit, quitCommand.representation());
    }

}
