package com.example.myproject.Models.Parsing;

/**
 * Custom token exception class for errors in tokenizer step
 * @author Abhaas Goyal - u7145384
 */
public class TokenException extends GrammarException {
    String toastMessageToken = "";
    public TokenException(String s) {
        switch (s) {
            case "INT": toastMessageToken = "Invalid Token"; break;
            case "NULL": toastMessageToken = "Please enter your details!"; break;
            case "ICT": toastMessageToken = "Tried to tokenize empty buffer/ Parsing error"; break;
            case "IK" : toastMessageToken = "Illegal keyword/ Missing String"; break;
            case "CM" : toastMessageToken = "Country parameter is mandatory"; break;
            case "ICO" : toastMessageToken = "Invalid Country"; break;
            default :
                System.err.println("Wrong type of error thrown in code");
                printStackTrace();
        }
    }

    public String toString() {
        return toastMessageToken;
    }
}
