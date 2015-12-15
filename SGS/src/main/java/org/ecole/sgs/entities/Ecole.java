/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ecole.sgs.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import org.ecole.sgs.util.enums.Section;

/**
 *
 * @author armel
 */
@Entity
public class Ecole extends Etablissement implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Section firstSection;
    
    @Enumerated(EnumType.STRING)
    private Section secondSection;

    public Section getFirstSection() {
        return firstSection;
    }

    public void setFirstSection(Section firstSection) {
        this.firstSection = firstSection;
    }

    public Section getSecondSection() {
        return secondSection;
    }

    public void setSecondSection(Section secondSection) {
        this.secondSection = secondSection;
    }

    @Override
    public String toString() {
        return this.getSigle()+" : "+this.getNom();
    }
    
}
