package com.example.myproject.Models.Parsing;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Tokenizer {
    private String _buffer;    //save text
    private Token current;    //save token extracted from next()
    private int PARENTHESIS = 2;

    /**
     * Tokenizer class constructor
     * The constructor extracts the first token and saves it to currentToken
     */
    public Tokenizer(String text) {
        _buffer = text;        // save input text (string)
        next();                // extracts the first token.
    }

    /**
     * TODO: Create comments and docstring for this method.
     */
    public void next() {
        _buffer = _buffer.trim(); // remove whitespace

        if (_buffer.isEmpty()) {
            current = null;    // if there's no string left, set currentToken null and return
            return;
        }
        char firstChar = _buffer.charAt(0);
        if (firstChar == ';')
            current = new Token(";", Token.Type.SEMI);
        if (firstChar == '=')
            current = new Token("=", Token.Type.EQ);
        if (firstChar == '\"') {
            // StringTokenizer is a legacy library if possible could use a better one idk
            StringTokenizer st = new StringTokenizer(_buffer.substring(1),"\"");
            current = new Token(st.nextToken().toLowerCase(), Token.Type.STRING);
            // TODO: Checker for some types of invalid strings
        }
        if (Character.isLowerCase(firstChar)) {
            String[] result = _buffer.split("[^A-Za-z]");
            String keyword = result[0];
            switch (keyword.toLowerCase()) {
                case "city" : current = new Token(keyword, Token.Type.CITY); break;
                case "country" : current = new Token(keyword, Token.Type.COUNTRY); break;
                case "duration" : current = new Token(keyword, Token.Type.DURATION); break;
                case "weeks" : case "week"  : current = new Token(keyword, Token.Type.WEEK); break;
                case "days"  : case "day"   : current = new Token(keyword, Token.Type.DAY); break;
                case "months": case "month" : current = new Token(keyword, Token.Type.MONTH); break;
                default :
                    System.err.println("Wrong keyword I guess");
            }
        }
        if (Character.isDigit(firstChar)) {
            String[] result = _buffer.split("[^0-9]");
            current = new Token(result[0], Token.Type.INT);
        }
        if (current == null) {
            System.out.println("Tokenizer error woah");
        }

        int tokenLen;
        // Remove the extracted token from buffer
        if (current.type() == Token.Type.STRING)
            //No magic numbers: 2, 3, 4;
            // Instead make a global variable.
            tokenLen = current.token().length() + PARENTHESIS;
        else
            tokenLen = current.token().length();

        _buffer = _buffer.substring(tokenLen);
    }
    /**
     * @return type: Token
     */
    public Token current() {
        return current;
    }

    /**
     * @return type: boolean
     */
    public boolean hasNext() {
        return current != null;
    }
}