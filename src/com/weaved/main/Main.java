/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weaved.main;

import com.weaved.config.models.LinkConfigModel;
import com.weaved.config.models.PercpConfigModel;
import com.weaved.config.models.PercpModelElement;
import com.weaved.xml.parsers.XMLParser;
import java.util.ArrayList;

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
        XMLParser xMLParser = new XMLParser();
        ArrayList<PercpModelElement> percpModelElements = xMLParser.getPerceptionConfiguration("ikasl_params.xml");
        PercpConfigModel percpConfigModel = xMLParser.createConfigModel("perception_config_model.xml", percpModelElements);
        PercpConfigModel afterImpConfig = xMLParser.importantCongigXml("important_percep_config.xml", percpConfigModel);
        LinkConfigModel linkConfigModel = xMLParser.getLinksFromXml("link_config_model.xml");
    }
}
