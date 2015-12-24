/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ecole.sgs.util.interceptor;

import java.io.Serializable;
import java.lang.reflect.Type;
import org.ecole.sgs.entities.User;
import org.hibernate.EmptyInterceptor;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author armel
 */
public class UserInterceptor extends EmptyInterceptor{
    private static final long serialVersionUID = 1L;
    
    private int updates;
    private int creates;
    private int loads;
    private static final String salt = BCrypt.gensalt(12);
    
    public boolean onSave(Object entity,
                          Serializable id,
                          Object[] state,
                          String[] propertyNames,
                          Type[] types) {

        if ( entity instanceof User ) {
            creates++;
            for ( int i=0; i<propertyNames.length; i++ ) {
                if ( "password".equals( propertyNames[i] ) ) {
                    state[i] = BCrypt.hashpw(state[i].toString(), salt);
                    return true;
                }
            }
        }
        return false;
    }
}
