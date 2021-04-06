package com.epam.courses.main;

import com.epam.courses.composition.Composition;
import com.epam.courses.composition.Genre;
import com.epam.courses.container.Disk;
import com.epam.courses.createobject.CreateObj;
import com.epam.courses.filter.Filter;
import com.epam.courses.workwithfile.read.ReadFile;
import com.epam.courses.workwithfile.write.WriteFile;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Filter filter = new Filter();
        List<Composition> tracklist = new ArrayList<>();
        CreateObj obj = new CreateObj();
        tracklist = obj.getTracklist();
        WriteFile w = new WriteFile();
        ReadFile r = new ReadFile();
        w.write(tracklist);
        Disk disk = new Disk(tracklist);

        System.out.println("Tracklist:");
        int counter = 0;
        for(Composition c :tracklist){
            counter++;
            c.display(counter);
        }

        System.out.println("\nPermutation by genre:");
        counter = 0;
        for(Composition c :filter.showGenre(tracklist, Genre.CLASSIC)){
            counter++;
            c.display(counter);
        }

        System.out.println("\nRange search:");
        filter.findByRange(3.00,6.00,tracklist);

    }
}
