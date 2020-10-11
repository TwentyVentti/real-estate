package com.example.myproject;

public class Tokenizer {
    private String _buffer;        //save text
    private Token currentToken;    //save token extracted from next()

    /**
     * Tokenizer class constructor
     * The constructor extracts the first token and save it to currentToken
     * **** please do not modify this part ****
     */
    public Tokenizer(String text) {
        _buffer = text;        // save input text (string)
        next();        // extracts the first token.
    }

    /**
     * TODO: Create comments and docstring for this method.
     */
    public void next() {
        _buffer = _buffer.trim(); // remove whitespace

        if (_buffer.isEmpty()) {
            currentToken = null;    // if there's no string left, set currentToken null and return
            return;
        }


        // TODO: Implement left derivation tokenizer for
        // TODO: Implement left round bracket and right round bracket
        // TODO: Implement integer literal tokenising
        char firstChar = _buffer.charAt(0);
        if (firstChar == '+')
            currentToken = new Token("+", Token.Type.ADD);
        if (firstChar == '-')
            currentToken = new Token("-", Token.Type.SUB);
        StringBuilder number = new StringBuilder();
        if (firstChar == '*') {
            currentToken = new Token("*", Token.Type.MUL);
        }
        if (firstChar == '/') {
            currentToken = new Token("/", Token.Type.DIV);
        }
        if (firstChar == '(') {
            currentToken = new Token("(", Token.Type.LBRA);
        }
        if (firstChar == ')') {
            currentToken = new Token(")", Token.Type.RBRA);
        }
        if (Character.isDigit(firstChar)) {
            number.append(firstChar);
            for (int i = 1; i < _buffer.length(); i++) {
                if (Character.isDigit(_buffer.charAt(i))) {
                    number.append(_buffer.charAt(i));
                } else {
                    currentToken = new Token(number.toString(), Token.Type.INT);
                    break;
                }
            }
            currentToken = new Token(number.toString(), Token.Type.INT);
        }

        // Remove the extracted token from buffer
        int tokenLen = currentToken.token().length();
        _buffer = _buffer.substring(tokenLen);
    }

    /**
     * returned the current token extracted by {@code next()}
     * **** please do not modify this part ****
     *
     * @return type: Token
     */
    public Token current() {
        return currentToken;
    }

    /**
     * check whether there still exists another tokens in the buffer or not
     * **** please do not modify this part ****
     *
     * @return type: boolean
     */
    public boolean hasNext() {
        return currentToken != null;
    }
}