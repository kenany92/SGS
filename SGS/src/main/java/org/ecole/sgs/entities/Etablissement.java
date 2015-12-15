/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ecole.sgs.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author armel
 */
@MappedSuperclass
public abstract class Etablissement implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    private String sigle;
    
    @Column(nullable = false, unique = true)
    private String nom;
    
    @Lob
    private byte[] logo;
    
    @Column(nullable = false)
    private String anneeScolaire;  //Année Scolaire

    public String getSigle() {
        return sigle;
    }

    public void setSigle(String sigle) {
        this.sigle = sigle;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public String getAnneeScolaire() {
        return anneeScolaire;
    }

    public void setAnneeScolaire(String anneeScolaire) {
        this.anneeScolaire = anneeScolaire;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sigle != null ? sigle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Etablissement)) {
            return false;
        }
        
        Etablissement other = (Etablissement) object;
        
        return  this.sigle != null &&
                other.getSigle() != null &&
                this.nom != null &&
                other.getNom() != null &&
                this.sigle.equals(other.getSigle()) && 
                this.nom.equals(other.getNom());
    }

    @Override
    public String toString() {
        return "org.ecole.sgs.entities.Etablissement[ id=" + sigle + " ]";
    }
    
}
