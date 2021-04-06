package com.epam.courses.filter;

import com.epam.courses.comparator.*;
import com.epam.courses.composition.*;

import java.util.Comparator;
import java.util.List;

public class Filter implements DurationFilter, GenreFilter, RangeFilter{
    private static final String TOTAL_DURATION = "Total duration: ";
    private static final String MIN = " min";
    private Double duration = 0.0;

    @Override
    public void getDuration(List<Composition> tracklist) {
        for (int i = 0; i < tracklist.size(); i++) {
            duration += tracklist.get(i).getDuration();
        }
        System.out.println(TOTAL_DURATION + duration + MIN);
        System.out.println();
    }

    @Override
    public List<Composition> showGenre(List<Composition> tracklist, Genre genre) {
        Comparator genreComparator = new GenreComparator(genre);
        tracklist.sort(genreComparator);
        return tracklist;
    }

    @Override
    public void findByRange(Double from, Double to, List<Composition> tracklist) {
        for (int i = 0; i < tracklist.size(); i++) {
            if (tracklist.get(i).getDuration() >= from && tracklist.get(i).getDuration() <= to) {
                System.out.print(i+1 + ") " + tracklist.get(i).toString());
            }
        }
    }
}
