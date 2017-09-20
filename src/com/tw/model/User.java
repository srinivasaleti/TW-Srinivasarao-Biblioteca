package com.tw.model;

//Represents a person
public class User {

    private final String name;
    private final String libraryNo;
    private final String password;
    private final String email;
    private final String phoneNumber;

    public User(String name, String libraryNo, String password, String email, String phoneNumber) {
        this.name = name;
        this.libraryNo = libraryNo;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public boolean hasSameCredentials(String libraryNo, String password) {
        return this.libraryNo.equals(libraryNo) && this.password.equals(password);
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (this.getClass() != other.getClass()) {
            return false;
        }
        User that = (User) other;
        return this.name.equals(that.name) &&
                this.libraryNo.equals(that.libraryNo) &&
                this.password.equals(that.password) &&
                this.email.equals(that.email) &&
                this.phoneNumber.equals(that.phoneNumber);
    }

}
