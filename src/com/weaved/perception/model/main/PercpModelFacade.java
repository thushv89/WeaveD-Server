/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weaved.perception.model.main;

import com.weaved.config.models.IKASLConfigModel;
import com.weaved.config.models.ImportantPercpConfigModel;
import com.weaved.config.models.PercpConfigModel;
import com.weaved.config.models.elememts.IKASLConfigModelElement;
import com.weaved.config.models.elememts.ImportantPercpConfigModelElement;
import com.weaved.config.models.elememts.PercpConfigModelElement;
import com.weaved.perception.model.objects.PerceptionHierarchy;
import com.weaved.perception.model.objects.PerceptionHierarchyNode;
import java.util.ArrayList;

/**
 *
 * @author Thushan Ganegedara
 */
public class PercpModelFacade {
    
    private PerceptionHierarchy perceptionHierarchy;

    /**
     * Sets the Full PerceptionHierarchy With Following 3 XML Files
     *
     * @param percpConfigModel
     * @param iKASLConfigModel
     * @param importantPercpConfigModel
     * @return PerceptionHierarchy
     */
    public PerceptionHierarchy createPerceptionHierarchy(PercpConfigModel percpConfigModel, IKASLConfigModel iKASLConfigModel, ImportantPercpConfigModel importantPercpConfigModel) {
        perceptionHierarchy = new PerceptionHierarchy();
        ArrayList<PerceptionHierarchyNode> perceptionHierarchyNodes = new ArrayList<PerceptionHierarchyNode>();
        for (PercpConfigModelElement percpConfigModelElement : percpConfigModel.getPercpModelElements()) {
            
            if (!percpConfigModelElement.getStackId().equals("L-1F-1")) {
                IKASLConfigModelElement iKASLConfigModelElement = getIKASLConfigModelElementFromModel(iKASLConfigModel, percpConfigModelElement.getStackId());
                ImportantPercpConfigModelElement importantPercpConfigModelElement = getImportantPercpConfigModelElementFromModel(importantPercpConfigModel, percpConfigModelElement.getStackId());
                PerceptionHierarchyNode perceptionHierarchyNode = new PerceptionHierarchyNode(percpConfigModelElement.getStackId());
                perceptionHierarchyNode.setStackName(percpConfigModelElement.getStackName());
                perceptionHierarchyNode.setParentElement(percpConfigModelElement.getParentElement());
                perceptionHierarchyNode.setSpreadFactor(iKASLConfigModelElement.getSpreadFactor());
                perceptionHierarchyNode.setMaxIterations(iKASLConfigModelElement.getMaxIterations());
                perceptionHierarchyNode.setMaxNeighborhoodRadius(iKASLConfigModelElement.getMaxNeighborhoodRadius());
                perceptionHierarchyNode.setStartLearningRate(iKASLConfigModelElement.getStartLearningRate());
                perceptionHierarchyNode.setHitThreshold(iKASLConfigModelElement.getHitThreshold());
                perceptionHierarchyNode.setIsSelected(importantPercpConfigModelElement.isIsSelected());
                perceptionHierarchyNodes.add(perceptionHierarchyNode);
            } else {
                PerceptionHierarchyNode perceptionHierarchyNode = new PerceptionHierarchyNode(percpConfigModelElement.getStackId());
                perceptionHierarchyNode.setParentElement(null);
                perceptionHierarchyNodes.add(perceptionHierarchyNode);
            }
        }
        perceptionHierarchy.setPerceptionHierarchyNodes(perceptionHierarchyNodes);
        return perceptionHierarchy;
    }

    /**
     * Retrieve IKASLConfigModelElement in IKASLConfigModel for a given IKASLID
     *
     * @param iKASLConfigModel
     * @param ikaslStackID
     * @return IKASLConfigModelElement
     */
    public IKASLConfigModelElement getIKASLConfigModelElementFromModel(IKASLConfigModel iKASLConfigModel, String ikaslStackID) {
        
        IKASLConfigModelElement iKASLConfigModelElement = null;
        for (IKASLConfigModelElement modelElement : iKASLConfigModel.getiKASLConfigModelElements()) {
            if (ikaslStackID.equals(modelElement.getStackId())) {
                iKASLConfigModelElement = modelElement;
                break;
            }
        }
        return iKASLConfigModelElement;
    }

    /**
     * Retrieve ImportantPercpConfigModelElement in ImportantPercpConfigModel
     *
     * @param importantPercpConfigModel
     * @param ikaslStackID
     * @return ImportantPercpConfigModelElement
     */
    public ImportantPercpConfigModelElement getImportantPercpConfigModelElementFromModel(ImportantPercpConfigModel importantPercpConfigModel, String ikaslStackID) {
        
        ImportantPercpConfigModelElement importantPercpConfigModelElement = null;
        for (ImportantPercpConfigModelElement modelElement : importantPercpConfigModel.getImportantPercpConfigModelElements()) {
            if (ikaslStackID.equals(modelElement.getIkaslStackID())) {
                importantPercpConfigModelElement = modelElement;
                break;
            }
        }
        return importantPercpConfigModelElement;
    }
}
