package com.example.myproject.Models.Parsing;

/**
 * Custom ParserExceptionClass
 * @author Abhaas Goyal - u7145384
 */
public class ParserException extends GrammarException {

    public String nearToken;
    public String keyword;

    public ParserException(String nearToken, String keyword) {
        this.nearToken = nearToken;
        this.keyword = keyword;
    }
    public ParserException(String nearToken) {
        this.nearToken = nearToken;
    }

    @Override
    public String printError() {
        if (nearToken == null || keyword == null) {
            return "Unknown Parsing error on input";
        }
        else {
            return "Parsing error on input on parameter " + keyword + " near token " + nearToken;
        }
    }
}
