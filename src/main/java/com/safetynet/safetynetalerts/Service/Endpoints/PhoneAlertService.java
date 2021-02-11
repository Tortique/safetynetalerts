package com.safetynet.safetynetalerts.Service.Endpoints;

import com.safetynet.safetynetalerts.dao.JSONReader;
import com.safetynet.safetynetalerts.dto.PhoneWithName;
import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.model.Person;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@Service
public class PhoneAlertService implements IPhoneAlertService {
    @Autowired
    JSONReader jsonReader;

    private List<Person> listPerson;
    private Map<String, FireStation> map;

    public PhoneAlertService(JSONReader jsonReader) throws IOException {
        this.listPerson = jsonReader.readPerson();
        this.map = jsonReader.readFireStation();
    }
    public List<PhoneWithName> getPhoneAlert(String station) {
        List<String> addresses = map.get(station).getAddresses().stream().collect(Collectors.toList());
        List<Person> listPersonByAddress = listPerson.stream()
                .filter(person -> addresses.stream()
                .anyMatch(address -> person.getAddress().contains(address))).collect(Collectors.toList());
        List<PhoneWithName> listPhone = new ArrayList<>();
        for(Person person : listPersonByAddress) {
            listPhone.add(new PhoneWithName(person.getFirstName(), person.getLastName(), person.getPhone()));
        }
        return listPhone;
    }
}
