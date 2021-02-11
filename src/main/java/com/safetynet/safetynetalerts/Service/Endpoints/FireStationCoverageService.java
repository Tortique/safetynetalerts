package com.safetynet.safetynetalerts.Service.Endpoints;

import com.safetynet.safetynetalerts.Service.PersonAge;
import com.safetynet.safetynetalerts.dao.JSONReader;
import com.safetynet.safetynetalerts.dto.PersonCover;
import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;


@Data
@Service
public class FireStationCoverageService implements IFireStationCoverageService {

    @Autowired
    JSONReader jsonReader;

    @Autowired
    PersonAge personAge;

    private Map<String, FireStation> map;
    private List<MedicalRecord> listMedical;
    private List<Person> listPerson;

    public FireStationCoverageService(JSONReader jsonReader) throws IOException {
        this.map = jsonReader.readFireStation();
        this.listMedical = jsonReader.readMedicalRecord();
        this.listPerson = jsonReader.readPerson();
    }
    public List<String> getFireStationCoverage(String station) {
        List<String> addresses = new ArrayList<>(map.get(station).getAddresses());
        List<Person> listPersonByAddress = listPerson.stream()
                .filter(person -> addresses.stream()
                .anyMatch(address -> person.getAddress().contains(address))).collect(Collectors.toList());
        List<PersonCover> listPersonCover = new ArrayList<>();
        for(Person person : listPersonByAddress) {
            listPersonCover.add(new PersonCover(person.getFirstName(), person.getLastName(), person.getAddress(), person.getPhone()));
        }
        List<MedicalRecord> listMedicalForPersonCover = listMedical.stream()
                .filter(medicalRecord -> listPersonCover.stream()
                        .anyMatch(person -> medicalRecord.getFirstName().contains(person.getFirstName()) && medicalRecord.getLastName().contains(person.getLastName()))).collect(Collectors.toList());
        int numberOfAdults = getNumberOfAdults(listMedicalForPersonCover);
        int numberOfChildren = getNumberOfChildren(listMedicalForPersonCover);

        List<String> listCoverage = new ArrayList<>();
        listCoverage.add(listPersonCover.toString());
        listCoverage.add("number of Adults : " + numberOfAdults);
        listCoverage.add("number of Children : " + numberOfChildren);

        return listCoverage;
    }

    private int getNumberOfAdults(List<MedicalRecord> listMedicalForPersonCover) {
        int numberOfAdults = 0;
        for(MedicalRecord person : listMedicalForPersonCover) {
            int age = personAge.getPersonAge(person.getBirthDate());
            if(age > 18) {
                numberOfAdults++;
            }
        }

        return numberOfAdults;
    }

    private int getNumberOfChildren(List<MedicalRecord> listMedicalForPersonCover) {
        int numberOfChildren = 0;
        for(MedicalRecord person : listMedicalForPersonCover) {
            int age = personAge.getPersonAge(person.getBirthDate());
            if(age <= 18) {
                numberOfChildren++;
            }
        }

        return numberOfChildren;
    }

}
