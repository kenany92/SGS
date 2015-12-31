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
import org.ecole.sgs.util.functions.Function;

/**
 * FXML Controller class
 *
 * @author armel
 */
public class RootController implements Initializable {
    
    @FXML
    private Button add_classe;
    
    private final Function fn = Function.getFunction();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void addClasseAction(ActionEvent event){
        try {
            fn.openEtabType("/fxml/classe.fxml", "Ajouter une classe");
            
//            ((Node)event.getSource()).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(RootController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
