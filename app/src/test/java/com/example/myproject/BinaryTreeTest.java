package com.example.myproject;

import com.example.myproject.Models.BST.BinarySearch;

import org.testng.annotations.Test;

public class BinaryTreeTest {

    private static final int[] testSimple = new int[]{1, 2, 3, 4, 5, 6, 7};

    @Test
    public void testSortedConstruction() {
        BinarySearch bs = new BinarySearch();
        bs.construct(testSimple);
        bs.inOrder();
    }

}
