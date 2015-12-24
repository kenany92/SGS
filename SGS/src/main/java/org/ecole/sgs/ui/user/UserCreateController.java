/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ecole.sgs.ui.user;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.ecole.sgs.entities.User;
import org.ecole.sgs.services.UserResource;
import org.ecole.sgs.util.functions.Function;
import org.mindrot.jbcrypt.BCrypt;

/**
 * FXML Controller class
 *
 * @author armel
 */
public class UserCreateController implements Initializable {
    
    @FXML
    private TextField login_f;
    
    @FXML
    private PasswordField psw_f;
    
    @FXML
    private Label droit;
    
    private static final String LOGIN_ERROR_MSG = "Le login doit avoir au moins 5 caractères";
    
    private static final String PASSWORD_ERROR_MSG = "Le mot de passe doit avoir au moins 8 caractères";
    
    private final UserResource userRs = UserResource.builder();
    
    private static final String salt = BCrypt.gensalt(12);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void submitHandle(ActionEvent event){
        String log = login_f.getText();
        String ps =  BCrypt.hashpw(psw_f.getText(),salt);
        if(validate(log, ps, droit)){
            User u = new User();
            u.setLogin(log);
            u.setPassword(ps);
            userRs.add(u);
        }
    }
    
    private boolean validate(String log, String ps, Label msg){
        
        return validLogin(log, msg)&&validPassword(ps, msg);
    }
    
    private boolean validPassword(String ps, Label msg){
        if(!Function.stringValidation(ps, 8)){
            msg.setText(PASSWORD_ERROR_MSG);
            return false;
        }
        return true;
    }
    
    private boolean validLogin(String log, Label msg){
        if(!Function.stringValidation(log, 5)){
            msg.setText(LOGIN_ERROR_MSG);
            return false;
        }
        return true;
    }
    
}
