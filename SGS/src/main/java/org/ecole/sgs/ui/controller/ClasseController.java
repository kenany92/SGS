/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ecole.sgs.ui.controller;

import com.sun.javafx.collections.ObservableListWrapper;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import org.ecole.sgs.entities.Classe;
import org.ecole.sgs.entities.Section;
import org.ecole.sgs.services.ClasseResource;
import org.ecole.sgs.services.SectionResource;
import org.ecole.sgs.util.enums.ClasseNameEN;
import org.ecole.sgs.util.enums.ClasseNameFR;
import org.ecole.sgs.util.enums.SectionName;
import org.ecole.sgs.util.functions.Function;

/**
 * FXML Controller class
 *
 * @author armel
 */
public class ClasseController implements Initializable {
    
    @FXML
    private ComboBox<String> section;
    
    @FXML
    private ComboBox<String> classe_names;
    
    @FXML 
    private TextField files_amount;
    
    @FXML
    private DatePicker files_limit;
    
    @FXML 
    private TextField first_amount;
    
    @FXML
    private DatePicker first_limit;
    
    @FXML 
    private TextField second_amount;
    
    @FXML
    private DatePicker second_limit;
    
    @FXML 
    private TextField third_amount;
    
    @FXML
    private DatePicker third_limit;
    
    @FXML 
    private TextField inscription_amount;
    
    @FXML
    private DatePicker inscription_limit;
    
    @FXML
    private Button submit;
    
    private List<String> sectionNames = new ArrayList<>();
    
    private Classe classe;
    
    private final SectionResource sectionRs = SectionResource.builder();
    private final ClasseResource classRs = ClasseResource.builder();
    
    private final List<String> availables_classes = classRs.getClassNames();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Section> secs = sectionRs.getEnabled(true);
        
        for(Section sec : secs){
            sectionNames.add(sec.getName());
        }
        
        ObservableList<String> list = new ObservableListWrapper<>(sectionNames);
        section.setItems(list);
        
        hiddeFileProperties();
        
    }
    
    @FXML
    private void sectionChangeHandle(Event event){
     
        switch(section.getValue()){
            
            case "Anglophone":
                classe_names.setItems(classEnList());
                break;
            default:
                classe_names.setItems(classFrList());
                break;
        }
    }
    
    @FXML
    private void classeChangeHandle(Event event){
        String value = classe_names.getValue();
        if(value.equals(ClasseNameFR.CMII.toString()) || value.equals(ClasseNameEN.CLASS_VI.toString())){
            showFileProperties();
        }
        else{
            hiddeFileProperties();
        }
    }
    
    @FXML
    private void fieldChangeHandle(Event event){
        TextField field = (TextField)event.getSource();
        try{
            Function.stringToDouble(field.getText());
            submit.setDisable(false);
        }catch(NumberFormatException nfe){
            submit.setDisable(true);
        } catch (Exception ex) {
            Logger.getLogger(ClasseController.class.getName()).log(Level.SEVERE, null, ex);
            submit.setDisable(true);
        }
    }
    
    @FXML
    private void submitHandle(ActionEvent event){
        
        if(validfields()){
            setClasseValue();
            if(files_amount.isVisible()){
                if(!(Function.stringValidation(files_amount.getText(), 4) && files_limit.getValue() != null) ){
                    submit.setDisable(true);
                }else{
                    classe.setFileAmount(Double.parseDouble(files_amount.getText()));
                    classe.setFileLimit(Function.localDateToDate(files_limit.getValue()));
                    classRs.add(classe);
                    System.out.println("La classe "+classe.getName()+" a été créée avec succès");
                }
            }else{
                classRs.add(classe);
                System.out.println("La classe "+classe.getName()+" a été créée avec succès");
            }
        }
    }
    
    @FXML
    private void dateChangeHandle(Event event){
        DatePicker dp = (DatePicker)event.getSource();
        if(dp.getValue() != null){
            submit.setDisable(false);
        }
    }
    
    private ObservableList<String> classFrList(){
        ClasseNameFR [] classes = ClasseNameFR.values();
        List<String> classesNames = new ArrayList<>();
        
        for(ClasseNameFR classe : classes){
            String current = classe.toString();
            if(!availables_classes.contains(current)){
                classesNames.add(current);
            }
        }
        
        ObservableList<String> list = new ObservableListWrapper<>(classesNames);
        
        return list;
    }
    
    private ObservableList<String> classEnList(){
        ClasseNameEN [] classes = ClasseNameEN.values();
        List<String> classesNames = new ArrayList<>();
        
        for(ClasseNameEN classe : classes){
            String current = classe.toString();
            if(!availables_classes.contains(current)){
                classesNames.add(current);
            }
        }
        
        ObservableList<String> list = new ObservableListWrapper<>(classesNames);
        
        return list;
    }
    
    private void hiddeFileProperties(){
        files_amount.setVisible(false);
        files_limit.setVisible(false);
    }
    
    private void showFileProperties(){
        files_amount.setVisible(true);
        files_limit.setVisible(true);
    }
    
    private boolean validfields(){
        return Function.defaultStringValidation(classe_names.getValue()) &&
               Function.stringValidation(first_amount.getText(), 4) &&
               Function.stringValidation(second_amount.getText(), 4) &&
               Function.stringValidation(third_amount.getText(), 4) &&
               Function.stringValidation(inscription_amount.getText(), 4) &&
               first_limit.getValue() != null &&
               second_limit.getValue() != null &&
               third_limit.getValue() != null &&
               inscription_limit.getValue() != null &&
               section.getValue() != null &&
               classe_names.getValue() != null;
    }
    
    private void setClasseValue(){
        Section sec = sectionRs.getByName(section.getValue()).get(0);
        classe = new Classe();
        classe.setFirstAmount(Double.parseDouble(first_amount.getText()));
        classe.setFirstLimit(Function.localDateToDate(first_limit.getValue()));
        classe.setInscriptionAmount(Double.parseDouble(inscription_amount.getText()));
        classe.setInscrptionLimit(Function.localDateToDate(inscription_limit.getValue()));
        classe.setName(classe_names.getValue());
        classe.setSecondAmount(Double.parseDouble(second_amount.getText()));
        classe.setSecondLimit(Function.localDateToDate(second_limit.getValue()));
        classe.setSection(sec);
        classe.setThirdAmount(Double.parseDouble(third_amount.getText()));
        classe.setThirdLimit(Function.localDateToDate(third_limit.getValue()));
        
    }
    
}
