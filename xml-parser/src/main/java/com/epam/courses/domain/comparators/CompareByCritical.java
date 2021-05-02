package com.epam.courses.domain.comparators;

import com.epam.courses.domain.Component;

import java.util.Comparator;

public class CompareByCritical implements Comparator<Component> {
    @Override
    public int compare(Component o1, Component o2) {
        return o1.getCritical().compareTo(o2.getCritical());
    }
}
