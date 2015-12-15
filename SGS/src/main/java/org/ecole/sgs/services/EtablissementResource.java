/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ecole.sgs.services;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.ecole.sgs.dao.EtablissementDAO;
import org.ecole.sgs.dao.exceptions.NonexistentEntityException;
import org.ecole.sgs.dao.exceptions.PreexistingEntityException;
import org.ecole.sgs.entities.Etablissement;

/**
 *
 * @author armel
 */
public class EtablissementResource implements Resource<Etablissement, String> {
    
    private final EtablissementDAO dao = EtablissementDAO.builder();

    @Override
    public void add(Etablissement entity) {
        try {
            dao.create(entity);
        } catch (PreexistingEntityException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(EtablissementResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(EtablissementResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Etablissement entity) {
        try {
            dao.update(entity);
        } catch (NonexistentEntityException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(EtablissementResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(EtablissementResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Etablissement get(String id) {
        Etablissement etablissement = dao.findById(id);
        if(etablissement == null){
            // Dialog Box
            System.out.println("Désolé cette etablissement n'existe pas dans le système");
        }
        return etablissement;
    }

    @Override
    public List<Etablissement> getAll() {
        List<Etablissement> etablissements = dao.findAll();
        if(etablissements.isEmpty()){
            // Dialog Box
            System.out.println("Aucune etablissement n'a encore été enregistrée dans le système");
        }
            
        return etablissements;
    }

    @Override
    public List<Etablissement> getAll(int maxResut) {
        List<Etablissement> etablissements = dao.findAll(maxResut);
        if(etablissements.isEmpty()){
            // Dialog Box
            System.out.println("Aucune etablissement n'a encore été enregistrée dans le système");
        }
            
        return etablissements;
    }

    @Override
    public List<Etablissement> getAll(int firstResult, int maxResult) {
        List<Etablissement> etablissements = dao.findAll(firstResult, maxResult);
        if(etablissements.isEmpty()){
            // Dialog Box
            System.out.println("Aucune etablissement n'a encore été enregistrée dans le système");
        }
            
        return etablissements;
    }

    @Override
    public void remove(String id) {
        try {
            dao.delete(id);
        } catch (NonexistentEntityException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(EtablissementResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void remove(Etablissement entity) {
        try {
            dao.delete(entity);
        } catch (NonexistentEntityException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(EtablissementResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static EtablissementResource builder(){
        return new EtablissementResource();
    }
}
