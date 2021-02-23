package com.safetynet.safetynetalerts.Service.Endpoints;

import com.safetynet.safetynetalerts.dto.PhoneAndMedical;
import com.safetynet.safetynetalerts.dto.PhoneAndMedicalWithStation;

import java.util.List;

public interface IFireService {
    List<PhoneAndMedicalWithStation> getFire(String address);
}
