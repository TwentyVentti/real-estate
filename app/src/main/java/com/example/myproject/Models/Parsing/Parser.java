package com.example.myproject.Models.Parsing;

import java.util.ArrayList;

import static com.example.myproject.Models.Parsing.Token.Type.*;

/**
 * Name: Parser.java
 *
 *  The main objective of this class is to implement a simple parser.
 *  It should be able to parser the following grammar rule:
 *  <MAIN>   -> <BASE> | <>
 *  <BASE>    -> <USER> <BASE> | <USER>
 *  <USER>  -> <PLACE> EQ STRING SEMI | DURATION EQ INTEGER <TUNIT> SEMI
 *  <PLACE>   -> COUNTRY | CITY
 *  <TUNIT>   -> DAY | WEEK | MONTH
 */
public class Parser {

    Tokenizer _tokenizer;

    public Parser(Tokenizer tokenizer) {
        _tokenizer = tokenizer;
    }

    public Exp parseBase() {
        Exp base1 = parseUser();
        if (_tokenizer.hasNext()) {
            BaseExp base2 = (BaseExp) parseBase();
            return new BaseExp(base1, base2);
        }
        else
            return new BaseExp(base1);
    }

//    public Exp parseMulti(Exp term1) {
//        Exp term2 = parseUser();
//        if (_tokenizer.hasNext()) {
//            Exp base2 = parseBase();
//            return new BaseExp(term1, term2);
//        }
//        else
//            return term1;
//    }
    public Exp parseUser() {
//        System.out.println(_tokenizer.current().type());
        switch (_tokenizer.current().type()) {
            case CITY:
            case COUNTRY:
                return parseLocation();
            default : return parseTime();
        }
    }

    public Exp parseLocation() {
        Token.Type placeType = _tokenizer.current().type();

        _tokenizer.next();
        _tokenizer.next();

        String place = _tokenizer.current().token().trim();

        _tokenizer.next();
        _tokenizer.next();

        return new PlaceExp(placeType, place);
    }
    public Exp parseTime() {
        _tokenizer.next();
        _tokenizer.next();
        int time = Integer.parseInt(_tokenizer.current().token());

        _tokenizer.next();
        Token.Type tunit = parseTUnit();

        _tokenizer.next();
        _tokenizer.next();
        return new TimeExp(time, tunit);
    }
    public Token.Type parseTUnit() {
        switch (_tokenizer.current().type()) {
            case DAY: case MONTH: case WEEK:
                return _tokenizer.current().type();
            default:
                System.out.println("Error in creation of TUnit");
        }


        return _tokenizer.current().type();
    }
}