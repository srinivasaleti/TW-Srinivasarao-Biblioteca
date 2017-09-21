package com.tw.model;

//Represents a normal user
public class GuestUser implements User {

    @Override
    public boolean hasSameCredentials(String libraryNo, String password) {
        return false;
    }

}
