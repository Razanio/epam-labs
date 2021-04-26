package com.epam.courses.sentence;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.epam.courses.parser.*;

public class SentenceBuilder {
    private List<Token> tokens;

    private List<Sentence> sentences;

    public SentenceBuilder(List<Token> tokens) {
        this.tokens = tokens;
    }

    public List<Sentence> getSentences() {
        return sentences;
    }

    /*public void filter(Filter<Token> tokenFilter){
        tokens = tokens.stream().filter(tokenFilter::check).collect(Collectors.toList());
    }*/

    public void buildSentences() {
        sentences = new ArrayList<>();
        List<Token> currentSentenceTokens = new ArrayList<>();
        for (Token token : tokens) {
            if (token.getType() == TokenType.WHITESPACE && currentSentenceTokens.isEmpty()) {
                continue;
            }

            currentSentenceTokens.add(token);

            if (token.getValue().equals(".")) {
                sentences.add(new Sentence(currentSentenceTokens));
                currentSentenceTokens = new ArrayList<>();
            }
        }
    }
}
