package com.in28minutes.rest.webservices.restfulwebservices.versioning;

public class PersonV2 {
    private  final  Name name;

    @Override
    public String toString() {
        return "PersonV2{" +
            "name=" + name +
            '}';
    }

    public Name getName() {
        return name;
    }

    public PersonV2(final Name name) {
        this.name = name;
    }
}
