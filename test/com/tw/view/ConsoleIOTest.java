package com.tw.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ConsoleIOTest {

    private ConsoleIO consoleIO;
    private PrintStream out;

    @BeforeEach
    void setUp() {
        this.out = mock(System.out.getClass());
        this.consoleIO = new ConsoleIO(this.out, new Scanner(System.in));
    }

    @Test
    void shouldDisplayHello() {
        String hello = "Hello";

        this.consoleIO.println(hello);

        verify(this.out).println(hello);
    }

    @Test
    void shouldDisplayWorld() {
        String world = "World";

        this.consoleIO.println(world);

        verify(this.out).println(world);
    }

    @Test
    void shouldDisplayHelloWithOutLine() {
        String hello = "Hello";

        this.consoleIO.print(hello);

        verify(this.out).print(hello);
    }

    @Test
    void shouldDisplayWorldWithOutLine() {
        String world = "World";

        this.consoleIO.print(world);

        verify(this.out).print(world);
    }

    @Test
    void shouldReadInputFromUser() {
        String input = "input";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ConsoleIO consoleIO = new ConsoleIO(System.out, new Scanner(System.in));

        assertEquals(input, consoleIO.getInput());
    }

    @Test
    void shouldReadAnotherInputFromUser() {
        String anotherInput = "anotherInput";
        InputStream in = new ByteArrayInputStream(anotherInput.getBytes());
        System.setIn(in);

        ConsoleIO consoleIO = new ConsoleIO(System.out, new Scanner(System.in));

        assertEquals(anotherInput, consoleIO.getInput());
    }

}
