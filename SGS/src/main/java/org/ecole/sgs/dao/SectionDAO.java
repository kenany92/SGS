/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ecole.sgs.dao;

import java.io.Serializable;
import org.ecole.sgs.dao.exceptions.NonexistentEntityException;
import org.ecole.sgs.dao.exceptions.PreexistingEntityException;
import org.ecole.sgs.entities.Section;

/**
 *
 * @author armel
 */
public class SectionDAO extends AbstractDAO<Section, String>implements Serializable {
    
     private static final long serialVersionUID = 1L;

    @Override
    public void create(Section section) throws PreexistingEntityException, Exception {
        if (findById(section.getId()) != null) {
                throw new PreexistingEntityException("Désolé cette section existe déjà");
            }else{
            try {
            super.create(section);
        } catch (Exception ex) {

        }
        }
    }

    @Override
    public void update(Section section) throws NonexistentEntityException, Exception {
        try {
            super.update(section);
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = section.getId();
                if (findById(id) == null) {
                    throw new NonexistentEntityException("Désolé cette section n'existe pas");
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
     public void update(String id) throws NonexistentEntityException, Exception {
        try {
            super.update(id);
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                if (findById(id) == null) {
                    throw new NonexistentEntityException("Désolé cette section n'existe pas");
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
    public void delete(String id) throws NonexistentEntityException {
        try {
            super.delete(id);
            
            } catch (NonexistentEntityException ex) {
                if(findById(id) == null){
                    throw new NonexistentEntityException("Désolé cette section n'existe pas: impossible de la supprimer");
                }
                throw ex;
        } finally {
             if (getSession() != null) {
                getSession().close();
            }
        }
    }
    
    public static SectionDAO builder(){
        return new SectionDAO();
    }
    
}

