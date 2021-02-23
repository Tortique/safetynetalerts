package com.safetynet.safetynetalerts.dto;

import lombok.Data;

import java.util.List;

@Data
public class FireCoverage {
    private List<PersonCover> personCoverList;
    private int numberOfAdults;
    private int numberOfChildren;

    public FireCoverage(List<PersonCover> personCoverList, int numberOfAdults, int numberOfChildren) {
        this.personCoverList = personCoverList;
        this.numberOfAdults = numberOfAdults;
        this.numberOfChildren = numberOfChildren;
    }

    @Override
    public String toString() {
        return "FireCoverage{" +
                "personCoverList=" + personCoverList +
                ", numberOfAdults='" + numberOfAdults + '\'' +
                ", numberOfChildren='" + numberOfChildren + '\'' +
                '}';
    }
}
