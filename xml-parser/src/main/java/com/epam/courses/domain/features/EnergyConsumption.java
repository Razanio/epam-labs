package com.epam.courses.domain.features;

import com.epam.courses.domain.Feature;


public class EnergyConsumption extends Feature {

    private Double consumptionSize;

    public EnergyConsumption() {
        super("energy-consumption");
    }

    @Override
    public void setValue(String value) {
        consumptionSize = Double.parseDouble(value);
    }

    @Override
    public String getValue() {
        return String.valueOf(consumptionSize);
    }
}
