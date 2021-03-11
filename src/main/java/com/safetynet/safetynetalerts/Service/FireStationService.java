package com.safetynet.safetynetalerts.Service;

import com.safetynet.safetynetalerts.dao.JSONReader;
import com.safetynet.safetynetalerts.dao.Reader;
import com.safetynet.safetynetalerts.model.FireStation;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

/**
 * Service that allows to get a fire station's list from jsonfile, to add, delete and update this list.
 */
@Data
@Service
public class FireStationService implements IFireStationService {
    private static Logger logger = LogManager.getLogger("FireStationService");

    @Autowired
    JSONReader jsonReader;

    private Map<String, FireStation> map;

    public FireStationService(Reader jsonReader) throws IOException {
        this.map = jsonReader.readFireStation();
    }

    public Map<String, FireStation> getFireStation() {
        return map;
    }

    public Result deleteAddressOfFireStation(String address) {
        map.values().stream().filter(addressToRemove -> !addressToRemove.getAddresses().contains(address));
        if (map.values().contains(address)) {
            return Result.failure;
        }
        return Result.success;
    }

    public Result deleteFireStation(String station) {
        map.entrySet().removeIf(stationToRemove -> stationToRemove.getKey().equals(station));
        if (map.entrySet().contains(station)) {
            return Result.failure;
        }
        return Result.success;
    }

    public FireStation saveFireStation(FireStation fireStation) {
        map.put(fireStation.getStation(), fireStation.addAddress(fireStation.getAddress()));
        return fireStation;
    }

    public Result updateFireStationOfAddress(FireStation fireStation, String station, String address) {
        for (FireStation fireStation1 : map.values()) {
            if (fireStation1.getStation().equals(station)) {
                map.remove(station, address);
                map.put(fireStation.getStation(), fireStation);
                return Result.success;
            }
        }
        return Result.failure;
    }
}
