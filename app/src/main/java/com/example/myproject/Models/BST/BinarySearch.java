package com.example.myproject.Models.BST;

import java.util.ArrayList;

public class BinarySearch {

    Node root;
    ArrayList <Integer> temp;
    ArrayList <Node> tempNode;
    public BinarySearch() {
        temp = new ArrayList<>();
        root = null;
    }

    public BinarySearch(ArrayList <Node> arr) {
        constructTree(arr);
    }
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
     * Construct BSTs from Arraylist
     * @param
     */
    public void constructTree(ArrayList<Node> x) {
        // Make tree called from loginActivity
        helpConstructTree(x, 0, x.size() - 1);
    }

    private Node helpConstructTree(ArrayList<Node> arr, int lowerBound, int higherBound) {
        if (lowerBound > higherBound)
            return null;
        int middle = (lowerBound + higherBound)/2;
        Node node = arr.get(middle);
        node.left = helpConstructTree(arr, lowerBound, middle -1);
        node.right = helpConstructTree(arr, middle+1,higherBound);
        return node;
    }

    // Return the nodes of phrases of a particular section
    public ArrayList<Node> sectionNodes(int low, int high) {
        tempNode.clear();
        helpSectionNode(root, low, high);
        return tempNode;
    }

    public void helpSectionNode(Node node, int low, int high) {
        if (node == null) {
            return;
        }
        if (low < node.ID)
            helpSectionNode(node.left, low, high);
        if (low <= node.ID && high >= node.ID) {
            tempNode.add(node);
        }
        if (high > node.ID)
            helpSectionNode(node.right, low, high);
    }

}