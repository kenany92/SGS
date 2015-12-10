/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ecole.sgs.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.ecole.sgs.dao.exceptions.NonexistentEntityException;
import org.ecole.sgs.dao.exceptions.PreexistingEntityException;

/**
 *
 * @author armel
 * @param <T> Entity class
 * @param <ID> Type of id of entity
 */
public interface GenericDAO<T, ID extends Serializable> {
    
    T findById(ID id);
    
    void create(T entity) throws PreexistingEntityException, Exception;
    
    void update(T entity) throws NonexistentEntityException, Exception;
    
    void update(ID id) throws NonexistentEntityException, Exception;
    
    void delete(T entity) throws NonexistentEntityException ;
    
    void delete(ID id) throws NonexistentEntityException ;
    
    List<T> findAll();
    
    List<T> findByCriteria(String property, Object propertyValue);
    
    List<T> findByMultiCriterias(Map propertiesNamesValues);
    
    int count();

    public List<T> findAll(int maxResut);
    
}
