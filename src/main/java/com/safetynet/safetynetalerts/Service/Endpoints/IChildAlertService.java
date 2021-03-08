package com.safetynet.safetynetalerts.Service.Endpoints;

import com.safetynet.safetynetalerts.dto.ChildrenAndAdults;

import java.util.List;

public interface IChildAlertService {
    List<ChildrenAndAdults> getChildAlert(String address) throws Exception;
}
