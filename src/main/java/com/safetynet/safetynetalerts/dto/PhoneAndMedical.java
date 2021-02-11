package com.safetynet.safetynetalerts.dto;

import lombok.Data;

@Data
public class PhoneAndMedical {
    private String firstName;
    private String lastName;
    private String phone;
    private String age;
    private String medications;
    private String allergies;

    public PhoneAndMedical(String firstName, String lastName, String phone, String age, String medications, String allergies) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.age = age;
        this.medications = medications;
        this.allergies = allergies;
    }

    @Override
    public String toString() {
        return "PhoneAndMedical{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", age=" + age +
                ", medications='" + medications + '\'' +
                ", allergies='" + allergies + '\'' +
                '}';
    }
}
