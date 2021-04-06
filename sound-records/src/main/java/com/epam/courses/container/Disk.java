package com.epam.courses.container;

import com.epam.courses.composition.*;
import com.epam.courses.filter.*;

import java.util.List;

public class Disk{
    private List<Composition> tracklist;

    public Disk(List<Composition> tracklist) {
        this.tracklist = tracklist;
    }

    public List<Composition> getTracklist() {
        return tracklist;
    }

    public void setTracklist(List<Composition> tracklist) {
        this.tracklist = tracklist;
    }
}
