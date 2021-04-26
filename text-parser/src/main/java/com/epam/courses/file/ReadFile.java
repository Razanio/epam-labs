package com.epam.courses.file;

import com.epam.courses.parser.Token;
import com.epam.courses.sentence.*;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class ReadFile implements Read {
    public ReadFile(){}
    @Override
    public String readFile() throws FileNotFoundException {
        String text = new BufferedReader(new FileReader("src\\main\\java\\com\\epam\\courses\\file\\text.txt")).lines().collect(Collectors.joining());
        return text;
    }
}
