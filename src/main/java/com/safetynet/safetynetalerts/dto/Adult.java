package com.safetynet.safetynetalerts.dto;

import lombok.Data;

@Data
public class Adult {
    private String firstName;
    private String lastName;

    public Adult (String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Adult() {

    }

    @Override
    public String toString() {
        return "Adult{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
