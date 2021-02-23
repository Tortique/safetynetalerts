package com.safetynet.safetynetalerts.Service.Endpoints;

import com.safetynet.safetynetalerts.Service.PersonAge;
import com.safetynet.safetynetalerts.dao.JSONReader;
import com.safetynet.safetynetalerts.dao.Reader;
import com.safetynet.safetynetalerts.dto.Adult;
import com.safetynet.safetynetalerts.dto.Child;
import com.safetynet.safetynetalerts.dto.ChildrenAndAdults;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@Service
public class ChildAlertService implements IChildAlertService {
    private static Logger logger = LogManager.getLogger("ChildAlertService");

    @Autowired
    JSONReader jsonReader;

    PersonAge personAge = new PersonAge();

    private List<Person> listPerson;
    private List<MedicalRecord> listMedical;

    public ChildAlertService(Reader jsonReader) throws IOException {
        this.listPerson = jsonReader.readPerson();
        this.listMedical = jsonReader.readMedicalRecord();
    }

    public List<ChildrenAndAdults> getChildAlert(String address) {
        try {
            logger.debug("Entering getChildAlert");
            Map<Boolean, List<MedicalRecord>> booleanListMap = listPerson.stream().filter(person -> person.getAddress().contains(address))
                    .flatMap(medicalRecord -> listMedical.stream()
                            .filter(person -> person.getFirstName().contains(medicalRecord.getFirstName()) && person.getLastName().contains(medicalRecord.getLastName())))
                    .collect(Collectors.partitioningBy(person -> personAge.getPersonAge(person.getBirthDate()) <= 18));
            logger.debug("Create ChildrenList");
            List<Child> childList = booleanListMap.get(true).stream().map(child ->
            {
                Child child1 = new Child();
                child1.setFirstName(child.getFirstName());
                child1.setLastName(child.getLastName());
                child1.setAge(personAge.getPersonAge(child.getBirthDate()));
                return child1;
            }).collect(Collectors.toList());
            logger.debug("Create AdultsList");
            List<Adult> adultList = booleanListMap.get(false).stream().map(adult ->
            {
                Adult adult1 = new Adult();
                adult1.setFirstName(adult.getFirstName());
                adult1.setLastName(adult.getLastName());
                return adult1;
            }).collect(Collectors.toList());
            logger.debug("Success create List");
            List<ChildrenAndAdults> getChildAlert = new ArrayList<>();
            getChildAlert.add(new ChildrenAndAdults(childList, adultList));

            logger.info("Children and Adults find successfully");
            return getChildAlert;
        } catch (Exception e) {
            logger.error("Error finding Children data");
            throw e;
        }
    }
}



