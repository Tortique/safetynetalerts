package com.safetynet.safetynetalerts.Service.Endpoints;

import com.safetynet.safetynetalerts.dao.JSONReader;
import com.safetynet.safetynetalerts.dao.Reader;
import com.safetynet.safetynetalerts.dto.PhoneWithName;
import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.model.Person;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Data
@Service
public class PhoneAlertService implements IPhoneAlertService {
    private static final Logger logger = LogManager.getLogger("PhoneAlertService");

    @Autowired
    JSONReader jsonReader;

    private List<Person> listPerson;
    private Map<String, FireStation> map;

    public PhoneAlertService(Reader jsonReader) throws IOException {
        this.listPerson = jsonReader.readPerson();
        this.map = jsonReader.readFireStation();
    }

    public List<PhoneWithName> getPhoneAlert(String station) {
        try {
            logger.debug("Entering getPhoneAlert");
            List<PhoneWithName> listPhone = map.get(station).getAddresses().stream()
                    .flatMap(address -> listPerson.stream()
                            .filter(person -> person.getAddress().contains(address))
                            .map(phoneWithName ->
                            {
                                PhoneWithName phoneWithName1 = new PhoneWithName();
                                phoneWithName1.setFirstName(phoneWithName.getFirstName());
                                phoneWithName1.setLastName(phoneWithName.getLastName());
                                phoneWithName1.setPhone(phoneWithName.getPhone());
                                return phoneWithName1;
                            })).collect(Collectors.toList());
            logger.info("Phone data find successfully");
            return listPhone;
        } catch (Exception e) {
            logger.error("Error finding phone data", e);
            throw e;
        }
    }
}
