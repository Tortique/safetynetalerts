package com.safetynet.safetynetalerts.model;

import com.jsoniter.annotation.JsonProperty;
import com.jsoniter.fuzzy.MaybeStringIntDecoder;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class FireStation {
    @JsonProperty(decoder = MaybeStringIntDecoder.class)
    private Set<String> addresses = new HashSet<>();
    private String address;
    private String station;

    public FireStation(String station) {
        this.station = station;
    }

    public FireStation() {

    }

    public FireStation addAddress(String address) {
        addresses.add(address);
        return this;
    }

    public Set<String> getAddresses() {
        return addresses.stream().collect(Collectors.toSet());
    }
}
