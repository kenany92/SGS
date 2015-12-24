/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ecole.sgs.services;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.ecole.sgs.dao.UserDAO;
import org.ecole.sgs.dao.exceptions.NonexistentEntityException;
import org.ecole.sgs.dao.exceptions.PreexistingEntityException;
import org.ecole.sgs.entities.User;

/**
 *
 * @author armel
 */
public class UserResource implements Resource<User, String> {
    
    private final UserDAO dao = UserDAO.builder();

    @Override
    public void add(User entity) {
        try {
            dao.create(entity);
        } catch (PreexistingEntityException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(UserResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(UserResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(User entity) {
        try {
            dao.update(entity);
        } catch (NonexistentEntityException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(UserResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(UserResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public User get(String id) {
        User utilisateur = dao.findById(id);
        if(utilisateur == null){
            // Dialog Box
            System.out.println("Désolé cette utilisateur n'existe pas dans le système");
        }
        return utilisateur;
    }

    @Override
    public List<User> getAll() {
        List<User> utilisateurs = dao.findAll();
        if(utilisateurs.isEmpty()){
            // Dialog Box
            System.out.println("Aucune utilisateur n'a encore été enregistrée dans le système");
        }
            
        return utilisateurs;
    }

    @Override
    public List<User> getAll(int maxResut) {
        List<User> utilisateurs = dao.findAll(maxResut);
        if(utilisateurs.isEmpty()){
            // Dialog Box
            System.out.println("Aucune utilisateur n'a encore été enregistrée dans le système");
        }
            
        return utilisateurs;
    }

    @Override
    public List<User> getAll(int firstResult, int maxResult) {
        List<User> utilisateurs = dao.findAll(firstResult, maxResult);
        if(utilisateurs.isEmpty()){
            // Dialog Box
            System.out.println("Aucune utilisateur n'a encore été enregistrée dans le système");
        }
            
        return utilisateurs;
    }

    @Override
    public void remove(String id) {
        try {
            dao.delete(id);
        } catch (NonexistentEntityException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(UserResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void remove(User entity) {
        try {
            dao.delete(entity);
        } catch (NonexistentEntityException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(UserResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static UserResource builder(){
        return new UserResource();
    }
}
