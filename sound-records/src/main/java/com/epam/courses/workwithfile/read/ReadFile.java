package com.epam.courses.workwithfile.read;

import com.epam.courses.composition.Composition;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class ReadFile implements Read {
    public ReadFile() {
    }

    @Override
    public List<Composition> read() {
        List<Composition> tracklist = new ArrayList<>();
        try {
            InputStream is = new FileInputStream("tracklist.bin");
            ObjectInputStream ois = new ObjectInputStream(is);
            tracklist = (List<Composition>) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            tracklist = new ArrayList<>();
        }
        return tracklist;
    }
}
