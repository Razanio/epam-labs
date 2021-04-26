package com.epam.courses.file;

import com.epam.courses.parser.Token;

import java.io.FileNotFoundException;
import java.util.List;

public interface Read {
    public String readFile() throws FileNotFoundException;
}
