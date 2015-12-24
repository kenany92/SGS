package org.ecole.sgs;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import org.ecole.sgs.entities.Etablissement;
import org.ecole.sgs.services.EtablissementResource;
import org.ecole.sgs.util.functions.Function;

public class FXMLController implements Initializable {
    
    @FXML
    private Label sgs;
    
    @FXML
    private ProgressBar pbar;
    
    private final EtablissementResource etabRs = EtablissementResource.builder();
    
    private List<Etablissement> etabs;
    
    private final Function fn = new Function();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        etabs = etabRs.getAll();
        
       
        pbar.indeterminateProperty().addListener(new ChangeListener<Boolean>(){

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue){
                    System.out.println("Calcul du temps");
                }
                else{
                    System.out.println("En cours");
                }
            }
            
        });
        
        pbar.progressProperty().addListener(new ChangeListener<Number>(){

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if(newValue.doubleValue() == 1){
                    if(etabs.isEmpty()){
                        pbar.getParent().getScene().getWindow().hide();
                        Alert al = new Alert(Alert.AlertType.CONFIRMATION," Vous n'avez encore aucun etablissement dans la base de données");
                        al.setTitle("Info base de données");
                        al.showAndWait();
                        
                        if(al.getResult() == ButtonType.OK){
                            try {
                                fn.openEtabType("/fxml/etab_type.fxml", "Type etablissement");
                            } catch (IOException ex) {
                                Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
            }
            
        });
        
        Task task = createTask(5);
        pbar.progressProperty().unbind();
        pbar.progressProperty().bind(task.progressProperty());
        new Thread(task).start();
        
        
    } 
    
    private Task createTask(final int time){
        return new Task() {

            @Override
            protected Object call() throws Exception {
                for(float i = 0; i < time; i += 0.01){
                    Thread.sleep(10);
                    updateProgress(i+0.1, time);
            }
                return true;
            }
            
        };
    }
    
//    private void openEtabType() throws IOException {
//        Parent root;
//                try {
//                        
//                        root = FXMLLoader.load(getClass().getResource("/fxml/etab_type.fxml"));
//                        Scene scene = new Scene(root);
//                        Stage stage = new Stage();
//                        stage.setTitle("Type Etablissement");
//                        stage.setScene(scene);
//                        stage.show();
////                    ((Node)event.getSource()).getScene().getWindow().hide();
//                } catch (IOException ex) {
//                    Logger.getLogger(EtablissementType.class.getName()).log(Level.SEVERE, null, ex);
//                }
//        
//        
//    }
}
