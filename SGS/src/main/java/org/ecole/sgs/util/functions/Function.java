/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ecole.sgs.util.functions;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.ecole.sgs.util.enums.EtablissementType;

/**
 *
 * @author armel
 */
public class Function {
    
    private static final DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    
    public static boolean defaultStringValidation(String candidate){
        
        return candidate != null && !candidate.trim().equals("") && candidate.length() >= 3;
    }
    
    public static boolean stringValidation(String candidate, int size){
        
        return candidate != null && !candidate.trim().equals("") && candidate.length() >= size;
    }
    
    public static String dateToAs(Date first, Date second){
        String first_s = first.toString();
        String second_s = second.toString();
        String beguin = first_s.substring(first_s.lastIndexOf(" ")+1);
        String end = second_s.substring(second_s.lastIndexOf(" ")+1);
        
        return beguin+"/"+end;
    }
    
    public void openEtabType(String src, String title) throws IOException {
        String title_r = title != null? title : "titre";
        Parent root;
                try {
                        
                        root = FXMLLoader.load(getClass().getResource(src));
                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.setTitle(title_r);
                        stage.setScene(scene);
                        stage.show();
//                    ((Node)event.getSource()).getScene().getWindow().hide();
                } catch (IOException ex) {
                    Logger.getLogger(EtablissementType.class.getName()).log(Level.SEVERE, null, ex);
                }
        
        
    }
    
    public static Date localDateToDate(LocalDate local){
        Instant inst1 = Instant.from(local.atStartOfDay(ZoneId.systemDefault()));
        Date date = Date.from(inst1);
        return date;
    }
    
    public static String parseDate(Date date){
        return format.format(date);
    }
    
    public static Double stringToDouble(String val) throws Exception{
       
        try{
            double value = Double.parseDouble(val);
            return value;
        }catch(NumberFormatException pex){
            throw new Exception("Veuillez saisir un nombre correct");
        }
        
    }
    
    public static Function getFunction(){
        return new Function();
    }
}
