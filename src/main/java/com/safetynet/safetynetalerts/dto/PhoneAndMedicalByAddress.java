package com.safetynet.safetynetalerts.dto;

import lombok.Data;

import java.util.*;

@Data
public class PhoneAndMedicalByAddress {
    private String address;
    private List<PhoneAndMedical> phoneAndMedicalList;

    public PhoneAndMedicalByAddress() {
    }

    public PhoneAndMedicalByAddress(String address, List<PhoneAndMedical> phoneAndMedicalList) {
        this.address = address;
        this.phoneAndMedicalList = phoneAndMedicalList;
    }
}
