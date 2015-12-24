/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ecole.sgs.ui.ecole;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import org.ecole.sgs.entities.Ecole;
import org.ecole.sgs.services.EcoleResource;
import org.ecole.sgs.util.enums.Section;
import org.ecole.sgs.util.functions.Function;

/**
 * FXML Controller class
 *
 * @author armel
 */
public class EcoleCreateController implements Initializable {
    
    @FXML
    private TextField sigle_f;
    
    @FXML
    private TextField nom_f;
    
    @FXML
    private CheckBox fr;
    
    @FXML
    private CheckBox en;
    
    @FXML
    private DatePicker asb_f;
    
    @FXML
    private DatePicker ase_f;
    
    @FXML
    private Button logo_f;
    
    @FXML
    private ImageView logoviewer_f;
    
    @FXML
    private Button submit;
    
    private final EcoleResource ecoleRs = EcoleResource.builder();
    
    private Ecole ecole;
    
    private final Function fn = new Function();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    private void submitHandle(ActionEvent event) throws IOException{
        
        if(checkFormValidation()){
            perform(event);
        }
        
        
    }
    
    @FXML
    private void prevHandle(ActionEvent event) throws IOException{
        fn.openEtabType("/fxml/etab_type.fxml", "Type etablissement");
        ((Node)event.getSource()).getScene().getWindow().hide();
    }
    
    private boolean checkFormValidation(){
        
        return Function.stringValidation(sigle_f.getText(), 2) && 
               Function.defaultStringValidation(nom_f.getText()) && 
               (fr.isSelected() || en.isSelected()) &&
               asb_f != null && ase_f != null;
    }
    
    private void perform(ActionEvent event) throws IOException{
        LocalDate local1 = asb_f.getValue();
        Instant inst1 = Instant.from(local1.atStartOfDay(ZoneId.systemDefault()));
        Date date1 = Date.from(inst1);
        
        LocalDate local2 = ase_f.getValue();
        Instant inst2 = Instant.from(local2.atStartOfDay(ZoneId.systemDefault()));
        Date date2 = Date.from(inst2);
        
        String as = Function.dateToAs(date1, date2);
        ecole = new Ecole();
        ecole.setSigle(sigle_f.getText());
        ecole.setNom(nom_f.getText());
        ecole.setAnneeScolaire(as);
        
        if(fr.isSelected()){
            ecole.setFirstSection(Section.FRANCOPHONE);
            
            if(en.isSelected()){
                ecole.setSecondSection(Section.ANGLOPHONE);
            }
        }
        else{
            ecole.setFirstSection(Section.ANGLOPHONE);
        }
        
        ecoleRs.add(ecole);
        
        Ecole verf = ecoleRs.get(sigle_f.getText());
        
        if(verf != null){
            System.out.println("L' école "+verf.getNom()+" a été créée avec succès");
            fn.openEtabType("/fxml/user_create.fxml", "Type etablissement");
            ((Node)event.getSource()).getScene().getWindow().hide();
        }else{
            System.out.println("Erreur lors de la création de l'école");
        }
        
    }
    
}
