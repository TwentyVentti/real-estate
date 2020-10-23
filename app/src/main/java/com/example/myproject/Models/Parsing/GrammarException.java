package com.example.myproject.Models.Parsing;


/**
 * Base grammar exception class - consisting of ParserException and TokenException
 * @author Abhaas Goyal - u7145384
 */
public abstract class GrammarException extends Exception {
    public abstract String printError();
}
