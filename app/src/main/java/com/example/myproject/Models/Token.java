package com.example.myproject.Models;
/**
 * Token class to save extracted token from tokenizer.
 * Each token has its surface form saved in {@code _token}
 * and type saved in {@code _type} which is one of the predefined type in Type enum.
 * INT  -> integer
 * SEMI -> ;
 * CI   -> City
 * // Grammar = <V_t,V_n,S,P>
 * // G = <{n,Ci},{Country,Stay},{Exp},{(<User> -> Country; Ci; n Stay),(<Stay>  -> month | week | day),(<Country>  -> France | Spain | Netherlands | Italy)}
 * // Stay = Length Of Stay
 * // Ci = City
 * // <User> -> Country; Ci; n Stay
 * // <Stay>  -> month | week | day
 * // <Country>  -> France | Spain | Netherlands | Italy
 *
 * @author COMP2100/6442
 */

public class Token {

    public enum Type {UNKNOWN, INT, SEMI, CI};
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
