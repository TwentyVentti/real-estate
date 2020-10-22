package com.example.myproject.Models.BST;

import java.util.ArrayList;

public class BinarySearch {

    Node root;
    ArrayList <Integer> temp;
    public BinarySearch() {
        temp = new ArrayList<>();
        root = null;
    }


    // Below 4 functions are basic test cases without JSON
    public void construct(int [] arr) {
        temp = new ArrayList<>();
        root = helpConstruct(arr, 0, arr.length - 1);
    }
    // TODO: Instead of int[] arr passed pass array of json object or something
    private Node helpConstruct (int[] arr, int l, int h) {
        if (l > h)
            return null;
        int m = (l + h)/2;
        Node node = new Node(arr[m]);
        node.left = helpConstruct(arr, l, m -1);
        node.right = helpConstruct(arr, m+1,h);
        return node;
    }

    public ArrayList<Integer> inOrder() {
        temp.clear();
        helpInOrder(root);
        return temp;
    }
    private void helpInOrder(Node node) {
        if (node == null) {
            return;
        }
        helpInOrder(node.left);
        temp.add(node.ID);
        System.out.println(node.ID);
        helpInOrder(node.right);
    }

    public void constructTree(ArrayList<Node> x) {
        // Make tree called from loginActivity
    }

    // Return the nodes of phrases of a particular section
    public ArrayList<Node> sectionNodes() {
        return null;
    }

}
