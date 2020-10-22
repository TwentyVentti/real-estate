package com.example.myproject.Models.BST;

public class Node {
    int ID;
    String englishPhrase, languagePhrase;

    Node left, right;

    public Node(int item) {
        ID = item;
        left = right = null;
    }

    public Node () {
        left = right = null;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getEnglishPhrase() {
        return englishPhrase;
    }

    public void setEnglishPhrase(String englishPhrase) {
        this.englishPhrase = englishPhrase;
    }

    public String getLanguagePhrase() {
        return languagePhrase;
    }

    public void setLanguagePhrase(String languagePhrase) {
        this.languagePhrase = languagePhrase;
    }
}
