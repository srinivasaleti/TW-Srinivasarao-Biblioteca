package com.tw.model;

//Represents a normal user
public class GuestUser implements User {

    public static final String REPRESENTATION = "Guest user";

    @Override
    public boolean hasSameCredentials(String libraryNo, String password) {
        return false;
    }

    @Override
    public String representation() {
        return REPRESENTATION;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (this == object) {
            return true;
        }
        return this.getClass() == object.getClass();
    }

}
