/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weaved.utils;

/**
 *
 * @author Thushan Ganegedara
 */
public class TreeNode<T> {
    
    private T nodeVal;
    private TreeNode<T> parent;
    
    public TreeNode (T value, TreeNode<T> parent){
        this.nodeVal = value;
        this.parent = parent;
    }

    /**
     * @return the nodeVal
     */
    public T getNodeVal() {
        return nodeVal;
    }

    /**
     * @param nodeVal the nodeVal to set
     */
    public void setNodeVal(T nodeVal) {
        this.nodeVal = nodeVal;
    }

    /**
     * @return the parent
     */
    public TreeNode<T> getParent() {
        return parent;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(TreeNode<T> parent) {
        this.parent = parent;
    }
    
    
}
