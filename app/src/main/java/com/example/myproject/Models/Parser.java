package com.example.myproject.Models;

public class Parser {
}
//
//import com.sun.source.tree.BinaryTree;
//        import com.sun.source.tree.Tree;
//
//        import java.util.BitSet;
//
///**
// * Name: Parser.java
// *
// *  The main objective of this class is to implement a simple parser.
// *  It should be able to parser the following grammar rule:
// *  <exp>    ::= <term> | <term> + <exp> | <term> - <exp>
// *  <term>   ::=  <factor> | <factor> * <term> | <factor> / <term>
// *  <factor> ::= <unsigned integer> | ( <exp> )
// *
// */
//
//public class Parser {
//
//    MyTokenizer _tokenizer;
//
//    public Parser(MyTokenizer tokenizer) {
//        _tokenizer = tokenizer;
//    }
//
//    /*
//    <exp>    ::= <term> | <term> + <exp> | <term> - <exp>
//     */
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
//    //<term>   ::=  <factor> | <factor> * <term> | <factor> / <term>
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
//    //<factor> ::= <unsigned integer> | ( <exp> )
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
//}
