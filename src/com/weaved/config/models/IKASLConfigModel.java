/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weaved.config.models;

import com.weaved.config.models.elememts.IKASLConfigModelElement;
import java.util.ArrayList;

/**
 *
 * @author Lasindu
 */
public class IKASLConfigModel extends ConfigModel{

    private ArrayList<IKASLConfigModelElement> iKASLConfigModelElements;

    public IKASLConfigModel() {
        iKASLConfigModelElements = new ArrayList<IKASLConfigModelElement>();

    }

    /**
     * @return the iKASLConfigModelElements
     */
    public ArrayList<IKASLConfigModelElement> getiKASLConfigModelElements() {
        return iKASLConfigModelElements;
    }

    /**
     * @param iKASLConfigModelElements the iKASLConfigModelElements to set
     */
    public void setiKASLConfigModelElements(ArrayList<IKASLConfigModelElement> iKASLConfigModelElements) {
        this.iKASLConfigModelElements = iKASLConfigModelElements;
    }
}
