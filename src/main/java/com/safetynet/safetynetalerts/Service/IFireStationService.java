package com.safetynet.safetynetalerts.Service;

import com.safetynet.safetynetalerts.model.FireStation;

import java.util.Map;

public interface IFireStationService {
    Map<String, FireStation> getFireStation();

    Result deleteAddressOfFireStation(String address);

    Result deleteFireStation(String station);

    FireStation saveFireStation(FireStation fireStation);

    Result updateFireStationOfAddress(FireStation fireStation, String station, String address);
}
