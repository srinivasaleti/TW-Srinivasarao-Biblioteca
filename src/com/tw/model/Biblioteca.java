package com.tw.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.tw.view.ConsoleIO.LINE_SEPARATOR;

//Represents a library
public class Biblioteca {

    private final List<LibraryItem> allLibraryItems;
    private final List<LibraryItem> checkedOutLibraryItems;
    private final List<User> users;
    private User currentUser;
    private final HashMap<LibraryItem, User> register;

    public Biblioteca(List<LibraryItem> libraryItems, List<User> users) {
        if (libraryItems == null) {
            this.allLibraryItems = new ArrayList<>();
        } else {
            this.allLibraryItems = new ArrayList<>(libraryItems);
        }
        if (users == null) {
            this.users = new ArrayList<>();
        } else {
            this.users = new ArrayList<>(users);
        }
        this.currentUser = new GuestUser();
        this.checkedOutLibraryItems = new ArrayList<>();
        this.register = new HashMap<>();
    }

    public String representationOfAllLibraryItems(Class<? extends LibraryItem> itemType) {
        return this.allLibraryItems
                .stream()
                .filter(libraryItem -> libraryItem.getClass().equals(itemType))
                .map(LibraryItem::representation)
                .collect(Collectors.joining(LINE_SEPARATOR));
    }

    public boolean isEmpty(Class<? extends LibraryItem> itemType) {
        return this.allLibraryItems.stream().noneMatch(libraryItem -> libraryItem.getClass().equals(itemType));
    }

    public Optional<LibraryItem> checkoutALibraryItem(Class<? extends LibraryItem> itemType, String libraryItemName) {
        Optional<LibraryItem> allItemsHavingSameName = this.findLibraryItem(this.allLibraryItems, libraryItemName);
        Optional<LibraryItem> checkedOutItem = allItemsHavingSameName.filter(libraryItem -> libraryItem.getClass().equals(itemType));
        checkedOutItem.ifPresent(libraryItem -> this.moveLibraryItem(this.allLibraryItems, this.checkedOutLibraryItems, libraryItem));
        checkedOutItem.ifPresent(libraryItem -> this.register.put(libraryItem, this.currentUser));
        return checkedOutItem;
    }

    public boolean returnLibraryItem(Class<? extends LibraryItem> itemType, String libraryItemName) {
        Optional<LibraryItem> allItemsHavingSameName = this.findLibraryItem(this.checkedOutLibraryItems, libraryItemName);
        Optional<LibraryItem> returnItem = allItemsHavingSameName.filter(libraryItem -> libraryItem.getClass().equals(itemType));
        returnItem.ifPresent(item -> moveLibraryItem(this.checkedOutLibraryItems, this.allLibraryItems, item));
        return returnItem.isPresent();
    }

    public boolean currentUserCheckedOutItem(Class<? extends LibraryItem> type, String itemName) {
        Optional<LibraryItem> item = this.checkedOutLibraryItems
                .stream()
                .filter(libraryItem -> libraryItem.getClass() == type)
                .filter(libraryItem -> libraryItem.hasSameName(itemName))
                .findFirst();
        return item.isPresent() && this.register.get(item.get()).equals(this.currentUser);
    }

    public boolean isItemCheckedOut(Class<? extends LibraryItem> itemType, String itemName) {
        Optional<LibraryItem> libraryItem = this.findLibraryItem(checkedOutLibraryItems, itemName);
        return libraryItem.map(libraryItem1 -> libraryItem1.getClass().equals(itemType)).orElse(false);
    }

    private void moveLibraryItem(List<LibraryItem> source, List<LibraryItem> destination, LibraryItem libraryItem) {
        source.remove(libraryItem);
        destination.add(libraryItem);
    }

    public Optional<User> userWithGivenCredentials(String libraryNo, String password) {
        return this.users.stream().filter(user -> user.hasSameCredentials(libraryNo, password)).findFirst();
    }

    public boolean changeCurrentUser(User user) {
        if (user instanceof GuestUser) {
            this.currentUser = user;
            return true;
        }
        return changeUserToANonGuestUser(user);
    }

    public User currentUser() {
        return this.currentUser;
    }

    private Optional<LibraryItem> findLibraryItem(List<LibraryItem> libraryItems, String libraryItemName) {
        return libraryItems.stream()
                .filter(libraryItem -> libraryItem.hasSameName(libraryItemName))
                .findFirst();
    }

    private boolean changeUserToANonGuestUser(User user) {
        if (!hasUser(user)) {
            return false;
        }
        this.currentUser = user;
        return true;
    }

    private boolean hasUser(User currentUser) {
        return this.users.stream().anyMatch(user -> user.equals(currentUser));
    }

}
