/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weaved.perception.model.objects;

import java.util.ArrayList;

/**
 *
 * @author Lasindu
 */
public class PerceptionHierarchy {

    private ArrayList<PerceptionHierarchyNode> perceptionHierarchyNodes;

    public PerceptionHierarchy() {
        perceptionHierarchyNodes = new ArrayList<PerceptionHierarchyNode>();
    }

    /**
     * @return the perceptionHierarchyNodes
     */
    public ArrayList<PerceptionHierarchyNode> getPerceptionHierarchyNodes() {
        return perceptionHierarchyNodes;
    }

    /**
     * @param perceptionHierarchyNodes the perceptionHierarchyNodes to set
     */
    public void setPerceptionHierarchyNodes(ArrayList<PerceptionHierarchyNode> perceptionHierarchyNodes) {
        this.perceptionHierarchyNodes = perceptionHierarchyNodes;
    }
}
