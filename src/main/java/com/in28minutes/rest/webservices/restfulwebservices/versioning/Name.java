package com.in28minutes.rest.webservices.restfulwebservices.versioning;

public class Name {
    final private String firstName;
    final private String secondName;
    public Name(final String firstName, final String secondName) {
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getFirstName() {
        return firstName;
    }
    @Override
    public String toString() {
        return "Name{" +
            "firstName='" + firstName + '\'' +
            ", secondName='" + secondName + '\'' +
            '}';
    }


}
