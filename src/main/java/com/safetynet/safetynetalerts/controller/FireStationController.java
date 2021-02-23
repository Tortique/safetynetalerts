package com.safetynet.safetynetalerts.controller;

import com.safetynet.safetynetalerts.Service.FireStationService;
import com.safetynet.safetynetalerts.Service.Result;
import com.safetynet.safetynetalerts.model.FireStation;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Data
@RestController
public class FireStationController {

    @Autowired
    private FireStationService service;

    @GetMapping("/firestations")
    public Map<String, FireStation> getfirestations() {
        return service.getFireStation();
    }

    @DeleteMapping("/firestation/deleteAddress/{address}")
    public Result deleteAddressOfFireStation(@PathVariable("address") String address) {
        return service.deleteAddressOfFireStation(address);
    }

    @DeleteMapping("/firestation/deleteStation/{station}")
    public Result deleteFireStation(@PathVariable("station") String station) {
        return service.deleteFireStation(station);
    }

    @PostMapping("/firestation")
    public FireStation saveFireStation(@RequestBody FireStation fireStation) {
        return service.saveFireStation(fireStation);
    }

    @PutMapping("/firestation/{station}/{address}")
    public Result updateFireStationOfAddress(@PathVariable("station") String station, @PathVariable("address") String address, @RequestBody FireStation fireStation) {
        return service.updateFireStationOfAddress(fireStation, station, address);
    }
}
