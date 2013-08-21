/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weaved.main;

import com.ikasl.objects.IKASLParams;
import com.ikasl.utils.IKASLConstants;
import com.weaved.config.models.LinkConfigModel;
import com.weaved.input.NumericalDataParser;
import com.weaved.perception.model.main.PercpModelFacade;
import com.weaved.query.enums.QueryObjectType;
import com.weaved.xml.parsers.XMLParser;
import java.util.ArrayList;

/**
 *
 * @author Thushan Ganegedara
 */
public class Main {

    /*--------------------------------------------------------
     * Things to beware of
     * 1. Make sure you delete the lastGLayer.ser before runing IKASL from the scratch. 
     *    Otherwise IKASL will continue from lastGLayer.ser
     * 2. Make sure you set the correct Min and Max bounds in IKASL Constants
    ---------------------------------------------------------*/
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        PercpModelFacade percpModelFacade = new PercpModelFacade();
        //percpModelFacade.runLinkGeneration("","" , true, true);
        NumericalDataParser parser = new NumericalDataParser();
        parser.parseInput("input1_img.txt");
        ArrayList<double[]> imgIWeights = parser.getiWeights();
        ArrayList<String> imgINames = parser.getiNames();
        
        NumericalDataParser parser2 = new NumericalDataParser();
        parser2.parseInput("input1_txt.txt");
        ArrayList<double[]> txtIWeights = parser2.getiWeights();
        ArrayList<String> txtINames = parser2.getiNames();
        
        IKASLConstants.MIN_BOUND = 0;
        IKASLConstants.MAX_BOUND = 1;
        IKASLParams imgParams = new IKASLParams();
        imgParams.setDimensions(IKASLConstants.DIMENSIONS);
        imgParams.setSpreadFactor(0.45);
        imgParams.setMaxIterations(200);
        imgParams.setMaxNeighborhoodRadius(2);
        imgParams.setStartLearningRate(0.45);
        imgParams.setFD(0.2);
        imgParams.setAggregationType(0);
        imgParams.setHitThreshold(0);
        imgParams.setLearningCycleCount(1);
        percpModelFacade.runIKASLTest("L0F1", imgParams, imgIWeights, imgINames);
        
        IKASLConstants.MIN_BOUND = 0;
        IKASLConstants.MAX_BOUND = 100;
        IKASLParams txtParams = new IKASLParams();
        txtParams.setDimensions(IKASLConstants.DIMENSIONS);
        txtParams.setSpreadFactor(0.45);
        txtParams.setMaxIterations(200);
        txtParams.setMaxNeighborhoodRadius(2);
        txtParams.setStartLearningRate(0.45);
        txtParams.setFD(0.2);
        txtParams.setLearningCycleCount(1);
        txtParams.setAggregationType(0);
        txtParams.setHitThreshold(0);
        
        percpModelFacade.runIKASLTest("L0F2", txtParams, txtIWeights, txtINames);
        
        percpModelFacade.runLinkGeneration("L0F1", "L0F2", false, true);
        ArrayList<String>  test = percpModelFacade.getHorizontalLinksForQuery(QueryObjectType.IMAGE, new double[]{1,0,1,1,1,0,0,0,0,0,0,0,0,0,0});
        
        System.out.println("-------------------------- Link Gen finished -----------------------------");
        System.out.println(test.size());
        for(String s : test){
            System.out.println(s);
        }
    }
}
