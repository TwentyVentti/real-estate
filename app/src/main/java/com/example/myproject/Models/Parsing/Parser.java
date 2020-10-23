package com.example.myproject.Models.Parsing;

/**
 *  The main objective of this class is to implement a simple parser.
 *  It should be able to parser the following grammar rule:
 *  <BASE>    -> <USER> <BASE> | <USER>
 *  <USER>  -> <PLACE> EQ STRING SEMI | DURATION EQ INTEGER <TUNIT> SEMI
 *  <PLACE>   -> COUNTRY | CITY
 *  <TUNIT>   -> DAY | WEEK | MONTH
 *
 *  @author Abhaas Goyal - u7145384
 */

public class Parser {

    Tokenizer _tokenizer;

    public Parser(Tokenizer tokenizer) {
        _tokenizer = tokenizer;
    }

    public Exp parseBase() throws GrammarException {
        Exp base1 = parseUser();
        if (_tokenizer.hasNext()) {
            BaseExp base2 = (BaseExp) parseBase();
            return new BaseExp(base1, base2);
        }
        else
            return new BaseExp(base1);
    }

    public Exp parseUser() throws GrammarException {
            switch (_tokenizer.current().type()) {
                case CITY:
                case COUNTRY:
                    return parseLocation();
                default:
                    return parseTime();
            }
    }

    public Exp parseLocation() throws GrammarException {
        try {
            Token.Type placeType = _tokenizer.current().type();
            String place;
            _tokenizer.next();
            _tokenizer.next();
            place = _tokenizer.current().token().trim();

            _tokenizer.next();
            _tokenizer.next();
            return new PlaceExp(placeType, place);
        }
        catch (ParserException e) {
            throw new ParserException(e.nearToken, "country/city");
        }
    }
    public Exp parseTime() throws GrammarException{
        try {
            _tokenizer.next();
            _tokenizer.next();
            int time = Integer.parseInt(_tokenizer.current().token());

            _tokenizer.next();
            Token.Type tunit = parseTUnit();

            _tokenizer.next();
            _tokenizer.next();
            return new TimeExp(time, tunit);
        }
        catch (ParserException e) {
            throw new ParserException(e.nearToken, "duration");
        }
    }
    public Token.Type parseTUnit() throws GrammarException {
        switch (_tokenizer.current().type()) {
            case DAY:
            case MONTH:
            case WEEK:
                return _tokenizer.current().type();
            default:
                throw new TokenException("IK");
        }
    }
}