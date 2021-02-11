package com.safetynet.safetynetalerts.controller.Endpoints;

import com.safetynet.safetynetalerts.Service.Endpoints.ChildAlertService;
import com.safetynet.safetynetalerts.dto.Adult;
import com.safetynet.safetynetalerts.dto.Child;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Data
@RestController
public class ChildAlertController {

    @Autowired
    ChildAlertService service;

    @GetMapping("/childAlert")
    public Map<List<Child>, List<Adult>> getChildAlert(@RequestParam String address) {
        return service.getChildAlert(address);
    }

}
