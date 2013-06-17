/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weaved.config.models.elememts;

/**
 *
 * @author Thushan Ganegedara 
 * PercpModelElement is used to represent a single
 * element in the perception model related configuration file. This file holds
 * the perception hierarchy we're going to use in the system. Therefore, this
 * hierarchy will be represented by an arrayList of PercpModelElements
 */
public class PercpConfigModelElement {

    private String stackId;
    private String stackName;
    private PercpConfigModelElement parentElement;

    public PercpConfigModelElement(String stackId) {
        this.stackId = stackId;
    }

    /**
     * @return the parentElement
     */
    public PercpConfigModelElement getParentElement() {
        return parentElement;
    }

    /**
     * @param parentElement the parentElement to set
     */
    public void setParentElement(PercpConfigModelElement parentElement) {
        this.parentElement = parentElement;
    }

    public String getStackId() {
        return stackId;
    }

    /**
     * @return the stackName
     */
    public String getStackName() {
        return stackName;
    }

    /**
     * @param stackName the stackName to set
     */
    public void setStackName(String stackName) {
        this.stackName = stackName;
    }
}
