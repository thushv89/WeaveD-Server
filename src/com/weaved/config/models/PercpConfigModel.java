/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weaved.config.models;

import java.util.ArrayList;

/**
 *
 * @author Thushan Ganegedara
 *
 * This class contains the Perception Hierarchy of the system For example
 * Existence Color < Proportion Images < Texture ... < Text ... ...
 *
 * This information will be residing in this entity
 */
public class PercpConfigModel extends ConfigModel {

    private ArrayList<PercpModelElement> percpHeirarchy;

    public PercpConfigModel() {
        percpHeirarchy = new ArrayList<PercpModelElement>();
    }

    /**
     * @return the percpHeirarchy
     */
    public ArrayList<PercpModelElement> getPercpHeirarchy() {
        return percpHeirarchy;
    }

    /**
     * @param percpHeirarchy the percpHeirarchy to set
     */
    public void setPercpHeirarchy(ArrayList<PercpModelElement> percpHeirarchy) {
        this.percpHeirarchy = percpHeirarchy;
    }
}
