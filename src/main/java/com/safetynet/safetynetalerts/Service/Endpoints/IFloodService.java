package com.safetynet.safetynetalerts.Service.Endpoints;

import com.safetynet.safetynetalerts.dto.StationAndAddress;

import java.util.List;

public interface IFloodService {
   List<StationAndAddress> getFlood (List<String> stations);
}
