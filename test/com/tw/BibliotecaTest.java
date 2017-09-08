package com.tw;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class BibliotecaTest {

    private IO io;
    private Biblioteca biblioteca;

    @BeforeEach
    void setUp() {
        this.io = mock(IO.class);
        this.biblioteca = new Biblioteca(null, this.io);
    }

    @Test
    void displayWelcomeMessage() {
        String welcomeMessage = "Welcome to Bangalore Public Library";

        this.biblioteca.run();

        verify(this.io).println(welcomeMessage);
    }

}
