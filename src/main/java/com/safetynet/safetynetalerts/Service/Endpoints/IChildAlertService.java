package com.safetynet.safetynetalerts.Service.Endpoints;

import com.safetynet.safetynetalerts.dto.Adult;
import com.safetynet.safetynetalerts.dto.Child;

import java.util.List;
import java.util.Map;


public interface IChildAlertService {
    Map<List<Child>, List<Adult>> getChildAlert(String address);
}
