package com.example.myproject.Models;

import java.util.BitSet;

/**
 * Name: Parser.java
 *
 *  The main objective of this class is to implement a simple parser.
 *  It should be able to parser the following grammar rule:
 *  <User>     -> Country; City; n Stay
 *  <Stay>     -> month | week | day
 *  <City>     -> Paris | Madrid | Amsterdam | Rome
 *  <Country>  -> France | Spain | Netherlands | Italy
 *
 */
public class Parser {

    Tokenizer _tokenizer;

    public Parser(Tokenizer tokenizer) {
        _tokenizer = tokenizer;
    }

    /**
    * <User> -> Country; City; n Stay
     */
//    public Exp parseExp() {
//        // TODO: Implement parse function for <exp>
//        // ########## YOUR CODE STARTS HERE ##########
//        //Case 1: Term
//        Exp term = parseTerm();
//        //Case 2: Term + exp
//        if (_tokenizer.hasNext()&&_tokenizer.current().type()==Token.Type.ADD){
//            _tokenizer.next();
//            Exp exp = parseExp();
//            return new AddExp(term, exp);
//
//            //Case 3: Factor / Term
//        } else if (_tokenizer.hasNext()&&_tokenizer.current().type()==Token.Type.SUB){
//            _tokenizer.next();
//            Exp exp = parseExp();
//            return new SubExp(term, exp);
//        } else {
//            return term;
//        }
//        // ########## YOUR CODE ENDS HERE ##########
//    }
//
    /**
    * <Stay> -> month | week | day
    */
//    public Exp parseTerm() {
//        // TODO: Implement parse function for <term>
//        // ########## YOUR CODE STARTS HERE ##########
//        //Case 1: Factor
//        Exp factor = parseFactor();
//        //Case 2: Factor * Term
//        if (_tokenizer.hasNext()&&_tokenizer.current().type()==Token.Type.MUL){
//            _tokenizer.next();
//            Exp term = parseTerm();
//            return new MultExp(factor, term);
//
//            //Case 3: Factor / Term
//        } else if (_tokenizer.hasNext()&&_tokenizer.current().type()==Token.Type.DIV){
//            _tokenizer.next();
//            Exp term = parseTerm();
//            return new DivExp(factor, term);
//        } else {
//            return factor;
//        }
//        // ########## YOUR CODE ENDS HERE ##########
//    }
//
    /**
    * <City> -> Paris | Madrid | Amsterdam | Rome
    */
//    public Exp parseFactor() {
//        // TODO: Implement parse function for <factor>
//        // ########## YOUR CODE STARTS HERE ##########
//        if (_tokenizer.hasNext()&&_tokenizer.current().type()==Token.Type.LBRA){
//            _tokenizer.next();
//            Exp exp = parseExp();
//            _tokenizer.next();
//            return exp;
//        }else {
//            IntExp i = new IntExp(Integer.parseInt(_tokenizer.current().token()));
//            _tokenizer.next();
//            return i;
//        }
//        // ########## YOUR CODE ENDS HERE ##########
//    }
    /**
    * <Country> -> France | Spain | Netherlands | Italy
    */
//    public Exp parseFactor() {
//        // TODO: Implement parse function for <factor>
//        // ########## YOUR CODE STARTS HERE ##########
//        if (_tokenizer.hasNext()&&_tokenizer.current().type()==Token.Type.LBRA){
//            _tokenizer.next();
//            Exp exp = parseExp();
//            _tokenizer.next();
//            return exp;
//        }else {
//            IntExp i = new IntExp(Integer.parseInt(_tokenizer.current().token()));
//            _tokenizer.next();
//            return i;
//        }
//        // ########## YOUR CODE ENDS HERE ##########
//    }

//
//    public static void main(String[] args) {
//        MyTokenizer mathTokenizer = new MyTokenizer("2*5+1");
//        Exp t1 = new Parser(mathTokenizer).parseExp();
//        System.out.println(t1.show());
//        System.out.println(t1.evaluate());
//    }
}
