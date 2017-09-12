package com.tw.controller;

import com.tw.view.IO;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class QuitCommandTest {

    @Test
    void displayThankYouMessageWhenUserQuitTheApplication() {
        IO io = mock(IO.class);
        QuitCommand quitCommand = new QuitCommand(io);
        String thankYouMessage = "Thank you for your valuable time";

        quitCommand.execute();

        verify(io).println(thankYouMessage);
    }

}
