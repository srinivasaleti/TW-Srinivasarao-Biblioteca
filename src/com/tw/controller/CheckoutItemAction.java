package com.tw.controller;

import com.tw.model.Biblioteca;
import com.tw.model.LibraryItem;
import com.tw.view.IO;

import java.util.Optional;

//Responsible for checkout a item from biblioteca
abstract class CheckoutItemAction {

    private final String noItemsAvailable;
    private final String enterItemNameToCheckout;
    private final String successfulCheckoutMessage;
    private final String unSuccessfulCheckoutMessage;

    private final Biblioteca biblioteca;
    private final IO io;

    public CheckoutItemAction(Biblioteca biblioteca, IO io, String noItemsAvailable, String enterItemNameToCheckout,
                              String successfulCheckoutMessage, String unSuccessfulCheckoutMessage) {
        this.biblioteca = biblioteca;
        this.io = io;
        this.noItemsAvailable = noItemsAvailable;
        this.enterItemNameToCheckout = enterItemNameToCheckout;
        this.successfulCheckoutMessage = successfulCheckoutMessage;
        this.unSuccessfulCheckoutMessage = unSuccessfulCheckoutMessage;
    }

    public void execute(Class<? extends LibraryItem> itemType) {
        if (this.biblioteca.isEmpty(itemType)) {
            this.io.println(noItemsAvailable);
            return;
        }
        String itemName = readItemName();
        Optional<LibraryItem> checkoutItem = this.biblioteca.checkoutALibraryItem(itemType, itemName);
        displayMessageBasedOn(checkoutItem);
    }

    private String readItemName() {
        this.io.print(enterItemNameToCheckout);
        return this.io.getInput();
    }

    private void displayMessageBasedOn(Optional<LibraryItem> checkoutItem) {
        if (checkoutItem.isPresent()) {
            this.io.println(successfulCheckoutMessage);
            return;
        }
        this.io.println(unSuccessfulCheckoutMessage);
    }

}
