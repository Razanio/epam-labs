package com.epam.courses.createobject;

import com.epam.courses.composition.Composition;
import com.epam.courses.composition.Genre;

import java.util.ArrayList;
import java.util.List;

public class CreateObj {
    public CreateObj() {
    }
    public List<Composition> getTracklist(){
        List<Composition> tracklist = new ArrayList<>();
        tracklist.add(new Composition("Beethoven", "Symphony №3", Genre.CLASSIC, 5.15));
        tracklist.add(new Composition("Queen", "I Want It All",Genre.ROCK, 3.15));
        tracklist.add(new Composition("Queen", "I Want to Break Free",Genre.ROCK, 3.28));
        tracklist.add(new Composition("Beethoven", "Symphony №7",Genre.CLASSIC, 4.15));
        tracklist.add(new Composition("TheFatRat", "Monody",Genre.ELECTRONIC, 2.55));
        tracklist.add(new Composition("ENV", "BonusGame",Genre.ELECTRONIC, 2.15));
        tracklist.add(new Composition("Lil Skies", "Magic",Genre.HIPHOP, 3.00));
        tracklist.add(new Composition("Rise Against", "Give it all",Genre.ALTERNATIVE, 2.22));
        tracklist.add(new Composition("Manowar", "Warriors Of The World",Genre.METAL, 6.32));
        tracklist.add(new Composition("Queen", "I Want It All",Genre.ROCK, 3.15));
        return tracklist;
    }
}
