package com.example.myproject.Models.BST;

/**
 * Node class for Binary search tree implementation
 * @author Abhaas Goyal - u7145384
 */
public class Node {

    /**
     * Node Data
     */
    int ID;
    String englishPhrase, languagePhrase;

    Node left, right;

    public Node(int ID, String englishPhrase, String languagePhrase ) {
        this.ID = ID;
        this.englishPhrase = englishPhrase;
        this.languagePhrase = languagePhrase;
        left = right = null;
    }

    /**
     * Getters and setters (Not directly public because complicated data structure should not
     * have public changable elements by design
     * If more needed add in the future
     */

    public String getEnglishPhrase() {
        return englishPhrase;
    }

    public String getLanguagePhrase() {
        return languagePhrase;
    }

}
