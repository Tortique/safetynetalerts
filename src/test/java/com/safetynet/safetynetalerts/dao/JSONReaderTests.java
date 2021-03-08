package com.safetynet.safetynetalerts.dao;


import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class JSONReaderTests {

    @Test
    public void readPersonTest() throws IOException {
        List<Person> listPerson = new JSONReader().readPerson();
        assertThat(listPerson.isEmpty()).isFalse();
    }

    @Test
    public void readMedicalRecordTest() throws IOException {
        List<MedicalRecord> listMedical = new JSONReader().readMedicalRecord();
        assertThat(listMedical.isEmpty()).isFalse();
    }

    @Test
    public void readFireStationTest() throws IOException {
        Map<String, FireStation> listFireStation = new JSONReader().readFireStation();
        assertThat(listFireStation.isEmpty()).isFalse();
    }
}
