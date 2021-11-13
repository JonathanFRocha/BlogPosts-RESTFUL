package com.jonathanferreira.rest.restfulwebservices.versioning;

public class PersonV2 {
    private Name firstName;

    public PersonV2() {
    }

    public PersonV2(Name firstName) {
        this.firstName = firstName;
    }

    public Name getFirstName() {
        return firstName;
    }

    public void setFirstName(Name firstName) {
        this.firstName = firstName;
    }
}
