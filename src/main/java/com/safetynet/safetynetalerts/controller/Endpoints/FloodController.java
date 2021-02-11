package com.safetynet.safetynetalerts.controller.Endpoints;

import com.safetynet.safetynetalerts.Service.Endpoints.FloodService;
import com.safetynet.safetynetalerts.dto.PhoneAndMedical;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Data
@RestController
public class FloodController {

    @Autowired
    FloodService floodService;

    @GetMapping("/flood")
    public Map<String, Map<String,List<PhoneAndMedical>>> getFlood(@RequestParam List<String> stations) {
        return floodService.getFlood(stations);
    }
}
