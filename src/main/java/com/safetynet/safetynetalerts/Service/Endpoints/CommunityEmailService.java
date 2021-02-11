package com.safetynet.safetynetalerts.Service.Endpoints;

import com.safetynet.safetynetalerts.controller.Endpoints.CommunityEmailController;
import com.safetynet.safetynetalerts.dao.JSONReader;
import com.safetynet.safetynetalerts.dto.EmailWithName;
import com.safetynet.safetynetalerts.model.Person;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Service
public class CommunityEmailService implements ICommunityEmailService {
    @Autowired
    JSONReader jsonReader;

    private List<Person> list;
    public CommunityEmailService(JSONReader jsonReader) throws IOException {
        this.list = jsonReader.readPerson();
    }
    public List<EmailWithName> getCommunityEmail(String city) {
        List<Person> listInhabitants = list.stream().filter(person -> person.getCity().contains(city)).collect(Collectors.toList());
        List<EmailWithName> listEmail = new ArrayList<>();
        for(Person person : listInhabitants) {
            listEmail.add(new EmailWithName(person.getFirstName(), person.getLastName(), person.getEmail()));
        }
        return listEmail;
    }
}
