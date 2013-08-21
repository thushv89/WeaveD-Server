/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weaved.perception.model.main;

import com.ikasl.core.IKASLMain;
import com.ikasl.objects.CrossFeatureData;
import com.ikasl.objects.IKASLParams;
import com.ikasl.objects.TemporalLinkData;
import com.vhlinker.commands.VHLinkerCommand;
import com.vhlinker.main.VHLinkerFacade;
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
import com.weaved.utils.Tree;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

/**
 *
 * @author Thushan Ganegedara
 */
public class PercpModelFacade {

    private Tree<String> perceptionIDHierarchy;
    private ArrayList<IKASLConfigModelElement> ikaslParamList;
    private ArrayList<String> cfLinks;
    private ArrayList<IKASLMain> ikaslMainList;

    public PercpModelFacade() {
        ikaslMainList = new ArrayList<IKASLMain>();
    }

    private IKASLParams getIKASLParamsFromModelElement(IKASLConfigModelElement element) {
        IKASLParams params = new IKASLParams();
        params.setSpreadFactor(element.getSpreadFactor());
        params.setMaxNeighborhoodRadius(element.getMaxNeighborhoodRadius());
        params.setStartLearningRate(element.getStartLearningRate());
        params.setMaxIterations(element.getMaxIterations());
        params.setHitThreshold(element.getHitThreshold());

        //TODO: NEED TO SET DIMENSION
        //params.setDimensions("xxxxxxxxxx");

        return params;
    }

    /**
     * Sets the Full PerceptionHierarchy With Following 3 XML Files
     *
     * @param percpConfigModel
     * @param iKASLConfigModel
     * @param importantPercpConfigModel
     * @return PerceptionHierarchy
     */
    private void createPerceptionHierarchy(PercpModelConfigModel percpConfigModel, ImportantPercpConfigModel importantPercpConfigModel,
            IKASLConfigModel iKASLConfigModel, LinkConfigModel linkModel) {
        perceptionIDHierarchy = percpConfigModel.getPercpModelTree();
        ikaslParamList = iKASLConfigModel.getiKASLConfigModelElements();
        cfLinks = linkModel.getCrossLinks();
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
    public void loadAllConfig() {
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
        ipConfModel = (ImportantPercpConfigModel) ipConfLoader.getPopulatedConfigModel();

        createPerceptionHierarchy(pmConfModel, ipConfModel, ikaslModel, linkConfModel);

    }

    public void runIKASL(Tree<String> percpTree, ArrayList<IKASLConfigModelElement> ikaslParamList) {
        if (percpTree.getSize() == ikaslParamList.size()) {
            for (int i = 0; i < percpTree.getSize(); i++) {
                IKASLMain ikasl = new IKASLMain(null, null);
            }
        } else {
            System.out.println("Error dimension mismatch between, # of items in perception tree and ikasl param list");
        }
    }

    public void fusePerceptions() {
    }

    public void runLinkGeneration(String ikaslStack1Location, String ikaslStack2Location, boolean temporalLinksIsSet, boolean crossFLinksIsSet) {

        Properties prop = new Properties();

        try {
            //set the properties value
            prop.setProperty("sourceFolder1", ikaslStack1Location);
            prop.setProperty("sourceFolder2", ikaslStack2Location);

            //save properties to project root folder
            prop.store(new FileOutputStream("config.properties"), null);

        } catch (IOException ex) {
            ex.printStackTrace();
        }


        VHLinkerFacade vHLinkerFacade = new VHLinkerFacade();
        VHLinkerCommand vHLinkerCommand = vHLinkerFacade.generateVHLinkerCommand("config.properties", temporalLinksIsSet, crossFLinksIsSet);
        vHLinkerFacade.runLinkersWithCommand(vHLinkerCommand);

        CrossFeatureData crossFeatureData = vHLinkerFacade.getCrossLinkObject();
        TemporalLinkData temporalLinkData = vHLinkerFacade.getTemporalLinkObject();

    }
}
