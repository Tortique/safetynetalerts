package com.safetynet.safetynetalerts.Service.Endpoints;

import java.text.ParseException;
import java.util.List;

public interface IFireStationCoverageService {
    List<String> getFireStationCoverage(String station) throws ParseException;
}
