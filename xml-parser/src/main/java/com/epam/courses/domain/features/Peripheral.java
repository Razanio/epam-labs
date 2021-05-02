package com.epam.courses.domain.features;

import com.epam.courses.domain.Feature;

public class Peripheral extends Feature {

    private Boolean isPeripheral;

    public Peripheral() {
        super("peripheral");
    }

    @Override
    public void setValue(String value) {
        isPeripheral = Boolean.parseBoolean(value);
    }

    @Override
    public String getValue() {
        return String.valueOf(isPeripheral);
    }
}
