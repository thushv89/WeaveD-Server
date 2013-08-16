/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weaved.utils;

import com.sun.org.apache.xpath.internal.functions.Function;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.SortedSet;

/**
 *
 * @author Thushan Ganegedara
 */
public class Tree<T> {

    ArrayList<TreeNode<T>> tree = new ArrayList<TreeNode<T>>();
    
    public void addNode(T nodeObj, T parentObj){
        boolean isInTree = false;
        TreeNode<T> parent = null;
        for(TreeNode<T> node: tree){
            if(node.getNodeVal().equals(parentObj)){
                isInTree = true;
                parent = node;
                break;
            }
        }
        
        if(isInTree){
            TreeNode<T> newNode = new TreeNode<T>(nodeObj,parent);
            tree.add(newNode);
        }
        else{
            tree.add(new TreeNode<T>(nodeObj,null));
        }
        
        
    }
    
    public ArrayList<T> getChildren(T obj){
        ArrayList<T> children = new ArrayList<T>();
        for(TreeNode<T> node : tree){
            if(node.getParent()!=null && node.getParent().getNodeVal().equals(obj)){
                children.add(node.getNodeVal());
            }
        }
        return children;
    }
    
    public T getParent(T obj){
        for(TreeNode<T> node : tree){
            if(node.getNodeVal().equals(obj)){
                return node.getParent().getNodeVal();
            }
        }
        return null;
    }
    
    public ArrayList<T> getAllNodeVals(){
        ArrayList<T> allNodeVals = new ArrayList<T>();
        for(TreeNode<T> node : tree){
            allNodeVals.add(node.getNodeVal());
        }
        return allNodeVals;
    }
}

