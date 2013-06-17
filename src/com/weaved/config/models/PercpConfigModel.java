/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weaved.config.models;

import com.weaved.config.models.elememts.PercpConfigModelElement;
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

    private ArrayList<PercpConfigModelElement> percpModelElements;

    public PercpConfigModel() {
        percpModelElements = new ArrayList<PercpConfigModelElement>();
    }

    /**
     * @return the percpHeirarchy
     */
    public ArrayList<PercpConfigModelElement> getPercpModelElements() {
        return percpModelElements;
    }

    /**
     * @param percpHeirarchy the percpHeirarchy to set
     */
    public void setPercpModelElements(ArrayList<PercpConfigModelElement> percpHeirarchy) {
        this.percpModelElements = percpHeirarchy;
    }
}
