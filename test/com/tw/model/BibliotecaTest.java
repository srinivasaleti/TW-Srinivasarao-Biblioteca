package com.tw.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.tw.view.ConsoleIO.LINE_SEPARATOR;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BibliotecaTest {

    @Test
    void expectedNothingWhenThereAreNoLibraryItemsInLibrary() {
        Biblioteca biblioteca = new Biblioteca(null);
        String nothing = "";

        assertEquals(nothing, biblioteca.representationOfAllLibraryItems());
    }

    @Test
    void shouldAskRepresentationOfLibraryItemInTheBibliotecaToGetRepresentationOfAllLibraryItems() {
        LibraryItem libraryItemInTheLibrary = mock(LibraryItem.class);
        List<LibraryItem> libraryItems = Collections.singletonList(libraryItemInTheLibrary);
        Biblioteca biblioteca = new Biblioteca(libraryItems);

        biblioteca.representationOfAllLibraryItems();

        verify(libraryItemInTheLibrary).representation();
    }

    @Test
    void shouldAskRepresentationOfEveryLibraryItemInBibliotecaToGetRepresentationOfAllLibraryItems() {
        LibraryItem libraryItem = mock(LibraryItem.class);
        LibraryItem anotherLibraryItem = mock(LibraryItem.class);
        List<LibraryItem> libraryItems = Arrays.asList(libraryItem, anotherLibraryItem);
        Biblioteca biblioteca = new Biblioteca(libraryItems);

        biblioteca.representationOfAllLibraryItems();

        verify(libraryItem).representation();
        verify(anotherLibraryItem).representation();
    }

    @Test
    void expectedRepresentationOfAllLibraryItemsInBiblioteca() {
        LibraryItem aLibraryItem = mock(LibraryItem.class);
        LibraryItem anotherLibraryItem = mock(LibraryItem.class);
        List<LibraryItem> libraryItems = Arrays.asList(aLibraryItem, anotherLibraryItem);
        Biblioteca biblioteca = new Biblioteca(libraryItems);
        String aLibraryItemRepresentation = "LibraryItem1";
        String anotherLibraryItemRepresentation = "LibraryItem2";
        String expected = aLibraryItemRepresentation + LINE_SEPARATOR + anotherLibraryItemRepresentation;

        when(aLibraryItem.representation()).thenReturn(aLibraryItemRepresentation);
        when(anotherLibraryItem.representation()).thenReturn(anotherLibraryItemRepresentation);
        biblioteca.representationOfAllLibraryItems();

        assertEquals(expected, biblioteca.representationOfAllLibraryItems());
    }

    @Test
    void shouldCheckoutALibraryItemFromBibliotecaIfItIsAvailable() {
        LibraryItem aLibraryItem = new Book("book1", "author1", 1996);
        LibraryItem anotherLibraryItem = new Book("book1", "author2", 1996);
        List<LibraryItem> libraryItems = Arrays.asList(aLibraryItem, anotherLibraryItem);
        Biblioteca biblioteca = new Biblioteca(libraryItems);

        assertEquals(Optional.of(aLibraryItem), biblioteca.checkoutALibraryItem("book1"));
    }

    @Test
    void shouldNotCheckoutALibraryItemIfItIsNotAvailableInBiblioteca() {
        LibraryItem aLibraryItem = new Book("book1", "author1", 1996);
        LibraryItem anotherLibraryItem = new Book("book2", "author2", 1996);
        List<LibraryItem> libraryItems = Arrays.asList(aLibraryItem, anotherLibraryItem);
        Biblioteca biblioteca = new Biblioteca(libraryItems);

        assertEquals(Optional.empty(), biblioteca.checkoutALibraryItem("book3"));
    }

    @Test
    void shouldRemoveLibraryItemFromAllLibraryItems() {
        LibraryItem aLibraryItem = new Book("book1", "author1", 1996);
        LibraryItem anotherLibraryItem = new Book("book2", "author2", 1996);
        List<LibraryItem> libraryItems = Arrays.asList(aLibraryItem, anotherLibraryItem);
        Biblioteca biblioteca = new Biblioteca(libraryItems);

        biblioteca.checkoutALibraryItem("book1");

        assertEquals(anotherLibraryItem.representation(), biblioteca.representationOfAllLibraryItems());
    }

    @Test
    void shouldReturnTrueIfNoLibraryItemsInBiblioteca() {
        Biblioteca biblioteca = new Biblioteca(null);

        assertTrue(biblioteca.isEmpty());
    }

    @Test
    void shouldReturnFalseIfLibraryItemsAvailableInBiblioteca() {
        LibraryItem aLibraryItem = new Book("book1", "author1", 1996);
        LibraryItem anotherLibraryItem = new Book("book2", "author2", 1996);
        List<LibraryItem> libraryItems = Arrays.asList(aLibraryItem, anotherLibraryItem);
        Biblioteca biblioteca = new Biblioteca(libraryItems);

        assertFalse(biblioteca.isEmpty());
    }

    @Test
    void shouldReturnACheckedOutLibraryItem() {
        LibraryItem aLibraryItem = new Book("book1", "author1", 1996);
        LibraryItem anotherLibraryItem = new Book("book2", "author2", 1996);
        List<LibraryItem> libraryItems = Arrays.asList(aLibraryItem, anotherLibraryItem);
        Biblioteca biblioteca = new Biblioteca(libraryItems);

        biblioteca.checkoutALibraryItem("book1");

        assertTrue(biblioteca.returnLibraryItem("book1"));
    }

    @Test
    void shouldNotReturnAUnCheckedOutLibraryItem() {
        LibraryItem aLibraryItem = new Book("book1", "author1", 1996);
        LibraryItem anotherLibraryItem = new Book("book2", "author2", 1996);
        List<LibraryItem> libraryItems = Arrays.asList(aLibraryItem, anotherLibraryItem);
        Biblioteca biblioteca = new Biblioteca(libraryItems);

        biblioteca.checkoutALibraryItem("book1");

        assertFalse(biblioteca.returnLibraryItem("book2"));
    }

    @Test
    void shouldAddLibraryItemToBibliotecaAfterReturningALibraryItem() {
        LibraryItem aLibraryItem = new Book("book1", "author1", 1996);
        LibraryItem anotherLibraryItem = new Book("book2", "author2", 1996);
        List<LibraryItem> libraryItems = Arrays.asList(aLibraryItem, anotherLibraryItem);
        Biblioteca biblioteca = new Biblioteca(libraryItems);
        String actualRepresentation = anotherLibraryItem.representation() + LINE_SEPARATOR + aLibraryItem.representation();

        biblioteca.checkoutALibraryItem("book1");
        biblioteca.returnLibraryItem("book1");

        assertEquals(biblioteca.representationOfAllLibraryItems(), actualRepresentation);
    }

}
