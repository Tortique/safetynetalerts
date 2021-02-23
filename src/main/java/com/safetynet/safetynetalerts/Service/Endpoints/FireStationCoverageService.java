package com.safetynet.safetynetalerts.Service.Endpoints;


import com.safetynet.safetynetalerts.Service.PersonAge;
import com.safetynet.safetynetalerts.dao.JSONReader;
import com.safetynet.safetynetalerts.dao.Reader;
import com.safetynet.safetynetalerts.dto.FireCoverage;
import com.safetynet.safetynetalerts.dto.PersonCover;
import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;


@Data
@Service
public class FireStationCoverageService implements IFireStationCoverageService {
    private static final Logger logger = LogManager.getLogger("FireStationCoverageService");

    @Autowired
    JSONReader jsonReader;

    PersonAge personAge = new PersonAge();

    private Map<String, FireStation> map;
    private List<MedicalRecord> listMedical;
    private List<Person> listPerson;

    public FireStationCoverageService(Reader jsonReader) throws IOException {
        this.map = jsonReader.readFireStation();
        this.listMedical = jsonReader.readMedicalRecord();
        this.listPerson = jsonReader.readPerson();
    }

    public List<FireCoverage> getFireStationCoverage(String stationNumber) {
        try {
            logger.debug("Entering getFireStationCoverage");
            List<PersonCover> getPersonCover = map.get(stationNumber).getAddresses().stream()
                    .flatMap(address -> listPerson.stream()
                            .filter(person -> person.getAddress().contains(address))
                            .map(person ->
                            {
                                PersonCover personCover = new PersonCover();
                                personCover.setFirstName(person.getFirstName());
                                personCover.setLastName(person.getLastName());
                                personCover.setAddress(person.getAddress());
                                personCover.setPhone(person.getPhone());
                                return personCover;
                            })).collect(Collectors.toList());
            logger.debug("Count Adults and Children");
            List<MedicalRecord> listMedicalForPersonCover = listMedical.stream()
                    .filter(medicalRecord -> getPersonCover.stream()
                            .anyMatch(person -> medicalRecord.getFirstName().contains(person.getFirstName()) && medicalRecord.getLastName().contains(person.getLastName()))).collect(Collectors.toList());
            int numberOfAdults = getNumberOfAdults(listMedicalForPersonCover);
            int numberOfChildren = getNumberOfChildren(listMedicalForPersonCover);
            logger.debug("Success count");
            List<FireCoverage> listCoverage = new ArrayList<>();
            listCoverage.add(new FireCoverage(getPersonCover, numberOfAdults, numberOfChildren));

            logger.info("FireStation Coverage find successfully");
            return listCoverage;
        } catch (Exception e) {
            logger.error("Error finding PersonCover data");
            throw e;
        }
    }

    private int getNumberOfAdults(List<MedicalRecord> listMedicalForPersonCover) {
        try {
            int numberOfAdults = 0;
            for (MedicalRecord person : listMedicalForPersonCover) {
                int age = personAge.getPersonAge(person.getBirthDate());
                if (age > 18) {
                    numberOfAdults++;
                }
            }

            return numberOfAdults;
        } catch (Exception e) {
            logger.error("Error can't create Adults List");
            throw e;
        }
    }

    private int getNumberOfChildren(List<MedicalRecord> listMedicalForPersonCover) {
        try {
            int numberOfChildren = 0;
            for (MedicalRecord person : listMedicalForPersonCover) {
                int age = personAge.getPersonAge(person.getBirthDate());
                if (age <= 18) {
                    numberOfChildren++;
                }
            }

            return numberOfChildren;
        } catch (Exception e) {
            logger.error("Error can't create Children List");
            throw e;
        }
    }

}
