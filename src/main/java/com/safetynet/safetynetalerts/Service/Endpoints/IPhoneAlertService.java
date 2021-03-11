package com.safetynet.safetynetalerts.Service.Endpoints;

import com.safetynet.safetynetalerts.dto.PhoneWithName;

import java.util.List;

public interface IPhoneAlertService {
    List<PhoneWithName> getPhoneAlert(String station);
}
