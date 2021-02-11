package com.safetynet.safetynetalerts.controller.Endpoints;

import com.safetynet.safetynetalerts.Service.Endpoints.FireStationCoverageService;
import lombok.Data;

import java.text.ParseException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Data
@RestController
public class FireStationCoverageController {

    @Autowired
    FireStationCoverageService service;

    @GetMapping("/firestaion")
    public List<String> getFireStationCoverage(@RequestParam String station ) {
        return service.getFireStationCoverage(station);
    }
}
