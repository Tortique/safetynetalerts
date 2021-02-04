package com.safetynet.safetynetalerts.dao;

import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface Reader {
    List<Person> readPerson() throws IOException;
    List<MedicalRecord> readMedicalRecord() throws IOException;
    Map<String,FireStation> readFireStation() throws IOException;
}
