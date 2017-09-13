package com.tw.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.tw.view.ConsoleIO.LINE_SEPARATOR;

//Represents a library
public class Biblioteca {

    private final List<LibraryItem> allLibraryItems;
    private final List<LibraryItem> checkedOutLibraryItems;

    public Biblioteca(List<LibraryItem> libraryItems) {
        if (libraryItems == null) {
            this.allLibraryItems = new ArrayList<>();
        } else {
            this.allLibraryItems = new ArrayList<>(libraryItems);
        }
        this.checkedOutLibraryItems = new ArrayList<>();
    }

    public String representationOfAllLibraryItems(Class<? extends LibraryItem> itemType) {
        return this.allLibraryItems
                .stream()
                .filter(libraryItem -> libraryItem.getClass().equals(itemType))
                .map(LibraryItem::representation)
                .collect(Collectors.joining(LINE_SEPARATOR));
    }

    public boolean isEmpty() {
        return this.allLibraryItems.isEmpty();
    }

    public Optional<LibraryItem> checkoutALibraryItem(String libraryItemName) {
        Optional<LibraryItem> checkedOutLibraryItem = this.findLibraryItem(this.allLibraryItems, libraryItemName);
        checkedOutLibraryItem.ifPresent(libraryItem -> this.moveLibraryItem(this.allLibraryItems, this.checkedOutLibraryItems, libraryItem));
        return checkedOutLibraryItem;
    }

    public boolean returnLibraryItem(String libraryItemName) {
        Optional<LibraryItem> optionalLibraryItem = this.findLibraryItem(this.checkedOutLibraryItems, libraryItemName);
        optionalLibraryItem.ifPresent(libraryItem -> moveLibraryItem(this.checkedOutLibraryItems, this.allLibraryItems, libraryItem));
        return optionalLibraryItem.isPresent();
    }

    private void moveLibraryItem(List<LibraryItem> fromList, List<LibraryItem> toList, LibraryItem libraryItem) {
        fromList.remove(libraryItem);
        toList.add(libraryItem);
    }

    private Optional<LibraryItem> findLibraryItem(List<LibraryItem> libraryItems, String libraryItemName) {
        return libraryItems.stream()
                .filter(libraryItem -> libraryItem.hasSameName(libraryItemName))
                .findFirst();
    }

}
