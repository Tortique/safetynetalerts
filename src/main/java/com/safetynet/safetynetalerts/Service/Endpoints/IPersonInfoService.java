package com.safetynet.safetynetalerts.Service.Endpoints;

import java.util.List;
import com.safetynet.safetynetalerts.dto.PersonInfo;

public interface IPersonInfoService {
    List<PersonInfo> getPersonInfo(String firstName, String lastName);
}
