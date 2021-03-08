package com.safetynet.safetynetalerts.controller;

import com.safetynet.safetynetalerts.Service.PersonService;
import com.safetynet.safetynetalerts.Service.Result;
import com.safetynet.safetynetalerts.model.Person;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Controller mapping CRUD for PersonService
 */
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
    public Result deletePerson(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName) {
        return service.deletePerson(firstName, lastName);
    }

    @PostMapping("/person")
    public Person savePerson(@RequestBody Person person) {
        return service.savePerson(person);
    }

    @PutMapping("/persons/{firstName}/{lastName}")
    public Result updatePerson(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName, @RequestBody Person person) {
        return service.updatePerson(person, firstName, lastName);
    }
}
