package com.safetynet.safetynetalerts.controller;

import com.safetynet.safetynetalerts.Service.PersonService;
import com.safetynet.safetynetalerts.model.Person;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Data
@RestController
public class PersonController {

    @Autowired
    private PersonService service;

    @GetMapping("/persons")
    public Iterable<Person> getPersons() {
        return service.getPersons();
    }

    @DeleteMapping("/persons/{firstName}/{lastName}")
    public void deletePerson(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName) {
        service.deletePerson(firstName,lastName);
    }

    @PostMapping("/person")
    public Person savePerson(@RequestBody Person person) {
        System.out.println("person saved");
        return service.savePerson(person);
    }
}
