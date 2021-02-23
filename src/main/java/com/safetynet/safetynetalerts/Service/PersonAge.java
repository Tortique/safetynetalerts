package com.safetynet.safetynetalerts.Service;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@Component
public class PersonAge {
    public int getPersonAge(String birthDate) {
        DateTimeFormatter simpleDateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate birthDate1 = LocalDate.parse(birthDate, simpleDateFormat);
        LocalDate now = LocalDate.now();
        Period age = Period.between(birthDate1, now);
        return age.getYears();
    }
}
