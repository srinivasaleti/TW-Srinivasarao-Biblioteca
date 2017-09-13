package com.tw;

import com.tw.model.LibraryItem;

//Represents a film
public class Movie implements LibraryItem {

    public Movie(String name, int yearReleased, String director, String rating) {

    }

    @Override
    public String representation() {
        return null;
    }

    @Override
    public boolean hasSameName(String name) {
        return false;
    }

}
