package com.safetynet.safetynetalerts.Service.Endpoints;

import com.safetynet.safetynetalerts.dto.FireCoverage;

import java.util.List;
import java.text.ParseException;

public interface IFireStationCoverageService {
    List<FireCoverage> getFireStationCoverage(String station) throws ParseException;
}
