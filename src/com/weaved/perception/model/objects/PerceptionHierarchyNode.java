/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weaved.perception.model.objects;

import com.weaved.config.models.elememts.PercpConfigModelElement;

/**
 *
 * @author Lasindu
 */
public class PerceptionHierarchyNode {

    private String stackId;
    private String StackName;
    private PercpConfigModelElement parentElement;
    private double spreadFactor;
    private int maxIterations;
    private double maxNeighborhoodRadius;
    private double startLearningRate;
    private int hitThreshold;
    private boolean isSelected;

    public PerceptionHierarchyNode(String stackId) {
        this.stackId = stackId;
    }

    /**
     * @return the stackId
     */
    public String getStackId() {
        return stackId;
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
     * @return the isSelected
     */
    public boolean getIsSelected() {
        return isSelected;
    }

    /**
     * @param isSelected the isSelected to set
     */
    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    /**
     * @return the StackName
     */
    public String getStackName() {
        return StackName;
    }

    /**
     * @param StackName the StackName to set
     */
    public void setStackName(String StackName) {
        this.StackName = StackName;
    }
}
