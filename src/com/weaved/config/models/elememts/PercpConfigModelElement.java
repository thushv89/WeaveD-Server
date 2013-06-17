/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weaved.config.models.elememts;

/**
 *
 * @author Thushan Ganegedara PercpModelElement is used to represent a single
 * element in the perception model related configuration file. This file holds
 * the perception hierarchy we're going to use in the system. Therefore, this
 * hierarchy will be represented by an arrayList of PercpModelElements
 */
public class PercpConfigModelElement {

    private String stackId;
    private PercpConfigModelElement parentElement;
    private PercpConfigModelElement childElement;

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
     * @return the childElement
     */
    public PercpConfigModelElement getChildElement() {
        return childElement;
    }

    /**
     * @param childElement the childElement to set
     */
    public void setChildElement(PercpConfigModelElement childElement) {
        this.childElement = childElement;
    }
}
