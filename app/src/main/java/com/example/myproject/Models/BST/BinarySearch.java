package com.example.myproject.Models.BST;

public class BinarySearch {

    Node root;
    public BinarySearch() {
        root = null;
    }
    public void construct(int [] arr) {
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


    public void inOrder() {
        helpInOrder(root);
    }
    public void helpInOrder(Node node) {
        if (node == null) {
            return;
        }
        helpInOrder(node.left);
        System.out.println(node.ID);
        helpInOrder(node.right);
    }

}
