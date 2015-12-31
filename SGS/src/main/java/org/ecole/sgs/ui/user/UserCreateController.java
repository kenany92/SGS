/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ecole.sgs.ui.user;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
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
    
    @FXML
    private Label status;
    
    @FXML
    private Button next;
    
    @FXML
    private Button submit;
    
    private static final String LOGIN_ERROR_MSG = "Le login doit avoir au moins 5 caractères";
    
    private static final String PASSWORD_ERROR_MSG = "Le mot de passe doit avoir au moins 8 caractères";
    
    private static final String SUBMIT_ERROR_MSG = "DÉSOLÉ, VOTRE COMPTE N'A PAS PU ÊTRE CRÉE";
    
    private static final String SUBMIT_SUCCESS_MSG = "VOTRE COMPTE A ÉTÉ CRÉE AVEC SUCCÈS";
    
    Function fn = new Function();
    
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
        if(userRs.get(log) != null){
            status.setTextFill(Color.GREEN);
            status.setText(SUBMIT_SUCCESS_MSG);
            next.setDisable(false);
            submit.setDisable(true);
        }else{
            status.setTextFill(Color.RED);
            status.setText(SUBMIT_ERROR_MSG);
        }
        
    }
    
    @FXML
    private void switchToLogin(ActionEvent event){
        try {
            fn.openEtabType("/fxml/login.fxml", "Connexion");
            ((Node)event.getSource()).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(UserCreateController.class.getName()).log(Level.SEVERE, null, ex);
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
