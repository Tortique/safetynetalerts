package com.safetynet.safetynetalerts.Service.Endpoints;

import com.safetynet.safetynetalerts.dto.PhoneAndMedical;

import java.util.List;
import java.util.Map;

public interface IFloodService {
    Map<String, Map<String,List<PhoneAndMedical>>> getFlood (List<String> stations);
}
