/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weaved.utils;

import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Thushan Ganegedara
 */
public class TreeTest {
    
    Tree<String> tree;
    
    
    @Before
    public void setUp() {
        
    }
    
    @After
    public void tearDown() {
        tree = null;
    }

    /**
     * Test of addNode method, of class Tree.
     */
    @Test
    public void testAddNodeForWhetherTreeHasAllTheInsertedNodes() {
        tree = new Tree<String>();
        tree.addNode("str1", null);
        tree.addNode("str2", null);
        ArrayList<String> nodeVals = tree.getAllNodeVals();
        assertEquals(nodeVals.size(), 2);
    }
    
    @Test
    public void testAddNodeForWhetherTheTreeNodesHasTheCorrectValuesAndNodeParentsNull(){
        tree = new Tree<String>();
        tree.addNode("str1", null);
        tree.addNode("str2", null);
        ArrayList<String> nodeVals = tree.getAllNodeVals();
        assertEquals(nodeVals.get(0), "str1");
        assertEquals(nodeVals.get(1), "str2");
    }
    
    /**
     * Test of getChildren method, of class Tree.
     */
    @Test
    public void testGetChildrenWithSingleParentAndTwoChildren() {
        tree = new Tree<String>();
        tree.addNode("str1", null);
        tree.addNode("str2", "str1");
        tree.addNode("str3", "str1");        
        ArrayList<String> children = tree.getChildren("str1");
        assertEquals(children.get(0), "str2");
        assertEquals(children.get(1), "str3");
    }

    /**
     * Test of getParent method, of class Tree.
     */
    @Test
    public void testGetParentWithSingleParentAndTwoChildren() {
        tree = new Tree<String>();
        tree.addNode("str1", null);
        tree.addNode("str2", "str1");
        tree.addNode("str3", "str1");        
        assertEquals(tree.getParent("str2"), "str1");
        assertEquals(tree.getParent("str3"), "str1");
    }
}
