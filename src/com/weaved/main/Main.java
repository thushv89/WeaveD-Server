/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weaved.main;

import com.weaved.config.models.LinkConfigModel;
import com.weaved.perception.model.main.PercpModelFacade;
import com.weaved.xml.parsers.XMLParser;

/**
 *
 * @author Thushan Ganegedara
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // Bypassing for Testing
//        XMLParser xMLParser = new XMLParser();
//
//        PercpModelFacade percpModelFacade = new PercpModelFacade();
//        LinkConfigModel linkConfigModel = xMLParser.createLinkConfigModel("link_config_model.xml");
        /*PerceptionHierarchy perceptionHierarchy = percpModelFacade.createPerceptionHierarchy(xMLParser.createPercpConfigModel("perception_config_model.xml"), xMLParser.createIKASLConfiguration("ikasl_params.xml"), xMLParser.createImportantConfigModel("important_percep_config.xml"));

        for (PerceptionHierarchyNode perceptionHierarchyNode : perceptionHierarchy.getPerceptionHierarchyNodes()) {

            System.out.println("IKASL ID : " + perceptionHierarchyNode.getStackId());
            System.out.println("IKASL Name : " + perceptionHierarchyNode.getStackName());
            if (perceptionHierarchyNode.getParentElement() == null) {
                System.out.println("Parent : " + "null");
            } else {
                System.out.println("Parent : " + perceptionHierarchyNode.getParentElement().getStackId());
            }
            System.out.println("SF : " + perceptionHierarchyNode.getSpreadFactor());
            System.out.println("ITR : " + perceptionHierarchyNode.getMaxIterations());
            System.out.println("NR : " + perceptionHierarchyNode.getMaxNeighborhoodRadius());
            System.out.println("LR : " + perceptionHierarchyNode.getStartLearningRate());
            System.out.println("HT : " + perceptionHierarchyNode.getHitThreshold());
            System.out.println("Selected : " + perceptionHierarchyNode.getIsSelected());
            System.out.println("******************************");
        }*/

    }
}
