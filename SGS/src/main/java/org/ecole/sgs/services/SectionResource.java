/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ecole.sgs.services;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.ecole.sgs.dao.SectionDAO;
import org.ecole.sgs.dao.exceptions.NonexistentEntityException;
import org.ecole.sgs.dao.exceptions.PreexistingEntityException;
import org.ecole.sgs.entities.Section;

/**
 *
 * @author armel
 */
public class SectionResource implements Resource<Section, String> {
    
    private final SectionDAO dao = SectionDAO.builder();

    @Override
    public void add(Section entity) {
        try {
            dao.create(entity);
        } catch (PreexistingEntityException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(SectionResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(SectionResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Section entity) {
        try {
            dao.update(entity);
        } catch (NonexistentEntityException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(SectionResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(SectionResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Section get(String id) {
        Section ecole = dao.findById(id);
        if(ecole == null){
            // Dialog Box
            System.out.println("Désolé cette section n'existe pas dans le système");
        }
        return ecole;
    }

    @Override
    public List<Section> getAll() {
        List<Section> ecoles = dao.findAll();
        if(ecoles.isEmpty()){
            // Dialog Box
            System.out.println("Aucune section n'a encore été enregistrée dans le système");
        }
            
        return ecoles;
    }

    @Override
    public List<Section> getAll(int maxResut) {
        List<Section> ecoles = dao.findAll(maxResut);
        if(ecoles.isEmpty()){
            // Dialog Box
            System.out.println("Aucune section n'a encore été enregistrée dans le système");
        }
            
        return ecoles;
    }

    @Override
    public List<Section> getAll(int firstResult, int maxResult) {
        List<Section> ecoles = dao.findAll(firstResult, maxResult);
        if(ecoles.isEmpty()){
            // Dialog Box
            System.out.println("Aucune section n'a encore été enregistrée dans le système");
        }
            
        return ecoles;
    }

    @Override
    public void remove(String id) {
        try {
            dao.delete(id);
        } catch (NonexistentEntityException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(SectionResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void remove(Section entity) {
        try {
            dao.delete(entity);
        } catch (NonexistentEntityException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(SectionResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Section> getEnabled(boolean isEnable){
        List<Section> secs = dao.findByCriteria("enable", isEnable);
        
        return secs;
    }
    
    public List<String> getSectionNames(){
        List<String> sec_n = dao.findProperty("name");
        return sec_n;
    }
    
    public List<Section> getByName(String name){
        return dao.findByCriteria("name", name);
    }
    
    public static SectionResource builder(){
        return new SectionResource();
    }
}
