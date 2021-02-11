package com.safetynet.safetynetalerts.Service.Endpoints;

import com.safetynet.safetynetalerts.Service.PersonAge;
import com.safetynet.safetynetalerts.dao.JSONReader;
import com.safetynet.safetynetalerts.dto.Adult;
import com.safetynet.safetynetalerts.dto.Child;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@Service
public class ChildAlertService implements IChildAlertService{
    private static Logger logger = LogManager.getLogger("ChildAlertService");

    @Autowired
    JSONReader jsonReader;

    @Autowired
    PersonAge personAge;

    private List<Person> listPerson;
    private List<MedicalRecord> listMedical;

    public ChildAlertService (JSONReader jsonReader) throws IOException {
        this.listPerson = jsonReader.readPerson();
        this.listMedical = jsonReader.readMedicalRecord();
    }
    public Map<List<Child>, List<Adult>> getChildAlert(String address) {
        Map<List<Child>, List<Adult>> getChildAlert = new HashMap<>();
        List<Child> children = new ArrayList<>();
        List<Adult> adults = new ArrayList<>();
        List<Person> inhabitant = listPerson.stream().filter(person -> person.getAddress().contains(address)).collect(Collectors.toList());
        List<MedicalRecord> getBirthdate = listMedical.stream().filter(medicalRecord -> inhabitant.stream().anyMatch(person -> medicalRecord.getFirstName().contains(person.getFirstName()) && medicalRecord.getLastName().contains(person.getLastName()))).collect(Collectors.toList());
        for (MedicalRecord person : getBirthdate) {
            int age = personAge.getPersonAge(person.getBirthDate());
            if (age <= 18) {
                children.add(new Child(person.getFirstName(),person.getLastName(),age));
            } else {
                adults.add(new Adult(person.getFirstName(), person.getLastName()));
            }
        }
        if(children.isEmpty()) {
            logger.info("No children in this house");
        } else {
            getChildAlert.put(children, adults);
        }
        return getChildAlert;
    }
}
