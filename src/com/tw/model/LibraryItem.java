package com.tw.model;

//Represents an item in biblioteca
public interface LibraryItem {

    public String representation();

    public boolean hasSameName(String name);

}
