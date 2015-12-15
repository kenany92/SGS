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
public enum Section {
    
    FRANCOPHONE ("Francophone"),
    ANGLOPHONE ("Anglophone");
    
    private String section = "";
    
    private Section(String section){
        this.section = section;
    }
    
    @Override
    public String toString(){
        return section;
    }
}
