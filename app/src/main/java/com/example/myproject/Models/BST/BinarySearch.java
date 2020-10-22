package com.example.myproject.Models.BST;

import java.util.ArrayList;

public class BinarySearch {

    Node root;
    ArrayList <Integer> temp;
    public BinarySearch() {
        temp = new ArrayList<>();
        root = null;
    }

    //TODO: Instead of int[] arr passed pass array of json object or something
    /**
     * Construct Balanced BSTs from sorted ArrayList (demo)
     * @param arr sorted array of integers
     */
    public void construct(int [] arr) {
        temp = new ArrayList<>();
        root = helpConstruct(arr, 0, arr.length - 1);
    }

    /**
     * Helper function to construct Balanced BST from construct()
     * @param arr
     * @param lowerBound
     * @param higherBound
     * @return Balanced Subtree
     */
    private Node helpConstruct (int[] arr, int lowerBound, int higherBound) {
        if (lowerBound > higherBound)
            return null;
        int middle = (lowerBound + higherBound)/2;
        Node node = new Node(arr[middle]);
        node.left = helpConstruct(arr, lowerBound, middle -1);
        node.right = helpConstruct(arr, middle+1,higherBound);
        return node;
    }

    /**
     * Inorder traversal of BST
     * @return
     */
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

    /**
     * Construct BSTs from ARraylist
     * @param
     */
    public void constructTree(ArrayList<Node> x) {
        // Make tree called from loginActivity
    }

    // Return the nodes of phrases of a particular section
    public ArrayList<Node> sectionNodes() {
        return null;
    }

}
