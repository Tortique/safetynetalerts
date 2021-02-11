package com.safetynet.safetynetalerts.controller.Endpoints;

import com.safetynet.safetynetalerts.Service.Endpoints.FireService;
import java.util.List;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Data
@RestController
public class FireController {

    @Autowired
    FireService service;

    @GetMapping("/fire")
    public List<String> getFire(@RequestParam String address) {
        return service.getFire(address);
    }
}
