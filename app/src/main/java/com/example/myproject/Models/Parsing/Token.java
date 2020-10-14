package com.example.myproject.Models.Parsing;
/**
 * Token class to save extracted token from tokenizer.
 * Each token has its surface form saved in {@code _token}
 * and type saved in {@code _type} which is one of the predefined type in Type enum.
 * Structure inspired from COMP2100 Lab
 */

/**
 *  Types of tokens
 *  Keywords -> city, country, duration, day, week, month
 *  Operators -> =, <, >, <=, >=, &&, || (no need as of now)
 *  Whitespace -> \n, \t, <space>
 *  Whitespaces -> [Whitespace]+
 *  Miscellaneous -> ;
 *  Digit = [0-9]
 *  Digits = [Digit]+
 *  Character = [A-Za-z]
 *  String = "[Character]*"
 */

public class Token {

    public enum Type {UNKNOWN, EQ, DURATION, DAY, WEEK, MONTH, CITY, COUNTRY, SEMI, STRING, INT};
    private String _token = "";
    private Type _type = Type.UNKNOWN;

    public Token(String token, Type type) {
        _token = token;
        _type = type;
    }

    public String token() {
        return _token;
    }

    public Type type() {
        return _type;
    }
}
