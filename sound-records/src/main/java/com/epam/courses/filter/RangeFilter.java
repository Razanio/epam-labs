package com.epam.courses.filter;

import com.epam.courses.composition.Composition;

import java.util.List;

public interface RangeFilter {
    public void findByRange(Double from, Double to, List<Composition> tracklist);
}
