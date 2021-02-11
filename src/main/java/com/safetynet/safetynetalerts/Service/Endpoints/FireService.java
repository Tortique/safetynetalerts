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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@Service
public class FireService implements IFireService {

    @Autowired
    JSONReader jsonReader;

    @Autowired
    PersonAge personAge;

    private List<Person> listPerson;
    private Map<String, FireStation> map;
    private List<MedicalRecord> listMedical;

    public FireService(JSONReader jsonReader) throws IOException {
        this.listPerson = jsonReader.readPerson();
        this.map = jsonReader.readFireStation();
        this.listMedical = jsonReader.readMedicalRecord();
    }
    public List<String> getFire(String address) {
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
        List<String> getFire = new ArrayList<>();
        String getStation = map.entrySet().stream().filter(entry -> entry.getValue().getAddresses().contains(address)).map(Map.Entry::getKey).findFirst().get();
        getFire.add(String.valueOf(phoneAndMedicals));
        getFire.add(getStation);
        return getFire;
    }
}
