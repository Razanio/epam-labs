package com.epam.courses.domain.comparators;

import com.epam.courses.domain.Component;
import com.epam.courses.domain.features.EnergyConsumption;

import java.util.Comparator;

public class CompareByName implements Comparator<Component> {
    @Override
    public int compare(Component o1, Component o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
