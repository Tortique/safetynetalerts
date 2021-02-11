package com.safetynet.safetynetalerts.dto;

import lombok.Data;

@Data
public class PersonCover {
    private String firstName;

    private String lastName;

    private String address;

    private String phone;

    public PersonCover(String firstName, String lastName, String address, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "PersonCover{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
