package com.epam.courses.domain.comparators;

import com.epam.courses.domain.Component;

import java.util.Comparator;

public class CompareByPrice implements Comparator<Component> {
    @Override
    public int compare(Component o1, Component o2) {
        return o1.getPrice().compareTo(o2.getPrice());
    }
}
