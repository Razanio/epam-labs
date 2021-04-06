package com.epam.courses.comparator;

import com.epam.courses.composition.*;

import java.util.Comparator;

public class GenreComparator implements Comparator<Composition> {
    private Genre genre;

    public GenreComparator(Genre genre) {
        this.genre = genre;
    }

    @Override
    public int compare(Composition o1, Composition o2) {
        if(o1.getGenre() == genre && o2.getGenre() != genre){
            return -1;
        }
        else {
            return 1;
        }
    }
}
