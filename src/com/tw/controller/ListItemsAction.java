package com.tw.controller;

import com.tw.model.Biblioteca;
import com.tw.model.LibraryItem;
import com.tw.view.IO;

//Responsible for list a specific item from biblioteca
public abstract class ListItemsAction {

    private final String itemRepresentation;
    private final String header;
    private final String noItemsAvailable;

    private final Biblioteca biblioteca;
    private final IO io;

    ListItemsAction(Biblioteca biblioteca, IO io, String noItemsAvailable, String itemRepresentation, String header) {
        this.biblioteca = biblioteca;
        this.io = io;
        this.itemRepresentation = itemRepresentation;
        this.header = header;
        this.noItemsAvailable = noItemsAvailable;
    }

    public void execute(Class<? extends LibraryItem> itemType) {
        if (this.biblioteca.isEmpty(itemType)) {
            this.io.println(this.noItemsAvailable);
            return;
        }
        this.displayItems(itemType);
    }

    private void displayItems(Class<? extends LibraryItem> itemType) {
        this.io.println(this.itemRepresentation);
        this.io.println(this.header);
        this.io.println(this.biblioteca.representationOfAllLibraryItems(itemType));
    }

}
