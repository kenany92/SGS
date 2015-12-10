package org.ecole.sgs;

import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.ecole.sgs.services.EleveResource;


public class MainApp extends Application {
    
    private final EleveResource eleveRs = EleveResource.builder();

    @Override
    public void start(Stage stage) throws Exception {
        if(eleveRs.getAll().isEmpty()){
            Alert al = new Alert(Alert.AlertType.CONFIRMATION," Vous n'avez encore aucun eleve dans la base de données vous devez en créer au moins un");
            al.setTitle("Info base de données");
            al.showAndWait();
            
            if(al.getResult() == ButtonType.OK){
                run(stage);
            }
            
        }else{
            run(stage);
        }
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    private void run(Stage stage) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        
        Scene scene = new Scene(root, Double.MAX_EXPONENT, Double.MAX_EXPONENT);
        scene.getStylesheets().add("/styles/Styles.css");

        stage.setTitle("SGS");
        stage.getIcons().add(new Image("/img/logo.png"));
        stage.setScene(scene);
        stage.show();
    }

}
