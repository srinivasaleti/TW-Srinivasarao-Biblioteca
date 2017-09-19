package com.tw.controller;

import com.tw.model.Biblioteca;
import com.tw.model.LibraryItem;
import com.tw.view.IO;

public abstract class ReturnItem {

    private final String enterItemName;
    private final String successfulReturnMessage;
    private final String unsuccessfulReturnMessage;

    private final Biblioteca biblioteca;
    private final IO io;

    public ReturnItem(Biblioteca biblioteca, IO io, String enterItemName, String successfulReturnMessage, String unsuccessfulReturnMessage) {
        this.biblioteca = biblioteca;
        this.io = io;
        this.enterItemName = enterItemName;
        this.successfulReturnMessage = successfulReturnMessage;
        this.unsuccessfulReturnMessage = unsuccessfulReturnMessage;
    }

    public void execute(Class<? extends LibraryItem> itemType) {
        String itemName = readItemName();
        boolean isReturn = this.biblioteca.returnLibraryItem(itemType, itemName);
        if (isReturn) {
            this.io.println(this.successfulReturnMessage);
            return;
        }
        this.io.println(this.unsuccessfulReturnMessage);
    }

    private String readItemName() {
        this.io.print(this.enterItemName);
        return this.io.getInput();
    }

}
