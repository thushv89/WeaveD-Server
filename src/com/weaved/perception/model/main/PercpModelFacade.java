/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weaved.perception.model.main;

import com.weaved.config.loaders.IKASLConfigLoader;
import com.weaved.config.loaders.ImportantPercpConfigLoader;
import com.weaved.config.loaders.LinkGeneratorConfigLoader;
import com.weaved.config.loaders.PercpModelConfigLoader;
import com.weaved.config.models.IKASLConfigModel;
import com.weaved.config.models.ImportantPercpConfigModel;
import com.weaved.config.models.LinkConfigModel;
import com.weaved.config.models.PercpModelConfigModel;
import com.weaved.config.models.elememts.IKASLConfigModelElement;
import com.weaved.config.models.elememts.ImportantPercpConfigModelElement;
import com.weaved.config.models.elememts.PercpConfigModelElement;
import com.weaved.perception.model.objects.PerceptionHierarchyNode;
import com.weaved.utils.Tree;
import java.util.ArrayList;

/**
 *
 * @author Thushan Ganegedara
 */
public class PercpModelFacade {
    
    private Tree<String> perceptionIDHierarchy;
    private ArrayList<IKASLConfigModelElement> ikaslParamList;

    /**
     * Sets the Full PerceptionHierarchy With Following 3 XML Files
     *
     * @param percpConfigModel
     * @param iKASLConfigModel
     * @param importantPercpConfigModel
     * @return PerceptionHierarchy
     */
    private void createPerceptionHierarchy(PercpModelConfigModel percpConfigModel, IKASLConfigModel iKASLConfigModel, ImportantPercpConfigModel importantPercpConfigModel) {
        perceptionIDHierarchy =  percpConfigModel.getPercpModelTree();                
        ikaslParamList = iKASLConfigModel.getiKASLConfigModelElements();
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
    
    PercpModelConfigModel pmConfModel;
    IKASLConfigModel ikaslModel;
    LinkConfigModel linkConfModel;
    ImportantPercpConfigModel ipConfModel;
    
    //ArrayList<String>
    public void loadAllConfig(){
        PercpModelConfigLoader pmConfLoader = new PercpModelConfigLoader();
        IKASLConfigLoader ikaslLoader = new IKASLConfigLoader();
        LinkGeneratorConfigLoader lgConfLoader = new LinkGeneratorConfigLoader();
        ImportantPercpConfigLoader ipConfLoader = new ImportantPercpConfigLoader();
        
        //finds the config file in the folder struction and load configuration to a model
        pmConfLoader.loadConfig("STRING_PATH_TO_PERCP_MODEL_CONFIG");
        ikaslLoader.loadConfig("STRING_PATH_TO_IKASL_CONFIG");
        lgConfLoader.loadConfig("STRING_PATH_TO_LINK_GEN_CONFIG");
        ipConfLoader.loadConfig("STRING_PATH_TO_IMPORTANT_PERCP_CONFIG");
        
        pmConfModel = (PercpModelConfigModel) pmConfLoader.getPopulatedConfigModel();
        ikaslModel = (IKASLConfigModel) ikaslLoader.getPopulatedConfigModel();
        linkConfModel = (LinkConfigModel) lgConfLoader.getPopulatedConfigModel();
        ipConfModel =  (ImportantPercpConfigModel) ipConfLoader.getPopulatedConfigModel();
        
        createPerceptionHierarchy(pmConfModel, ikaslModel, ipConfModel);
    
    }
    
    public void runIKASL(){
    
    }
    
    public void fusePerceptions(){
    
    }
    
    public void runLinkGeneration(){
    
    }

    
}
