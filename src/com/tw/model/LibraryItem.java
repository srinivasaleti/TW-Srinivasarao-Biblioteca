package com.tw.model;

//Represents an item in biblioteca
public interface LibraryItem {

    String representation();

    boolean hasSameName(String name);

}
