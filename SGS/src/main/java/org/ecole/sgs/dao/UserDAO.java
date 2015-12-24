/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ecole.sgs.dao;

import java.io.Serializable;
import org.ecole.sgs.dao.exceptions.NonexistentEntityException;
import org.ecole.sgs.dao.exceptions.PreexistingEntityException;
import org.ecole.sgs.entities.User;

/**
 *
 * @author armel
 */
public class UserDAO extends AbstractDAO<User, String>implements Serializable {
    private static final long serialVersionUID = 1L;

    @Override
    public void create(User user) throws PreexistingEntityException, Exception {
        try {
            super.create(user);
        } catch (Exception ex) {
            if (findById(user.getLogin()) != null) {
                throw new PreexistingEntityException("Désolé cette utilisateur existe déjà", ex);
            }
            throw ex;
        } finally {
            if (getSession().isOpen()) {
                getSession().close();
            }
        }
    }

    @Override
    public void update(User user) throws NonexistentEntityException, Exception {
        try {
            super.update(user);
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String login = user.getLogin();
                if (findById(login) == null) {
                    throw new NonexistentEntityException("Désolé cette utilisateur n'existe pas");
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
     public void update(String login) throws NonexistentEntityException, Exception {
        try {
            super.update(login);
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                if (findById(login) == null) {
                    throw new NonexistentEntityException("Désolé l'utilisateur ayant cet identifient n'existe pas");
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
    public void delete(String login) throws NonexistentEntityException {
        try {
            super.delete(login);
            
            } catch (NonexistentEntityException ex) {
                if(findById(login) == null){
                    throw new NonexistentEntityException("Désolé cet utilisateur n'existe pas: impossible de la supprimer");
                }
                throw ex;
        } finally {
             if (getSession() != null) {
                getSession().close();
            }
        }
    }
    
    public static UserDAO builder(){
        return new UserDAO();
    }
}
