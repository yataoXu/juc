package com.evan.core.base.optionalDemo;

import java.util.Optional;


public class Country {

    private String name;

    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }
}
