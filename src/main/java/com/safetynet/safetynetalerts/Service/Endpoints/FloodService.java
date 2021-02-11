package com.safetynet.safetynetalerts.Service.Endpoints;

import com.safetynet.safetynetalerts.Service.PersonAge;
import com.safetynet.safetynetalerts.dao.JSONReader;
import com.safetynet.safetynetalerts.dto.PhoneAndMedical;
import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Data
@Service
public class FloodService implements IFloodService{

    @Autowired
    JSONReader jsonReader;

    @Autowired
    PersonAge personAge;

    private List<Person> listPerson;
    private Map<String, FireStation> map;
    private List<MedicalRecord> listMedical;

    public FloodService(JSONReader jsonReader) throws IOException {
        this.listPerson = jsonReader.readPerson();
        this.listMedical = jsonReader.readMedicalRecord();
        this.map = jsonReader.readFireStation();
    }

    public Map<String, Map<String,List<PhoneAndMedical>>> getFlood(List<String> stations) {
        Map<String, Map<String,List<PhoneAndMedical>>> getFlood = new HashMap<>();

        Map<String,List<PhoneAndMedical>> getPeople = new HashMap<>();

        for(String station : stations) {
           List<String> addresses = new ArrayList<>(map.get(station).getAddresses());
            for(String address : addresses) {
                getPeople.put(address,getPhoneAndMedical(address));
                }
            getFlood.put(station, getPeople);
            }

        return getFlood;
    }

    private List<PhoneAndMedical> getPhoneAndMedical(String address) {
        List<Person> inhabitants = listPerson.stream().filter(person -> person.getAddress().contains(address)).collect(Collectors.toList());
        List<MedicalRecord> medicalRecords = listMedical.stream()
                .filter(medicalRecord -> inhabitants.stream()
                        .anyMatch(person -> medicalRecord.getFirstName().contains(person.getFirstName()) && medicalRecord.getLastName().contains(person.getLastName()))).collect(Collectors.toList());
        for(MedicalRecord medicalRecord : medicalRecords) {
            String age = String.valueOf(personAge.getPersonAge(medicalRecord.getBirthDate()));
            medicalRecord.setBirthDate(age);
        }
        List<PhoneAndMedical> phoneAndMedicals = new ArrayList<>();
        for(MedicalRecord medicalRecord : medicalRecords) {
            String getPhoneNumber =  inhabitants.stream().filter(person -> person.getFirstName().contains(medicalRecord.getFirstName()) && person.getLastName().contains(medicalRecord.getLastName()))
                    .map(Person::getPhone).findFirst().get();
            phoneAndMedicals.add(new PhoneAndMedical(medicalRecord.getFirstName(),
                    medicalRecord.getLastName(),
                    getPhoneNumber,
                    medicalRecord.getBirthDate(),
                    medicalRecord.getMedications(),
                    medicalRecord.getAllergies()));
        }
        return phoneAndMedicals;
    }
}
