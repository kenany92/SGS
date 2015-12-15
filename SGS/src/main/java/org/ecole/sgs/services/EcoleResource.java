/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ecole.sgs.services;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.ecole.sgs.dao.EcoleDAO;
import org.ecole.sgs.dao.exceptions.NonexistentEntityException;
import org.ecole.sgs.dao.exceptions.PreexistingEntityException;
import org.ecole.sgs.entities.Ecole;

/**
 *
 * @author armel
 */
public class EcoleResource implements Resource<Ecole, String> {
    
    private final EcoleDAO dao = EcoleDAO.builder();

    @Override
    public void add(Ecole entity) {
        try {
            dao.create(entity);
        } catch (PreexistingEntityException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(EcoleResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(EcoleResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Ecole entity) {
        try {
            dao.update(entity);
        } catch (NonexistentEntityException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(EcoleResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(EcoleResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Ecole get(String id) {
        Ecole ecole = dao.findById(id);
        if(ecole == null){
            // Dialog Box
            System.out.println("Désolé cette ecole n'existe pas dans le système");
        }
        return ecole;
    }

    @Override
    public List<Ecole> getAll() {
        List<Ecole> ecoles = dao.findAll();
        if(ecoles.isEmpty()){
            // Dialog Box
            System.out.println("Aucune ecole n'a encore été enregistrée dans le système");
        }
            
        return ecoles;
    }

    @Override
    public List<Ecole> getAll(int maxResut) {
        List<Ecole> ecoles = dao.findAll(maxResut);
        if(ecoles.isEmpty()){
            // Dialog Box
            System.out.println("Aucune ecole n'a encore été enregistrée dans le système");
        }
            
        return ecoles;
    }

    @Override
    public List<Ecole> getAll(int firstResult, int maxResult) {
        List<Ecole> ecoles = dao.findAll(firstResult, maxResult);
        if(ecoles.isEmpty()){
            // Dialog Box
            System.out.println("Aucune ecole n'a encore été enregistrée dans le système");
        }
            
        return ecoles;
    }

    @Override
    public void remove(String id) {
        try {
            dao.delete(id);
        } catch (NonexistentEntityException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(EcoleResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void remove(Ecole entity) {
        try {
            dao.delete(entity);
        } catch (NonexistentEntityException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(EcoleResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static EcoleResource builder(){
        return new EcoleResource();
    }
}
