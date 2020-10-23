package com.example.myproject.Models.Parsing;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @co-author Abhaas Goyal - u7145384
 * @co-author  Andrew Carse - u6666440
 *
 * This class takes user inputs and converts it to our
 * optimized grammar in order for our parser to interpret it efficiently.
 */
public class Tokenizer {
    private String _buffer;    //save text
    private Token current;    //save token extracted from next()

    /**
     * Tokenizer class constructor
     * The constructor extracts the first token and saves it to currentToken
     */
    public Tokenizer(String text) throws GrammarException {
        _buffer = text;        // save input text (string)
        next();                // extracts the first token.
    }

    /**
     * Sets the current character to a Token object
     * Removes the whitespace of the buffer
     *
     * @throws GrammarException if token is not parsed correctly
     */
    public void next() throws GrammarException {
        _buffer = _buffer.trim(); // remove whitespace

        if (_buffer.isEmpty()) {
            if (current == null) {
                throw new ParserException("end of input");
            }
            else if (!current.token().equals(";")) {
                throw new ParserException(current().token());
            }
            current = null;    // if there's no string left, set currentToken null and return
            return;
        }
        char firstChar = _buffer.charAt(0);
        if (firstChar == ';')
            current = new Token(";", Token.Type.SEMI);
        if (firstChar == '=')
            current = new Token("=", Token.Type.EQ);
        if (firstChar == '\"') {
            Pattern p = Pattern.compile("\"([^\"]*)\"");
            Matcher m = p.matcher(_buffer);
            if (m.find()) {
                current = new Token(Objects.requireNonNull(m.group(1)).toLowerCase(), Token.Type.STRING);
                if (!current.token().matches("^[a-zA-Z0-9]*$")) {
                    throw new ParserException(current.token());
                }
            }
            else {
                throw new ParserException(current.token());
            }
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
                    throw new TokenException("IK");
            }
        }
        if (Character.isDigit(firstChar)) {
            String[] result = _buffer.split("[^0-9]");
            current = new Token(result[0], Token.Type.INT);
        }
        if (current == null) {
            throw new TokenException("INT");
        }
        int tokenLen;
        // Remove the extracted token from buffer
        int QUOTES = 2;
        if (current.type() == Token.Type.STRING)
            tokenLen = current.token().length() + QUOTES;
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