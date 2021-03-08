package com.safetynet.safetynetalerts.Service;


import com.safetynet.safetynetalerts.dao.JSONReader;
import com.safetynet.safetynetalerts.dao.Reader;
import com.safetynet.safetynetalerts.model.MedicalRecord;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class MedicalRecordServiceTests {
    private IMedicalRecordService service;
    private MedicalRecord medicalRecord;

    private Reader reader;

    @BeforeEach
    public void setUp() throws IOException {
        medicalRecord = new MedicalRecord();
        reader = Mockito.mock(JSONReader.class);
        List<MedicalRecord> medicalRecords = new ArrayList<>();
        medicalRecords.add(new MedicalRecord("John", "Wayne", "01/01/1901", "[\"paracetamol 1000mg\"]", "[\"penicillin\"]"));
        when(reader.readMedicalRecord()).thenReturn(medicalRecords);
        service = new MedicalRecordService(reader);
    }

    @Test
    public void getMedicalRecordTest() {
        List<MedicalRecord> getMedicalRecord = service.getMedicalRecords();
        assertNotNull(getMedicalRecord);
    }

    @Test
    public void deleteMedicalRecordTest() {
        service.deleteMedicalRecord("John", "Wayne");
        assertNull(medicalRecord.getFirstName(), medicalRecord.getLastName());
    }

    @Test
    public void saveMedicalRecordTest() {
        MedicalRecord medicalRecord = new MedicalRecord("Billy", "Bob", "02/03/1984", null, "[\"raspberry\"]");
        MedicalRecord medicalRecordSaved = service.saveMedicalRecord(medicalRecord);
        assertNotNull(medicalRecordSaved);
    }

    @Test
    public void updateMedicalRecordTest() {
        MedicalRecord medicalRecord = new MedicalRecord("John", "Wayne", "01/01/1991", "[\"paracetamol 1000mg\"]", "[\"penicillin\"]" );
        service.updateMedicalRecord(medicalRecord, "John", "Wayne");
        assertEquals("01/01/1991", medicalRecord.getBirthDate());
    }
}
