package com.example.myproject.Models;
/**
 * Token class to save extracted token from tokenizer.
 * Each token has its surface form saved in {@code _token}
 * and type saved in {@code _type} which is one of the predefined type in Type enum.
 * INT  -> integer
 * SEMI -> ;
 *
 * @author COMP2100/6442
 */

public class Token {

    public enum Type {UNKNOWN, INT, SEMI};
    private String _token = "";
    private Type _type = Type.UNKNOWN;

    public Token(String token, Type type) {
        _token = token;
        _type = type;
    }

    public String token() {
        return _token;
    }

    public Type type() {
        return _type;
    }
}
