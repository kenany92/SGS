/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ecole.sgs.util.enums;

/**
 *
 * @author armel
 */
public enum ClasseNameEN {
    
    CLASS_I ("CLASS I"),
    CLASS_II ("CLASS II"),
    CLASS_III ("CLASS III"),
    CLASS_IV ("CLASS IV"),
    CLASS_V ("CLASS V"),
    CLASS_VI ("CLASS VI");
    
    private String name = "";

    private ClasseNameEN(String name) {
        this.name = name;
    }
    
    @Override
    public String toString(){
        return name;
    }
    
    
}
