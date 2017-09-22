package com.tw.model;

//Represents a person
public interface User {

    boolean hasSameCredentials(String libraryNo, String password);

    String representation();

}
