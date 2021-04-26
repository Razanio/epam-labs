package com.epam.courses.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private final String text;

    public Parser(String text) {
        this.text = text;
    }

    public List<Token> parse() {
        Pattern pattern = buildPattern();
        Matcher matcher = pattern.matcher(text);
        List<Token> result = new ArrayList<>();

        while (matcher.find()) {
            TokenType currentTokenType = null;
            String currentTokenValue = null;

            for (TokenType type : TokenType.values()) {
                String value = matcher.group(type.name());
                if (value != null) {
                    currentTokenValue = value;
                    currentTokenType = type;
                }
            }

            if (currentTokenValue == null) {
                throw new IllegalArgumentException();
            }
            if (currentTokenType == TokenType.WHITESPACE) {
                currentTokenValue = " ";
            }
            result.add(new Token(currentTokenType, currentTokenValue));
        }
        return result;
    }

    private Pattern buildPattern() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < TokenType.values().length; i++) {
            if (i != 0) {
                builder.append("|");
            }
            builder.append("(?<").append(TokenType.values()[i].name()).append(">").append(TokenType.values()[i].getRegex()).append(")");
        }
        return Pattern.compile(builder.toString());
    }
}
