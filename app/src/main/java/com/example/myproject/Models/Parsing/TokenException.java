package com.example.myproject.Models.Parsing;

public class TokenException extends Exception {
    String toastMessageToken = "";
    public TokenException(String s) {
        switch (s) {
            case "INT": toastMessageToken = "Invalid Token"; break;
            case "NULL": toastMessageToken = "Empty String"; break;
            case "ICT": toastMessageToken = "Tried to tokenize empty buffer/ Parsing error"; break;
            case "IK" : toastMessageToken = "Illegal keyword"; break;
            case "CM" : toastMessageToken = "Country parameter is mandatory"; break;
            default :
                System.out.println("Wrong type of error thrown in code");
        }
    }
    public String toString() {
        return toastMessageToken;
    }
}
