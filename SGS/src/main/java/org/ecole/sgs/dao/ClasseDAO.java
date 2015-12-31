/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ecole.sgs.dao;

import java.io.Serializable;
import java.util.List;
import org.ecole.sgs.dao.exceptions.NonexistentEntityException;
import org.ecole.sgs.dao.exceptions.PreexistingEntityException;
import org.ecole.sgs.entities.Classe;

/**
 *
 * @author armel
 */
public class ClasseDAO extends AbstractDAO<Classe, String>implements Serializable {
    
     private static final long serialVersionUID = 1L;

    @Override
    public void create(Classe classe) throws PreexistingEntityException, Exception {
        if (findById(classe.getName()) != null) {
                throw new PreexistingEntityException("Désolé cette classe existe déjà");
            }else{
            try {
            super.create(classe);
        } catch (Exception ex) {

        }
        }
    }

    @Override
    public void update(Classe classe) throws NonexistentEntityException, Exception {
        try {
            super.update(classe);
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String name = classe.getName();
                if (findById(name) == null) {
                    throw new NonexistentEntityException("Désolé cette classe n'existe pas");
                }
            }
            throw ex;
        } finally {
            if (getSession() != null) {
                getSession().close();
            }
        }
    }
    
    @Override
     public void update(String name) throws NonexistentEntityException, Exception {
        try {
            super.update(name);
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                if (findById(name) == null) {
                    throw new NonexistentEntityException("Désolé cette classe n'existe pas");
                }
            }
            throw ex;
        } finally {
            if (getSession() != null) {
                getSession().close();
            }
        }
    }

    @Override
    public void delete(String name) throws NonexistentEntityException {
        try {
            super.delete(name);
            
            } catch (NonexistentEntityException ex) {
                if(findById(name) == null){
                    throw new NonexistentEntityException("Désolé cette classe n'existe pas: impossible de la supprimer");
                }
                throw ex;
        } finally {
             if (getSession() != null) {
                getSession().close();
            }
        }
    }
    
    public static ClasseDAO builder(){
        return new ClasseDAO();
    }
    
}

