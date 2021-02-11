package com.safetynet.safetynetalerts.dto;

import lombok.Data;

@Data
public class PhoneWithName {
    private String firstName;
    private String lastName;
    private String phone;

    public PhoneWithName(String firstName,String lastName, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "PhoneWithName{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
