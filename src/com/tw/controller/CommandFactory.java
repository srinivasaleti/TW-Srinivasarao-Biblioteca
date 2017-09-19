package com.tw.controller;

import com.tw.view.IO;

import java.util.List;

import static com.tw.view.ConsoleIO.LINE_SEPARATOR;

//Represents a factory which gives proper command based on user input
public class CommandFactory {

    private static final int OFFSET = 1;
    private static final int STARTING_INDEX_OF_LIST = 0;
    private static final String QUIT = "quit";
    private static final String TYPE_QUIT_TO_EXIT = "Type Quit To Exit";
    private static final String DELIMITER = "->";

    private final List<Command> commands;
    private final IO io;

    public CommandFactory(List<Command> commands, IO io) {
        this.commands = commands;
        this.io = io;
    }

    public Command getCommand(String option) {
        if (option.equalsIgnoreCase(QUIT)) {
            return new QuitCommand(io);
        }
        if (!isValidOption(option)) {
            return new InvalidCommand(io);
        }
        int index = Integer.parseInt(option) - OFFSET;
        if (indexIsInsideListBounds(index)) {
            return this.commands.get(index);
        }
        return new InvalidCommand(this.io);
    }

    public String representationOfMenuBasedOnCommands() {
        StringBuilder result = new StringBuilder();
        int index = STARTING_INDEX_OF_LIST + OFFSET;
        for (Command command : this.commands) {
            appendACommandRepresentationTo(result, command, index);
            index += OFFSET;
        }
        appendQuitCommandRepresentationTo(result);
        return result.toString();
    }

    private boolean indexIsInsideListBounds(int index) {
        return index >= STARTING_INDEX_OF_LIST && index < this.commands.size();
    }

    private boolean isValidOption(String option) {
        for (Character character : option.toCharArray()) {
            if (!Character.isDigit(character)) {
                return false;
            }
        }
        return true;
    }

    private void appendACommandRepresentationTo(StringBuilder result, Command command, int index) {
        result.append(index);
        result.append(DELIMITER);
        result.append(command.representation());
        result.append(LINE_SEPARATOR);
    }

    private void appendQuitCommandRepresentationTo(StringBuilder result) {
        result.append(TYPE_QUIT_TO_EXIT);
        result.append(LINE_SEPARATOR);
    }

}
