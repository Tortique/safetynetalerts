package com.safetynet.safetynetalerts.model;

import com.jsoniter.annotation.JsonProperty;
import com.jsoniter.fuzzy.MaybeStringIntDecoder;
import lombok.Data;


@Data
public class Person {
    @JsonProperty(decoder = MaybeStringIntDecoder.class)
    private String firstName;

    private String lastName;

    private String address;

    private String city;

    private String zip;

    private String phone;

    private String email;

    public Person(String firstName, String lastName, String address, String city, String zip, String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.zip = zip;
        this.phone = phone;
        this.email = email;
    }

    public Person() {

    }
}
