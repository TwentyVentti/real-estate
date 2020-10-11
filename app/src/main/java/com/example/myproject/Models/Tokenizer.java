package com.example.myproject.Models;
// Grammar = <V_t,V_n,S,P>
// G = <{n,Ci},{Country,Stay},{Exp},{(<User> -> Country; Ci; n Stay),(<Stay>  -> month | week | day),(<Country>  -> France | Spain | Netherlands | Italy)}
// Stay = Length Of Stay
// Ci = City
// <User> -> Country; Ci; n Stay
// <Stay>  -> month | week | day
// <Country>  -> France | Spain | Netherlands | Italy
public class Tokenizer {
    private String _buffer;    //save text
    private Token current;    //save token extracted from next()

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
        // TODO: Implement left derivation tokenizer for "Country" input.
        // TODO: Implement left derivation tokenizer for "Ci" input.
        // TODO: Implement integer literal tokenising
        // TODO: Implement left derivation tokenizer for "Stay" input.
        char firstChar = _buffer.charAt(0);
        if (firstChar == ';')
            current = new Token(";", Token.Type.SEMI);
//        if (firstChar == '-')
//            current = new Token("-", Token.Type.SUB);
        StringBuilder number = new StringBuilder();
        if (Character.isDigit(firstChar)) {
            number.append(firstChar);
            for (int i = 1; i < _buffer.length(); i++) {
                if (Character.isDigit(_buffer.charAt(i))) {
                    number.append(_buffer.charAt(i));
                } else {
                    current = new Token(number.toString(), Token.Type.INT);
                    break;
                }
            }
            current = new Token(number.toString(), Token.Type.INT);
        }

        // Remove the extracted token from buffer
        int tokenLen = current.token().length();
        _buffer = _buffer.substring(tokenLen);
    }

    /**
     * returned the current token extracted by {@code next()}
     * **** please do not modify this part ****
     *
     * @return type: Token
     */
    public Token current() {
        return current;
    }

    /**
     * check whether there still exists another tokens in the buffer or not
     * **** please do not modify this part ****
     *
     * @return type: boolean
     */
    public boolean hasNext() {
        return current != null;
    }
}