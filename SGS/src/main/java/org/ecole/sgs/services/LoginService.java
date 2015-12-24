/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ecole.sgs.services;

import org.ecole.sgs.entities.User;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author armel
 */
public class LoginService {
    
    private final UserResource userRs = UserResource.builder();
    
    public static LoginService builder(){
        return new LoginService();
    }
    
    @SuppressWarnings("empty-statement")
    public void login(String login, String password){
        User user = userRs.get(login);
        
        if(user == null){
            System.out.println("Cet utilisateur n'existe pas");
        }else{
            String upw = user.getPassword();
            if(BCrypt.checkpw(password, upw)){
                System.out.println("Ok Connexion!");
            }else{
                System.out.println("Erreur de mot de passe!");
            }
        }
    }
}
