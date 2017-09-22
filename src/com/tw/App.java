package com.tw;

import com.tw.controller.*;
import com.tw.model.Biblioteca;
import com.tw.view.ConsoleIO;
import com.tw.view.IO;

import java.util.List;
import java.util.Scanner;

//Represents entry point for application
public class App {

    public static void main(String[] args) {
        BibliotecaSetup bibliotecaSetup = new BibliotecaSetup();

        IO io = new ConsoleIO(System.out, new Scanner(System.in));
        Biblioteca biblioteca = new Biblioteca(bibliotecaSetup.listOfLibraryItems(), bibliotecaSetup.users());

        List<Command> commandsForGuestUser = bibliotecaSetup.commandsAssociatedWithGuestUser(biblioteca, io);
        Menu menuForGuestUser = getMenu(io, commandsForGuestUser);

        List<Command> commandsForLibraryMember = bibliotecaSetup.commandsAssociatedWithLibraryUser(biblioteca, io);
        Menu menuForLibraryUser = getMenu(io, commandsForLibraryMember);

        LoginCommand loginCommand = new LoginCommand(biblioteca, io, menuForLibraryUser);
        LogoutCommand logoutCommand = new LogoutCommand(biblioteca, io, menuForGuestUser);

        BibliotecaApp bibliotecaApp = new BibliotecaApp(io, commandsForGuestUser, commandsForLibraryMember,
                loginCommand, logoutCommand, menuForGuestUser);

        bibliotecaApp.run();
    }

    private static Menu getMenu(IO io, List<Command> commandsForGuestUser) {
        CommandFactory commandFactoryGuestUser = new CommandFactory(commandsForGuestUser, io);
        return new Menu(commandFactoryGuestUser, io);
    }

}
