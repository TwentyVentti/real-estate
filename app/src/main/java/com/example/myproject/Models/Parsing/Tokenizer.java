package com.example.myproject.Models.Parsing;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

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
    final ArrayList<String> COUNTRIES = new ArrayList<>(Arrays.asList("France","Spain","Netherlands","Italy"));
    final ArrayList<String> CITIES = new ArrayList<>(Arrays.asList("Paris","Madrid","Amsterdam","Rome"));
    final ArrayList<String> DURATIONS = new ArrayList<>(Arrays.asList("Month","Week","Day"));


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

    public Token misspelledCountry(String word){
        switch (word.toUpperCase()){
            case "FRANCE":
                return new Token("French", Token.Type.FRENCH);
            case "ITALY":
                return new Token("Italian", Token.Type.ITALIAN);
            case "NETHERLANDS":
                return new Token("Dutch", Token.Type.DUTCH);
            case "SPAIN":
                return new Token("Spanish", Token.Type.SPANISH);
        }
        int equalLetters = 0;
//        for (int i = 0; i < COUNTRIES.size(); i++) {
//            char[] x = COUNTRIES.get(i).toCharArray();
//            ArrayList<Character> countryCharArray = new ArrayList<Character>(Arrays.asList());
//            for (char letter : word.toCharArray()) {
//                if (countryCharArray)
//            }
//
//        }
        return null;
    }
//    public Token misspelledCity(String word){
//        return null;
//    }
//    public Token misspelledDuration(String word){
//        return null;
//    }
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