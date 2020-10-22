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
}
