package com.epam.courses.filter;

import com.epam.courses.parser.Token;
import com.epam.courses.parser.TokenType;

import java.util.List;

public class Filter implements InversionWords{
    @Override
    public List<Token> invertWords(List<Token> tokens) {
        int first_ind = 0, last_ind = tokens.size()-1;
        while(tokens.get(first_ind).getType() !=  TokenType.WORD){
            first_ind++;
        }
        while(tokens.get(last_ind).getType() !=  TokenType.WORD){
            last_ind--;
        }
        Token first_word = tokens.get(first_ind);
        Token last_word = tokens.get(last_ind);
        tokens.set(first_ind, last_word);
        tokens.set(last_ind, first_word);
        //System.out.printf("%d,%d\n", first_ind, last_ind );
        return tokens;
    }
}
