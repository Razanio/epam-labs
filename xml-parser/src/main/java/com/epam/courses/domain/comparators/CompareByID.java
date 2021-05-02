package com.epam.courses.domain.comparators;

import com.epam.courses.domain.Component;

import java.util.Comparator;

public class CompareByID implements Comparator<Component> {
    @Override
    public int compare(Component o1, Component o2) {
        return o1.getIdentity().compareTo(o2.getIdentity());
    }
}
