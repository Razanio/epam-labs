package com.epam.courses.parser;

public class Token {
    private final TokenType type;
    private final String value;

    public TokenType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString() {
        return getValue() + "<" + getType() + ">";
    }
}
