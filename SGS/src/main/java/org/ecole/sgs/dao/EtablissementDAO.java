/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ecole.sgs.dao;

import java.io.Serializable;
import org.ecole.sgs.dao.exceptions.NonexistentEntityException;
import org.ecole.sgs.dao.exceptions.PreexistingEntityException;
import org.ecole.sgs.entities.Etablissement;

/**
 *
 * @author armel
 */
public class EtablissementDAO extends AbstractDAO<Etablissement, String>implements Serializable {
    
     private static final long serialVersionUID = 1L;

    @Override
    public void create(Etablissement etablissement) throws PreexistingEntityException, Exception {
        try {
            super.create(etablissement);
        } catch (Exception ex) {
            if (findById(etablissement.getSigle()) != null) {
                throw new PreexistingEntityException("Désolé cette établissement existe déjà", ex);
            }
            throw ex;
        } finally {
            if (getSession() != null) {
                getSession().close();
            }
        }
    }

    @Override
    public void update(Etablissement etablissement) throws NonexistentEntityException, Exception {
        try {
            super.update(etablissement);
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String sigle = etablissement.getSigle();
                if (findById(sigle) == null) {
                    throw new NonexistentEntityException("Désolé cette établissement n'existe pas");
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
                    throw new NonexistentEntityException("Désolé l'etablissement ayant cet identifient n'existe pas");
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
                    throw new NonexistentEntityException("Désolé cette etablissement n'existe pas: impossible de la supprimer");
                }
                throw ex;
        } finally {
             if (getSession() != null) {
                getSession().close();
            }
        }
    }
    
    public static EtablissementDAO builder(){
        return new EtablissementDAO();
    }
}
