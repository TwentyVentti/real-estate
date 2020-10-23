package com.example.myproject.Models.BST;

import java.util.ArrayList;

/**
 * BST implementation for searchActivity and phraseListActivity
 * @author Abhaas Goyal - u7145384
 */
public class BinarySearch {

    Node root;
    ArrayList <Integer> tempInteger;
    ArrayList <Node> tempNode;
    public BinarySearch() {
        tempInteger = new ArrayList<>();
        tempNode = new ArrayList<>();
        root = null;
    }

    public BinarySearch(ArrayList <Node> arr) {
        tempInteger = new ArrayList<>();
        tempNode = new ArrayList<>();
        root = null;
        constructTree(arr);
        inOrder();
    }

    /**
     * Inorder traversal of BST
     * @return Arraylist of integers
     */
    public ArrayList<Integer> inOrder() {
        tempInteger.clear();
        helpInOrder(root);
        return tempInteger;
    }
    private void helpInOrder(Node node) {
        if (node == null) {
            return;
        }
        helpInOrder(node.left);
        tempInteger.add(node.ID);

        //Check whether nodes are being added perfectly
        helpInOrder(node.right);
    }

    /**
     *
     * @param level The level of competence the user is categorized as.
     * @return A list pertaining to all the phrases available to users in that level.
     */
    public ArrayList<Node> getArrayFromLevel(int level) {
        return sectionNodes(10000, (level+1) * 10000);
    }

    public ArrayList<Node> getArrayFromLevelSection(int level, int section) {
        return sectionNodes(level * 10000 + section * 1000, level * 10000 + section * 1000 + 999);
    }

    /**
     * Construct BSTs from sorted Arraylist of Nodes
     * @param x - ArrayList of Nodes
     */
    public void constructTree(ArrayList<Node> x) {
        // Make tree called from loginActivity
        root = helpConstructTree(x, 0, x.size() - 1);
    }

    /**
     * Helper function to construct Balanced BST from construct()
     * @param arr Input arraylist of nodes
     * @param lowerBound lower bound of array used to create subtree
     * @param higherBound higher bound of array used to create subtree
     * @return Balanced Subtree
     */
    private Node helpConstructTree(ArrayList<Node> arr, int lowerBound, int higherBound) {
        if (lowerBound > higherBound)
            return null;
        int middle = (lowerBound + higherBound)/2;
        Node node = arr.get(middle);
        node.left = helpConstructTree(arr, lowerBound, middle -1);
        node.right = helpConstructTree(arr, middle+1,higherBound);
        return node;
    }

    /**
     * Return Sorted Arraylist given a particular rangle
     * @param low Lower bound of range
     * @param high Higher bound for range
     * @return Bounded Sorted Arraylist
     */
    public ArrayList<Node> sectionNodes(int low, int high) {
        if (tempNode != null)
            tempNode.clear();
        assert tempNode != null;
        System.out.println(tempNode.size());
        helpSectionNode(root, low, high);
        return tempNode;
    }

    /**
     * Helper function to created bounded sorted arraylist
     */
    public void helpSectionNode(Node node, int low, int high) {
        if (node == null) {
            return;
        }
        if (low < node.ID) {
            helpSectionNode(node.left, low, high);
        }
        if (low <= node.ID && high >= node.ID) {
//            System.out.println(node.ID);
            tempNode.add(node);
        }
        if (high > node.ID) {
//            System.out.println(2);
            helpSectionNode(node.right, low, high);
        }
    }

}