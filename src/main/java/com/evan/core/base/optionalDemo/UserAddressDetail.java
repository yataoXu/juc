package com.evan.core.base.optionalDemo;

import lombok.Data;

import java.util.Optional;

/**
 * @author seassoon
 */
@Data
public class UserAddressDetail {

    private Address address;


    public Optional<Address> getAddress() {
        return Optional.ofNullable(address);
    }
}
