/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ecole.sgs;

import com.sun.javafx.collections.ObservableListWrapper;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.ecole.sgs.ui.ecole.EcoleCreate;
import org.ecole.sgs.util.enums.EtablissementType;

/**
 * FXML Controller class
 *
 * @author armel
 */
public class EtabTypeController implements Initializable {
    
    @FXML
    private ComboBox<String> type;
    
    @FXML
    private Button next;
    
    @FXML
    private Label notitem;
    
    private List<String> typelist = new ArrayList<>();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        typelist.add(EtablissementType.PRIMAIRE.toString());
        ObservableList<String> list = new ObservableListWrapper<>(typelist);
        
        type.setItems(list);
        notitem.setVisible(false);
    }
    
    @FXML
    private void nextButtonHandle(ActionEvent event) throws IOException{
        if(type.getValue() == null){
            notitem.setVisible(true);
        }else{
            switch(type.getValue()){
                
                case "PRIMAIRE":
                    EcoleCreate ecc = new EcoleCreate();
                    Stage st = new Stage();
                    ecc.start(st);
                    break;
                    
                case "SECONDAIRE":
                    
                    break;
                    
                default:
                    break;
            }
            ((Node)event.getSource()).getScene().getWindow().hide();
        }
    }
    
    
}
