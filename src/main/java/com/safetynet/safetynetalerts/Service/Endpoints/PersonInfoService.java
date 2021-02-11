package com.safetynet.safetynetalerts.Service.Endpoints;

import com.safetynet.safetynetalerts.Service.PersonAge;
import com.safetynet.safetynetalerts.dao.JSONReader;
import com.safetynet.safetynetalerts.dto.PersonInfo;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Service
public class PersonInfoService implements IPersonService {

    @Autowired
    JSONReader jsonReader;

    @Autowired
    PersonAge personAge;

    private List<Person> listPerson;
    private List<MedicalRecord> listMedical;

    public PersonInfoService(JSONReader jsonReader) throws IOException {
        this.listPerson = jsonReader.readPerson();
        this.listMedical = jsonReader.readMedicalRecord();
    }

    public List<PersonInfo> getPersonInfo(String firstName, String lastName) {
        List<Person> personByName = listPerson.stream().filter(person -> person.getFirstName().contains(firstName) && person.getLastName().contains(lastName)).collect(Collectors.toList());
        List<MedicalRecord> medicalRecords = listMedical.stream()
                .filter(medicalrecord -> personByName.stream()
                        .anyMatch(person -> medicalrecord.getFirstName().contains(firstName) && medicalrecord.getLastName().contains(lastName))).collect(Collectors.toList());
        for(MedicalRecord medicalRecord : medicalRecords) {
            String age = String.valueOf(personAge.getPersonAge(medicalRecord.getBirthDate()));
            medicalRecord.setBirthDate(age);
        }
        List<PersonInfo> personInfos = new ArrayList<>();
        for(MedicalRecord medicalRecord : medicalRecords) {
            String getAddress = personByName.stream().filter(person -> person.getFirstName().contains(medicalRecord.getFirstName()) && person.getLastName().contains(medicalRecord.getLastName())).map(Person::getAddress).findFirst().get();
            String getEmail = personByName.stream().filter(person -> person.getFirstName().contains(medicalRecord.getFirstName()) && person.getLastName().contains(medicalRecord.getLastName())).map(Person::getEmail).findFirst().get();
            personInfos.add(new PersonInfo(medicalRecord.getFirstName(),
                    medicalRecord.getLastName(),
                    getAddress,
                    medicalRecord.getBirthDate(),
                    getEmail,
                    medicalRecord.getMedications(),
                    medicalRecord.getAllergies()));
        }
        return personInfos;
    }
}
