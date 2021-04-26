package com.epam.courses.parser;

public enum TokenType {
    EMAIL("[\\w\\.]+\\@[\\w\\.]+\\.[a-z]+"),
    PHONE("\\+\\d{3}\\(\\d{2}\\)\\d{3}-\\d{2}-\\d{2}"),
    WORD("[\\wа-яА-я]+"),
    PUNCTUATION("\\p{Punct}"),
    WHITESPACE("\\p{Blank}+");

    private final String regex;

    TokenType(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }
}
