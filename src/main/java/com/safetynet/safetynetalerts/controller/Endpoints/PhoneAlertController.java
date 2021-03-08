package com.safetynet.safetynetalerts.controller.Endpoints;

import com.safetynet.safetynetalerts.Service.Endpoints.PhoneAlertService;
import com.safetynet.safetynetalerts.dto.PhoneWithName;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller mapping GET for PhoneAlertService and PhoneAlert endpoint
 */
@Data
@RestController
public class PhoneAlertController {
    @Autowired
    PhoneAlertService service;

    @GetMapping("/phoneAlert")
    public List<PhoneWithName> getPhoneAlert(@RequestParam String firestation) {
        return service.getPhoneAlert(firestation);
    }

}
