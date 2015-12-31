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
import org.ecole.sgs.util.enums.SectionName;

/**
 *
 * @author armel
 */
@Entity
public class Ecole extends Etablissement implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SectionName firstSection;
    
    @Enumerated(EnumType.STRING)
    private SectionName secondSection;

    public SectionName getFirstSection() {
        return firstSection;
    }

    public void setFirstSection(SectionName firstSection) {
        this.firstSection = firstSection;
    }

    public SectionName getSecondSection() {
        return secondSection;
    }

    public void setSecondSection(SectionName secondSection) {
        this.secondSection = secondSection;
    }

    @Override
    public String toString() {
        return this.getSigle()+" : "+this.getNom();
    }
    
}
