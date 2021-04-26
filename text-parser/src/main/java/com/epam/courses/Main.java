package com.epam.courses;

import com.epam.courses.file.ReadFile;
import com.epam.courses.filter.Filter;
import com.epam.courses.parser.*;
import com.epam.courses.sentence.*;

import java.util.List;

/**
 * Hello world!
 *
 */
public class Main
{
    public static void main( String[] args )
    {
        try {
            ReadFile reader = new ReadFile();
            String text = reader.readFile();
            Parser parser = new Parser(text);
            List<Token> tokens = parser.parse();

            SentenceBuilder sentenceBuilder = new SentenceBuilder(tokens);
            sentenceBuilder.buildSentences();

            for (Sentence sentence : sentenceBuilder.getSentences()) {
                Filter filter = new Filter();
                sentence.updateTokens(filter.invertWords(sentence.getTokens()));
                System.out.println(sentence.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
