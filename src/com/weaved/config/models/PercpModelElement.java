/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weaved.config.models;

/**
 *
 * @author Thushan Ganegedara PercpModelElement is used to represent a single
 * element in the perception model related configuration file. This file holds
 * the perception hierarchy we're going to use in the system. Therefore, this
 * hierarchy will be represented by an arrayList of PercpModelElements
 */
public class PercpModelElement {

    private String stackId;
    private PercpModelElement parentElement;
    private double spreadFactor;
    private int maxIterations;
    private double maxNeighborhoodRadius;
    private double startLearningRate;
    private int hitThreshold;
    private boolean isSelected;

    public PercpModelElement(String stackId) {
        this.stackId = stackId;
    }

    /**
     * @return the parentElement
     */
    public PercpModelElement getParentElement() {
        return parentElement;
    }

    /**
     * @param parentElement the parentElement to set
     */
    public void setParentElement(PercpModelElement parentElement) {
        this.parentElement = parentElement;
    }

    /**
     * @return the spreadFactor
     */
    public double getSpreadFactor() {
        return spreadFactor;
    }

    /**
     * @param spreadFactor the spreadFactor to set
     */
    public void setSpreadFactor(double spreadFactor) {
        this.spreadFactor = spreadFactor;
    }

    /**
     * @return the maxIterations
     */
    public int getMaxIterations() {
        return maxIterations;
    }

    /**
     * @param maxIterations the maxIterations to set
     */
    public void setMaxIterations(int maxIterations) {
        this.maxIterations = maxIterations;
    }

    /**
     * @return the maxNeighborhoodRadius
     */
    public double getMaxNeighborhoodRadius() {
        return maxNeighborhoodRadius;
    }

    /**
     * @param maxNeighborhoodRadius the maxNeighborhoodRadius to set
     */
    public void setMaxNeighborhoodRadius(double maxNeighborhoodRadius) {
        this.maxNeighborhoodRadius = maxNeighborhoodRadius;
    }

    /**
     * @return the startLearningRate
     */
    public double getStartLearningRate() {
        return startLearningRate;
    }

    /**
     * @param startLearningRate the startLearningRate to set
     */
    public void setStartLearningRate(double startLearningRate) {
        this.startLearningRate = startLearningRate;
    }

    /**
     * @return the hitThreshold
     */
    public int getHitThreshold() {
        return hitThreshold;
    }

    /**
     * @param hitThreshold the hitThreshold to set
     */
    public void setHitThreshold(int hitThreshold) {
        this.hitThreshold = hitThreshold;
    }

    /**
     * @return the stackId
     */
    public String getStackId() {
        return stackId;
    }

    /**
     * @return the isSelected
     */
    public boolean isIsSelected() {
        return isSelected;
    }

    /**
     * @param isSelected the isSelected to set
     */
    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
}
