package com.epam.courses.domain.features;

import com.epam.courses.domain.Feature;

public class Cooler extends Feature {

    private Boolean isCooler;

    public Cooler() {
        super("cooler");
    }

    @Override
    public void setValue(String value) {
        isCooler = Boolean.parseBoolean(value);
    }

    @Override
    public String getValue() {
        return String.valueOf(isCooler);
    }
}
