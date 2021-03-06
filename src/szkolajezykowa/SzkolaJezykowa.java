/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package szkolajezykowa;

import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author BlackHawk
 */
public class SzkolaJezykowa extends Application {
    
    Stage window;
    public double xOffset, yOffset;
    
    @Override
    public void init()  {
        System.out.println("Aplikacja ładuje zasoby.");
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        window = stage;
        Parent root = FXMLLoader.load(getClass().getResource("FXMLMain.fxml"));
        Image icon = new Image(SzkolaJezykowa.class.getResourceAsStream("obrazki/fawicon.png"));
        Scene scene = new Scene(root);
        
        window.setScene(scene);
        window.getIcons().add(icon);
        window.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
