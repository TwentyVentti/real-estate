package com.example.myproject;

import com.example.myproject.Models.BST.BinarySearch;
import com.example.myproject.Models.BST.Node;

import org.testng.annotations.Test;
import static org.testng.AssertJUnit.assertEquals;
import java.util.ArrayList;

public class BinaryTreeTest {

    private static final int[] testSimple = new int[]{1, 2, 3, 4, 5, 6, 7};
    private static final ArrayList<Node> testArrayConstruct = new ArrayList<>();
    @Test
    public void testSortedConstruction() {
        BinarySearch bs = new BinarySearch();
        bs.construct(testSimple);
        bs.inOrder();
    }

    @Test
    public void testArraySortedConstruction() {
        testArrayConstruct.add(new Node(1101, "Hello", "Bonjour"));
        testArrayConstruct.add(new Node(1102, "How you doing", "Comment Cava"));
        testArrayConstruct.add(new Node(1103, "I am fine", "Cava bien"));
        testArrayConstruct.add(new Node(1105, "I am fine", "Cava bien"));
        testArrayConstruct.add(new Node(1106, "What about you", "Et toi"));
        testArrayConstruct.add(new Node(1108, "Thank you", "Merci"));
        BinarySearch bs = new BinarySearch();
        bs.constructTree(testArrayConstruct);
        bs.inOrder();
    }

    @Test
    public void testBSTRange() {
        testArrayConstruct.add(new Node(1101, "Hello", "Bonjour"));
        testArrayConstruct.add(new Node(1102, "How you doing", "Comment Cava"));
        testArrayConstruct.add(new Node(1105, "I am fine", "Cava bien"));
        testArrayConstruct.add(new Node(1106, "What about you", "Et toi"));
        testArrayConstruct.add(new Node(1108, "Thank you", "Merci"));
        BinarySearch bs = new BinarySearch();
        bs.constructTree(testArrayConstruct);
        ArrayList<Node> x = bs.sectionNodes(1102,1106);
        assertEquals(x.size(),3);
    }

}
