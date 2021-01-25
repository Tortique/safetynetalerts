package com.safetynet.safetynetalerts.Service;

import com.safetynet.safetynetalerts.dao.JSONReader;
import com.safetynet.safetynetalerts.model.Person;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Data
@Service
public class PersonService {

    @Autowired
    private JSONReader jsonReader;

    public List<Person> getPersons() {
        return jsonReader.readPerson();
    }

    public void deletePerson(String firstName, String lastName) {
        List<Person> list = jsonReader.readPerson();
        Iterator<Person> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (iterator.next().getFirstName().equals(firstName) && iterator.next().getLastName().equals(lastName) ) {
                iterator.remove();
                break;
            }
        }
    }

    public Person savePerson(Person person) {
        List<Person> list = jsonReader.readPerson();
        list.add(new Person(person.getFirstName(), person.getLastName(), person.getAddress(), person.getCity(), person.getZip(), person.getPhone(), person.getEmail()));
        return person;
    }
}
