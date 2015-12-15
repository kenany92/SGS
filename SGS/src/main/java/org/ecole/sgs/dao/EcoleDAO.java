/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ecole.sgs.dao;

import java.io.Serializable;
import org.ecole.sgs.dao.exceptions.NonexistentEntityException;
import org.ecole.sgs.dao.exceptions.PreexistingEntityException;
import org.ecole.sgs.entities.Ecole;

/**
 *
 * @author armel
 */
public class EcoleDAO extends AbstractDAO<Ecole, String>implements Serializable {
    
     private static final long serialVersionUID = 1L;

    @Override
    public void create(Ecole ecole) throws PreexistingEntityException, Exception {
        if (findById(ecole.getSigle()) != null) {
                throw new PreexistingEntityException("Désolé cette école existe déjà");
            }else{
            try {
            super.create(ecole);
        } catch (Exception ex) {

        }
        }
    }

    @Override
    public void update(Ecole ecole) throws NonexistentEntityException, Exception {
        try {
            super.update(ecole);
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String sigle = ecole.getSigle();
                if (findById(sigle) == null) {
                    throw new NonexistentEntityException("Désolé cette école n'existe pas");
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
     public void update(String sigle) throws NonexistentEntityException, Exception {
        try {
            super.update(sigle);
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                if (findById(sigle) == null) {
                    throw new NonexistentEntityException("Désolé l'ecole ayant cet identifient n'existe pas");
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
    public void delete(String sigle) throws NonexistentEntityException {
        try {
            super.delete(sigle);
            
            } catch (NonexistentEntityException ex) {
                if(findById(sigle) == null){
                    throw new NonexistentEntityException("Désolé cette ecole n'existe pas: impossible de la supprimer");
                }
                throw ex;
        } finally {
             if (getSession() != null) {
                getSession().close();
            }
        }
    }
    
    public static EcoleDAO builder(){
        return new EcoleDAO();
    }
    
}
