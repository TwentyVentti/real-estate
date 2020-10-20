package com.example.myproject.Models.Parsing;

/**
 * @author Abhaas Goyal - u7145384
 */
public class ParserException extends GrammarException {

    public ParserException() {
    }

    @Override
    public String toString() {
        return "Parsing error on input";
    }
}
