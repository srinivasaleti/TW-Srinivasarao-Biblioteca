package com.tw.controller;

import com.tw.model.Biblioteca;
import com.tw.model.LibraryItem;
import com.tw.view.IO;

//Responsible for return a item to biblioteca
public abstract class ReturnItemAction {

    private final String notAValidUserMessage;
    private final String enterItemName;
    private final String successfulReturnMessage;
    private final String itemNotCheckedOutMessage;

    private final Biblioteca biblioteca;
    private final IO io;

    ReturnItemAction(Biblioteca biblioteca, IO io, String enterItemName, String successfulReturnMessage, String itemNotCheckedOutMessage, String notAValidUserMessage) {
        this.biblioteca = biblioteca;
        this.io = io;
        this.enterItemName = enterItemName;
        this.successfulReturnMessage = successfulReturnMessage;
        this.itemNotCheckedOutMessage = itemNotCheckedOutMessage;
        this.notAValidUserMessage = notAValidUserMessage;
    }

    public void execute(Class<? extends LibraryItem> itemType) {
        String itemName = readItemName();
        if (!this.biblioteca.isItemCheckedOut(itemType, itemName)) {
            this.io.println(this.itemNotCheckedOutMessage);
            return;
        }
        if (!this.biblioteca.currentUserCheckedOutItem(itemType, itemName)) {
            this.io.println(this.notAValidUserMessage);
            return;
        }
        boolean isReturn = this.biblioteca.returnLibraryItem(itemType, itemName);
        if (isReturn) {
            this.io.println(this.successfulReturnMessage);
        }
    }

    private String readItemName() {
        this.io.print(this.enterItemName);
        return this.io.getInput();
    }

}
