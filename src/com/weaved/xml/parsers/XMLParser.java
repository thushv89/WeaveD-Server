/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weaved.xml.parsers;

import com.weaved.config.models.LinkConfigModel;
import com.weaved.config.models.PercpConfigModel;
import com.weaved.config.models.PercpModelElement;
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

    private ArrayList<PercpModelElement> percpModelElements;

    /**
     * Creates an ArrayList of PercpModelElements from XML file
     *
     * @param ikaslDataXml - XML Configuration file with IKASL Parameters
     * @return ArrayList of PercpModelElement
     */
    public ArrayList<PercpModelElement> getPerceptionConfiguration(String ikaslDataXml) {

        percpModelElements = new ArrayList<>();

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
                // Create a PercpModelElement with IKASL Attributes
                PercpModelElement percpModelElement = new PercpModelElement(eElement.getAttribute("id"));
                percpModelElement.setSpreadFactor(Double.parseDouble(eElement.getElementsByTagName("SF").item(0).getTextContent()));
                percpModelElement.setMaxIterations(Integer.parseInt(eElement.getElementsByTagName("ITR").item(0).getTextContent()));
                percpModelElement.setMaxNeighborhoodRadius(Double.parseDouble(eElement.getElementsByTagName("NR").item(0).getTextContent()));
                percpModelElement.setStartLearningRate(Double.parseDouble(eElement.getElementsByTagName("LR").item(0).getTextContent()));
                percpModelElement.setHitThreshold(Integer.parseInt(eElement.getElementsByTagName("HT").item(0).getTextContent()));
                // Add Elements to ArrayList
                percpModelElements.add(percpModelElement);
            }
        }
        return percpModelElements;

    }

    /**
     * Creates a Tree Structure from Configuration XML
     *
     * @param configModelXml Configuration Model XML file Path
     * @param elementList ArrayList of PercpModelElements created from IKASL
     * Parameters
     * @return PercpConfigModel PercpConfigModel object with Tree Structure
     */
    public PercpConfigModel createConfigModel(String configModelXml, ArrayList<PercpModelElement> elementList) {

        PercpConfigModel percpConfigModel = new PercpConfigModel();
        ArrayList<PercpModelElement> percpModelElementsList = elementList; // Create a Root Element
        PercpModelElement rootElement = new PercpModelElement("L-1F-1");
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
                PercpModelElement percpModelElement = getElementById(eElement.getAttribute("id"), percpModelElementsList);
                percpModelElement.setParentElement(rootElement);
                NodeList featureNodes = nNode.getChildNodes();

                for (int i = 0; i < featureNodes.getLength(); i++) {
                    Node featureNode = featureNodes.item(i); // feature nodes
                    if (featureNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element featureElement = (Element) featureNode;
                        PercpModelElement percpModelFeatureElement = getElementById(featureElement.getAttribute("id"), percpModelElementsList);
                        percpModelFeatureElement.setParentElement(percpModelElement);
                        NodeList dimensionNodes = featureNode.getChildNodes();

                        for (int j = 0; j < dimensionNodes.getLength(); j++) {
                            Node dimensionNode = dimensionNodes.item(j);
                            if (dimensionNode.getNodeType() == Node.ELEMENT_NODE) {
                                Element dimElement = (Element) dimensionNode;
                                PercpModelElement percpModelDimElement = getElementById(dimElement.getAttribute("id"), percpModelElementsList);
                                percpModelDimElement.setParentElement(percpModelFeatureElement);
                            }
                        }
                    }
                }
            }

        }
        percpConfigModel.setPercpHeirarchy(percpModelElementsList);
        return percpConfigModel;
    }

    /**
     * Selects which PercpModelElements are used in higher level
     *
     * @param importantConfigXml Path to Important Configuration XML
     * @param model PercpConfigModel object with IKASL Parameters & Tree set
     * @return PercpConfigModel with isSelected set
     */
    public PercpConfigModel importantCongigXml(String importantConfigXml, PercpConfigModel model) {

        PercpConfigModel percpConfigModel = model;
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
                PercpModelElement percpModelElement = getElementById(eElement.getAttribute("id"), percpConfigModel.getPercpHeirarchy());
                int isSelected = Integer.parseInt(eElement.getFirstChild().getNodeValue());
                if (isSelected == 1) {
                    percpModelElement.setIsSelected(true);
                } else if (isSelected == 0) {
                    percpModelElement.setIsSelected(false);
                }
            }
        }

        return percpConfigModel;
    }

    /**
     * Creates the LinkConfigModel with all Cross and TemporalLinks
     *
     * @param linkConfigXml Link Configuration XML Path
     * @return LinkConfigModel with Cross and Temporal Links set
     */
    public LinkConfigModel getLinksFromXml(String linkConfigXml) {

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
    public PercpModelElement getElementById(String ikaslId, ArrayList<PercpModelElement> percpElementList) {

        PercpModelElement element = null;
        for (PercpModelElement percpModelElement : percpElementList) {
            if (percpModelElement.getStackId().equals(ikaslId)) {
                element = percpModelElement;
                break;
            }
        }
        return element;
    }
}
