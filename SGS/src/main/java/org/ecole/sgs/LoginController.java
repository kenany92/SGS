/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ecole.sgs;

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
public class LoginController implements Initializable {

    @FXML
    private Label banner;
    
    @FXML
    private Label login_label;
    
    @FXML
    private Label psw_label;
    
    @FXML
    private TextField login_field;
    
    @FXML
    private PasswordField psw_field;
    
    @FXML
    private Button submit;
    
    @FXML
    private Label state;
    
    UserResource userRs = UserResource.builder();
    
    Function fn = Function.getFunction();
    
    private static final String NOTEXISTUSER = "CET UTILISATEUR N'EXISTE PAS: LOGIN INCORRECT";
    
    private static final String BADPSW = "MOT DE PASSE INCORRECT";
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }   
    
    @FXML
    public void submitAction(ActionEvent event){
        String login = login_field.getText();
        String psw = psw_field.getText();
        
        User u = userRs.get(login);
        
        if(u == null){
            state.setTextFill(Color.RED);
            state.setText(NOTEXISTUSER);
        }else{
            String upsw = u.getPassword();
            if(BCrypt.checkpw(psw, upsw)){
                try {
                    fn.openEtabType("/fxml/root.fxml", "Ecole Numérique");
                    ((Node)event.getSource()).getScene().getWindow().hide();
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }else{
                state.setTextFill(Color.RED);
                state.setText(BADPSW);
            }
        }
    }
    
}
