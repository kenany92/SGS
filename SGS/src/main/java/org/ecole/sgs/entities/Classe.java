/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ecole.sgs.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 *
 * @author armel
 */
@Entity
public class Classe implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    private String name;
    
    @Column(nullable = false)
    private double inscriptionAmount;
    
    @Column(nullable = false)
    private double firstAmount;
    
    @Column(nullable = false)
    private double secondAmount;
    
    @Column(nullable = false)
    private double thirdAmount;
        
    private double fileAmount;
    
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date inscrptionLimit;
    
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date firstLimit;
    
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date secondLimit;
    
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date thirdLimit;
    
    @Temporal(TemporalType.DATE)
    private Date fileLimit;
    
    @JoinColumn(referencedColumnName = "name", nullable = false, name = "section")
    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Section section;
    
    @Version
    private int version;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getInscriptionAmount() {
        return inscriptionAmount;
    }

    public void setInscriptionAmount(double inscriptionAmount) {
        this.inscriptionAmount = inscriptionAmount;
    }

    public Date getInscrptionLimit() {
        return inscrptionLimit;
    }

    public void setInscrptionLimit(Date inscrptionLimit) {
        this.inscrptionLimit = inscrptionLimit;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public double getFirstAmount() {
        return firstAmount;
    }

    public void setFirstAmount(double firstAmount) {
        this.firstAmount = firstAmount;
    }

    public double getSecondAmount() {
        return secondAmount;
    }

    public void setSecondAmount(double secondAmount) {
        this.secondAmount = secondAmount;
    }

    public double getThirdAmount() {
        return thirdAmount;
    }

    public void setThirdAmount(double thirdAmount) {
        this.thirdAmount = thirdAmount;
    }

    public double getFileAmount() {
        return fileAmount;
    }

    public void setFileAmount(double fileAmount) {
        this.fileAmount = fileAmount;
    }

    public Date getFirstLimit() {
        return firstLimit;
    }

    public void setFirstLimit(Date firstLimit) {
        this.firstLimit = firstLimit;
    }

    public Date getSecondLimit() {
        return secondLimit;
    }

    public void setSecondLimit(Date secondLimit) {
        this.secondLimit = secondLimit;
    }

    public Date getThirdLimit() {
        return thirdLimit;
    }

    public void setThirdLimit(Date thirdLimit) {
        this.thirdLimit = thirdLimit;
    }

    public Date getFileLimit() {
        return fileLimit;
    }

    public void setFileLimit(Date fileLimit) {
        this.fileLimit = fileLimit;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (name != null ? name.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Classe)) {
            return false;
        }
        Classe other = (Classe) object;
        if ((this.name == null && other.name != null) || (this.name != null && !this.name.equals(other.name))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return name;
    }
    
}
