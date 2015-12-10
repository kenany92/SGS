/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ecole.sgs.services;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.ecole.sgs.dao.EleveDAO;
import org.ecole.sgs.dao.exceptions.NonexistentEntityException;
import org.ecole.sgs.dao.exceptions.PreexistingEntityException;
import org.ecole.sgs.entities.Eleve;

/**
 *
 * @author armel
 */
public class EleveResource implements Resource<Eleve, String> {

    private final EleveDAO dao = EleveDAO.builder();

    @Override
    public void add(Eleve entity) {
        try {
            dao.create(entity);
        } catch (PreexistingEntityException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(EleveResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(EleveResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Eleve entity) {
        try {
            dao.update(entity);
        } catch (NonexistentEntityException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(EleveResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(EleveResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Eleve get(String id) {
        Eleve eleve = dao.findById(id);
        if(eleve == null){
            // Dialog Box
            System.out.println("Désolé cette eleve n'existe pas dans le système");
        }
        return eleve;
    }

    @Override
    public List<Eleve> getAll() {
        List<Eleve> eleves = dao.findAll();
        if(eleves.isEmpty()){
            // Dialog Box
            System.out.println("Aucune eleve n'a encore été enregistrée dans le système");
        }
            
        return eleves;
    }

    @Override
    public List<Eleve> getAll(int maxResut) {
        List<Eleve> eleves = dao.findAll(maxResut);
        if(eleves.isEmpty()){
            // Dialog Box
            System.out.println("Aucune eleve n'a encore été enregistrée dans le système");
        }
            
        return eleves;
    }

    @Override
    public List<Eleve> getAll(int firstResult, int maxResult) {
        List<Eleve> eleves = dao.findAll(firstResult, maxResult);
        if(eleves.isEmpty()){
            // Dialog Box
            System.out.println("Aucune eleve n'a encore été enregistrée dans le système");
        }
            
        return eleves;
    }

    @Override
    public void remove(String id) {
        try {
            dao.delete(id);
        } catch (NonexistentEntityException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(EleveResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void remove(Eleve entity) {
        try {
            dao.delete(entity);
        } catch (NonexistentEntityException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(EleveResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static EleveResource builder(){
        return new EleveResource();
    }
    
}

