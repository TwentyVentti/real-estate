package com.example.myproject.Models.Parsing;


/**
 *  Grammar = <V_t,V_n,S,P>
 *  G = <{n,Ci},{Country,Stay},{Exp},{(<User> -> Country; Ci; n Stay),(<Stay>  -> month | week | day),(<Country>  -> France | Spain | Netherlands | Italy)}
 *  Stay = Length Of Stay
 *  Ci = City
 *  <User>     -> Country; City; n Stay
 *  <Stay>     -> month | week | day
 *  <City>     -> Paris | Madrid | Amsterdam | Rome
 *  <Country>  -> France | Spain | Netherlands | Italy
 */
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
        char firstChar = _buffer.charAt(0);
        if (firstChar == ';')
            current = new Token(";", Token.Type.SEMI);

        StringBuilder word = new StringBuilder();
        if (Character.isAlphabetic(firstChar)){
            word.append(firstChar);
            for (int i = 1; i < _buffer.length(); i++) {
                if (Character.isAlphabetic(_buffer.charAt(i))) {
                    word.append(_buffer.charAt(i));
                } else {
                    current = new Token(word.toString(), Token.Type.WORD);
                    break;
                }
            }
            current = new Token(word.toString(), Token.Type.INT);
        }
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

    public String wrongSpellingCheck(String word){
        return "";
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