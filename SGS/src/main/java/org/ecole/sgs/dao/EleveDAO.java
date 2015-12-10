/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ecole.sgs.dao;

import java.io.Serializable;
import org.ecole.sgs.dao.exceptions.NonexistentEntityException;
import org.ecole.sgs.dao.exceptions.PreexistingEntityException;
import org.ecole.sgs.entities.Eleve;

/**
 *
 * @author armel
 */
public class EleveDAO extends AbstractDAO<Eleve, String>implements Serializable {
    private static final long serialVersionUID = 1L;

    @Override
    public void create(Eleve eleve) throws PreexistingEntityException, Exception {
        try {
            super.create(eleve);
        } catch (Exception ex) {
            if (findById(eleve.getMatricule()) != null) {
                throw new PreexistingEntityException("Désolé cette élève existe déjà", ex);
            }
            throw ex;
        } finally {
            if (getSession() != null) {
                getSession().close();
            }
        }
    }

    @Override
    public void update(Eleve eleve) throws NonexistentEntityException, Exception {
        try {
            super.update(eleve);
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String matricule = eleve.getMatricule();
                if (findById(matricule) == null) {
                    throw new NonexistentEntityException("Désolé cette élève n'existe pas");
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
     public void update(String matricule) throws NonexistentEntityException, Exception {
        try {
            super.update(matricule);
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                if (findById(matricule) == null) {
                    throw new NonexistentEntityException("Désolé la eleve ayant cet identifient n'existe pas");
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
    public void delete(String matricule) throws NonexistentEntityException {
        try {
            super.delete(matricule);
            
            } catch (NonexistentEntityException ex) {
                if(findById(matricule) == null){
                    throw new NonexistentEntityException("Désolé cette eleve n'existe pas: impossible de la supprimer");
                }
                throw ex;
        } finally {
             if (getSession() != null) {
                getSession().close();
            }
        }
    }
    
    public static EleveDAO builder(){
        return new EleveDAO();
    }
}
