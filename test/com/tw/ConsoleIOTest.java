package com.tw;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.PrintStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ConsoleIOTest {

    private ConsoleIO consoleIO;
    private PrintStream out;

    @BeforeEach
    void setUp() {
        this.out = mock(System.out.getClass());
        this.consoleIO = new ConsoleIO(this.out);
    }

    @Test
    void displayHello() {
        String hello = "Hello";

        this.consoleIO.println(hello);

        verify(this.out).println(hello);
    }

    @Test
    void displayWorld() {
        String world = "World";

        this.consoleIO.println(world);

        verify(this.out).println(world);
    }

}
