package com.tw.model;

//Represents a user who registered with biblioteca
public class LibraryUser implements User {

    private static final String REPRESENTATION_FORMAT = "%-35s %-35s %-35s";

    private final String name;
    private final String libraryNo;
    private final String password;
    private final String email;
    private final String phoneNumber;

    public LibraryUser(String name, String libraryNo, String password, String email, String phoneNumber) {
        this.name = name;
        this.libraryNo = libraryNo;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean hasSameCredentials(String libraryNo, String password) {
        return this.libraryNo.equals(libraryNo) && this.password.equals(password);
    }

    @Override
    public String representation() {
        return String.format(REPRESENTATION_FORMAT, this.name, this.email, this.phoneNumber);
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (this.getClass() != other.getClass()) {
            return false;
        }
        LibraryUser that = (LibraryUser) other;
        return this.name.equals(that.name) &&
                this.libraryNo.equals(that.libraryNo) &&
                this.password.equals(that.password) &&
                this.email.equals(that.email) &&
                this.phoneNumber.equals(that.phoneNumber);
    }

}
