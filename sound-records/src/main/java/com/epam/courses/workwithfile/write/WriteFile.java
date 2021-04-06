package com.epam.courses.workwithfile.write;

import com.epam.courses.composition.Composition;
import com.epam.courses.workwithfile.read.*;
import com.epam.courses.workwithfile.write.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.List;

public class WriteFile implements Write {

    public WriteFile() {
    }
    @Override
    public void write(List<Composition> tracklist) {
        try {
            OutputStream os = new FileOutputStream("tracklist.bin");
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(tracklist);
            oos.close();
        } catch (IOException e) {
            System.out.println("Невозможно сохранить файл");
        }
    }
}
