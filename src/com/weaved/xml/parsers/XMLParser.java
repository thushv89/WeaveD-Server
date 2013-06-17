/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weaved.xml.parsers;

import com.weaved.config.models.IKASLConfigModel;
import com.weaved.config.models.ImportantPercpConfigModel;
import com.weaved.config.models.LinkConfigModel;
import com.weaved.config.models.PercpConfigModel;
import com.weaved.config.models.elememts.IKASLConfigModelElement;
import com.weaved.config.models.elememts.ImportantPercpConfigModelElement;
import com.weaved.config.models.elememts.PercpConfigModelElement;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Thushan Ganegedara
 */
public class XMLParser {
    
    private PercpConfigModel percpConfigModel;
    private IKASLConfigModel iKASLConfigModel;
    private ImportantPercpConfigModel imporatantPercpConfigModel;

    /**
     * Creates IKASLConfigModel with IKASLConfigModelElements
     *
     * @param ikaslDataXml XML Configuration file with IKASL Parameters
     * @return IKASLConfigModel with IKASL Parameters set
     */
    public IKASLConfigModel createIKASLConfiguration(String ikaslDataXml) {
        
        iKASLConfigModel = new IKASLConfigModel();
        ArrayList<IKASLConfigModelElement> iKASLConfigModelElements = new ArrayList<IKASLConfigModelElement>();
        File fXmlFile = new File(ikaslDataXml);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        Document doc = null;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            try {
                doc = dBuilder.parse(fXmlFile);
                doc.getDocumentElement().normalize();
            } catch (SAXException ex) {
                Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("ikasl_stack");
        
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                IKASLConfigModelElement iKASLConfigModelElement = new IKASLConfigModelElement();
                // Create a PercpModelElement with IKASL Attributes
                iKASLConfigModelElement.setStackId(eElement.getAttribute("id"));
                iKASLConfigModelElement.setSpreadFactor(Double.parseDouble(eElement.getElementsByTagName("SF").item(0).getTextContent()));
                iKASLConfigModelElement.setMaxIterations(Integer.parseInt(eElement.getElementsByTagName("ITR").item(0).getTextContent()));
                iKASLConfigModelElement.setMaxNeighborhoodRadius(Double.parseDouble(eElement.getElementsByTagName("NR").item(0).getTextContent()));
                iKASLConfigModelElement.setStartLearningRate(Double.parseDouble(eElement.getElementsByTagName("LR").item(0).getTextContent()));
                iKASLConfigModelElement.setHitThreshold(Integer.parseInt(eElement.getElementsByTagName("HT").item(0).getTextContent()));
                // Add Elements to ArrayList
                iKASLConfigModelElements.add(iKASLConfigModelElement);
            }
        }
        iKASLConfigModel.setiKASLConfigModelElements(iKASLConfigModelElements);
        return iKASLConfigModel;
        
    }

    /**
     * Creates a tree of Perception Model with the given configuration XML File
     *
     * @param configModelXml Perception Configuration Model XML file Path
     * @return PercpConfigModel PercpConfigModel object with Tree Structure
     */
    public PercpConfigModel createPercpConfigModel(String configModelXml) {
        
        percpConfigModel = new PercpConfigModel();
        ArrayList<PercpConfigModelElement> percpModelElementsList = new ArrayList<PercpConfigModelElement>(); // Create a Root Element
        
        PercpConfigModelElement rootElement = new PercpConfigModelElement("L-1F-1");
        percpModelElementsList.add(rootElement); // Add Root to ArrayList
        File fXmlFile = new File(configModelXml);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        Document doc = null;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            try {
                doc = dBuilder.parse(fXmlFile);
                doc.getDocumentElement().normalize();
            } catch (SAXException ex) {
                Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("perception");
        
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                PercpConfigModelElement percpModelPerceptionElement = new PercpConfigModelElement(eElement.getAttribute("id"));
                PercpConfigModelElement parentRoot = rootElement;
                percpModelPerceptionElement.setStackName(eElement.getAttribute("name"));
                percpModelPerceptionElement.setParentElement(parentRoot);
                percpModelElementsList.add(percpModelPerceptionElement);
                NodeList featureNodes = nNode.getChildNodes();
                
                for (int i = 0; i < featureNodes.getLength(); i++) {
                    Node featureNode = featureNodes.item(i); // feature nodes
                    if (featureNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element featureElement = (Element) featureNode;
                        PercpConfigModelElement percpModelFeatureElement = new PercpConfigModelElement(featureElement.getAttribute("id"));
                        percpModelFeatureElement.setStackName(featureElement.getAttribute("name"));
                        percpModelFeatureElement.setParentElement(percpModelPerceptionElement);
                        percpModelElementsList.add(percpModelFeatureElement);
                        NodeList dimensionNodes = featureNode.getChildNodes();
                        
                        for (int j = 0; j < dimensionNodes.getLength(); j++) {
                            Node dimensionNode = dimensionNodes.item(j);
                            if (dimensionNode.getNodeType() == Node.ELEMENT_NODE) {
                                Element dimElement = (Element) dimensionNode;
                                PercpConfigModelElement percpModelDimElement = new PercpConfigModelElement(dimElement.getAttribute("id"));
                                percpModelDimElement.setStackName(dimElement.getAttribute("name"));
                                percpModelDimElement.setParentElement(percpModelFeatureElement);
                                percpModelElementsList.add(percpModelDimElement);
                            }
                        }
                    }
                }
            }
            
        }
        percpConfigModel.setPercpModelElements(percpModelElementsList);
        return percpConfigModel;
    }

    /**
     * Sets the ImportantPercpConfigModel for the given XML File
     *
     * @param importantConfigXml Path for importantPercepConfig XML
     * @return ImportantPercpConfigModel
     */
    public ImportantPercpConfigModel createImportantConfigModel(String importantConfigXml) {
        
        boolean selected = false;
        imporatantPercpConfigModel = new ImportantPercpConfigModel();
        ArrayList<ImportantPercpConfigModelElement> importantPercpConfigModelElements = new ArrayList<ImportantPercpConfigModelElement>();
        
        File fXmlFile = new File(importantConfigXml);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        Document doc = null;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            try {
                doc = dBuilder.parse(fXmlFile);
                doc.getDocumentElement().normalize();
            } catch (SAXException ex) {
                Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("ikasl_stack");
        
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                int isSelected = Integer.parseInt(eElement.getFirstChild().getNodeValue());
                if (isSelected == 1) {
                    selected = true;
                } else if (isSelected == 0) {
                    selected = false;
                }
                ImportantPercpConfigModelElement importantPercpConfigModelElement = new ImportantPercpConfigModelElement(eElement.getAttribute("id"), selected);
                importantPercpConfigModelElements.add(importantPercpConfigModelElement);
            }
        }
        imporatantPercpConfigModel.setImportantPercpConfigModelElements(importantPercpConfigModelElements);
        return imporatantPercpConfigModel;
    }

    /**
     * Creates the LinkConfigModel with all Cross and TemporalLinks
     *
     * @param linkConfigXml Link Configuration XML Path
     * @return LinkConfigModel with Cross and Temporal Links set
     */
    public LinkConfigModel createLinkConfigModel(String linkConfigXml) {
        
        LinkConfigModel linkConfigModel = new LinkConfigModel();
        ArrayList<String> crossLinks = new ArrayList<String>();
        ArrayList<String> temporalLinks = new ArrayList<String>();
        File fXmlFile = new File(linkConfigXml);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        Document doc = null;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            try {
                doc = dBuilder.parse(fXmlFile);
                doc.getDocumentElement().normalize();
            } catch (SAXException ex) {
                Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        doc.getDocumentElement().normalize();
        NodeList crossLinkList = doc.getElementsByTagName("crossLink");
        NodeList temporalLinkList = doc.getElementsByTagName("temporalLink");
        // Iterate through Cross Links
        for (int i = 0; i < crossLinkList.getLength(); i++) {
            Node nNode = crossLinkList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                crossLinks.add(eElement.getFirstChild().getNodeValue());
            }
        }
        // Iterate through Temporal links
        for (int j = 0; j < temporalLinkList.getLength(); j++) {
            Node node = temporalLinkList.item(j);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;
                temporalLinks.add(eElement.getFirstChild().getNodeValue());
            }
        }
        linkConfigModel.setCrossLinks(crossLinks); // Set Cross Links
        linkConfigModel.setTemporalLinks(temporalLinks); // Set Temporal Links

        return linkConfigModel;
    }

    /**
     * Finds the PercpModelElement for a given IKASL ID from the ArrayList
     *
     * @param ikaslId String IKASLID to be searched
     * @param percpElementList List of PercpModelElements
     * @return PercpModelElement
     */
    public PercpConfigModelElement getElementById(String ikaslId, ArrayList<PercpConfigModelElement> percpElementList) {
        
        PercpConfigModelElement element = null;
        for (PercpConfigModelElement percpModelElement : percpElementList) {
            if (percpModelElement.getStackId().equals(ikaslId)) {
                element = percpModelElement;
                break;
            }
        }
        return element;
    }
}
