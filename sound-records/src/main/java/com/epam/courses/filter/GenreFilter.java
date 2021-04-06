package com.epam.courses.filter;

import com.epam.courses.composition.*;

import java.util.List;

public interface GenreFilter{
    public List<Composition> showGenre(List<Composition> tracklist, Genre genre);
}
