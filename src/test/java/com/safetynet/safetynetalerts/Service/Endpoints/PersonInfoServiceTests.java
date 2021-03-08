package com.safetynet.safetynetalerts.Service.Endpoints;

import com.safetynet.safetynetalerts.dao.JSONReader;
import com.safetynet.safetynetalerts.dao.Reader;
import com.safetynet.safetynetalerts.dto.PersonInfo;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class PersonInfoServiceTests {
    private IPersonInfoService service;
    private Person person;
    private MedicalRecord medicalRecord;

    private Reader reader;

    @BeforeEach
    public void setUp() throws IOException {
        person = new Person();
        medicalRecord = new MedicalRecord();
        reader = Mockito.mock(JSONReader.class);
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("John", "Wayne", "1 rue Pournousdeux", "Paris", "75000", "010203", "jwayneparis@mail.com"));
        List<MedicalRecord> medicalRecordList = new ArrayList<>();
        medicalRecordList.add(new MedicalRecord("John", "Wayne", "01/01/1991", "[\"paracetamol 1000mg\"]", "[\"penicillin\"]"));
        when(reader.readPerson()).thenReturn(personList);
        when(reader.readMedicalRecord()).thenReturn(medicalRecordList);
        service = new PersonInfoService(reader);
    }

    @Test
    public void getPersonInfoTest() {
        List<PersonInfo> expectedList = new ArrayList<>();
        expectedList.add(new PersonInfo("John", "Wayne", "1 rue Pournousdeux", "30", "jwayneparis@mail.com", "[\"paracetamol 1000mg\"]","[\"penicillin\"]"));
        List<PersonInfo> getPersonInfo = service.getPersonInfo("John", "Wayne");
        assertEquals(expectedList,getPersonInfo);
    }
}
