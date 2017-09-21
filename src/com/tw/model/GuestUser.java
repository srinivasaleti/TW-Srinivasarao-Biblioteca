package com.tw.model;

//Represents a normal user
public class GuestUser implements User {

    @Override
    public boolean hasSameCredentials(String libraryNo, String password) {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        return this.getClass() == obj.getClass();
    }

}
