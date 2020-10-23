package com.example.myproject;

import com.example.myproject.Models.BST.BinarySearch;
import com.example.myproject.Models.BST.Node;

import org.testng.annotations.Test;
import static org.testng.AssertJUnit.assertEquals;
import java.util.ArrayList;

/**
 * Note: Test them individually since testBSTRange() and
 */
public class BinaryTreeTest {

    private static final ArrayList<Node> testArrayConstruct = new ArrayList<>();

    @Test
    public void testArraySortedConstruction() {
        testArrayConstruct.clear();
        testArrayConstruct.add(new Node(1101, "Hello", "Bonjour"));
        testArrayConstruct.add(new Node(1102, "How you doing", "Comment Cava"));
        testArrayConstruct.add(new Node(1103, "I am fine", "Cava bien"));
        testArrayConstruct.add(new Node(1105, "I am fine", "Cava bien"));
        testArrayConstruct.add(new Node(1106, "What about you", "Et toi"));
        testArrayConstruct.add(new Node(1108, "Thank you", "Merci"));
        BinarySearch bs = new BinarySearch();
        bs.inOrder();
    }

    @Test
    public void testBSTRange() {
        testArrayConstruct.clear();
        testArrayConstruct.add(new Node(1101, "Hello", "Bonjour"));
        testArrayConstruct.add(new Node(1102, "How you doing", "Comment Cava"));
        testArrayConstruct.add(new Node(1105, "I am fine", "Cava bien"));
        testArrayConstruct.add(new Node(1106, "What about you", "Et toi"));
        testArrayConstruct.add(new Node(1108, "Thank you", "Merci"));
        BinarySearch bst = new BinarySearch(testArrayConstruct);
        ArrayList<Node> x = bst.sectionNodes(1102,1106);
        assertEquals(x.size(),3);
    }

}
