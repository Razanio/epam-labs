package com.epam.courses.filter;

import com.epam.courses.composition.Composition;

import java.util.List;

public interface DurationFilter {
    public void getDuration(List<Composition> tracklist);
}
