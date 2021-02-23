package com.safetynet.safetynetalerts.Service;

import com.safetynet.safetynetalerts.model.MedicalRecord;

import java.util.List;

public interface IMedicalRecordService {
    List<MedicalRecord> getMedicalRecords();

    Result deleteMedicalRecord(String firstName, String lastName);

    MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord);

    Result updateMedicalRecord(MedicalRecord medicalRecord, String firstName, String lastName);
}
