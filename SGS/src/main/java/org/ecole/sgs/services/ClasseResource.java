/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ecole.sgs.services;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.ecole.sgs.dao.ClasseDAO;
import org.ecole.sgs.dao.exceptions.NonexistentEntityException;
import org.ecole.sgs.dao.exceptions.PreexistingEntityException;
import org.ecole.sgs.entities.Classe;

/**
 *
 * @author armel
 */
public class ClasseResource implements Resource<Classe, String> {
    
    private final ClasseDAO dao = ClasseDAO.builder();

    @Override
    public void add(Classe entity) {
        try {
            dao.create(entity);
        } catch (PreexistingEntityException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(ClasseResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ClasseResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Classe entity) {
        try {
            dao.update(entity);
        } catch (NonexistentEntityException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(ClasseResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ClasseResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Classe get(String id) {
        Classe ecole = dao.findById(id);
        if(ecole == null){
            // Dialog Box
            System.out.println("Désolé cette classe n'existe pas dans le système");
        }
        return ecole;
    }

    @Override
    public List<Classe> getAll() {
        List<Classe> ecoles = dao.findAll();
        if(ecoles.isEmpty()){
            // Dialog Box
            System.out.println("Aucune classe n'a encore été enregistrée dans le système");
        }
            
        return ecoles;
    }

    @Override
    public List<Classe> getAll(int maxResut) {
        List<Classe> ecoles = dao.findAll(maxResut);
        if(ecoles.isEmpty()){
            // Dialog Box
            System.out.println("Aucune classe n'a encore été enregistrée dans le système");
        }
            
        return ecoles;
    }

    @Override
    public List<Classe> getAll(int firstResult, int maxResult) {
        List<Classe> ecoles = dao.findAll(firstResult, maxResult);
        if(ecoles.isEmpty()){
            // Dialog Box
            System.out.println("Aucune classe n'a encore été enregistrée dans le système");
        }
            
        return ecoles;
    }

    @Override
    public void remove(String id) {
        try {
            dao.delete(id);
        } catch (NonexistentEntityException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(ClasseResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void remove(Classe entity) {
        try {
            dao.delete(entity);
        } catch (NonexistentEntityException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(ClasseResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Classe> getBySection(String sectionId){
        List<Classe> classes = dao.findByCriteria("section", sectionId);
        
        return classes;
    }
    
    public List<String> getClassNames(){
        List<String> class_names = dao.findProperty("name");
        return class_names;
    }
    
    public static ClasseResource builder(){
        return new ClasseResource();
    }
}

