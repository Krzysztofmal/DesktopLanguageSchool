/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package szkolajezykowa.logowanie.wykladowca.MainView;

import com.jfoenix.controls.JFXDrawer;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import szkolajezykowa.SzkolaJezykowa;
import szkolajezykowa.logowanie.LoginViewController;

/**
 * FXML Controller class
 *
 * @author BlackHawk
 */
public class MainWykladowcaViewController implements Initializable {

    @FXML
    private JFXDrawer drawer;
    @FXML
    private AnchorPane dynamicPane;
    @FXML
    private AnchorPane repPane;
    @FXML
    private Label label;
    AnchorPane kursy,kursanci,zaliczenia,wyloguj;
    VBox bocznyPanel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        try {
             System.out.println(LoginViewController.r);
             wyloguj = FXMLLoader.load(SzkolaJezykowa.class.getResource("FXMLMain.fxml"));
             zaliczenia = FXMLLoader.load(getClass().getResource("zaliczenia/ZaliczeniaView.fxml"));
             kursanci = FXMLLoader.load(getClass().getResource("kursanci/KursanciView.fxml"));
             kursy = FXMLLoader.load(getClass().getResource("kursy/KursyView.fxml"));
             bocznyPanel = FXMLLoader.load(getClass().getResource("WykladowcaDrawer.fxml"));
             drawer.setSidePane(bocznyPanel);
             drawer.open();
             
             for (Node node : bocznyPanel.getChildren()) {
                 if (node.getAccessibleText() != null){
                     
                     node.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler <MouseEvent>(){
                         @Override
                         public void handle(MouseEvent e) {
                             switch (node.getAccessibleText()){
                                case ("wylogujSie"):
                                    Image image = new Image(SzkolaJezykowa.class.getResourceAsStream("obrazki/fawicon.png"));
                                    Stage mainStage = new Stage();
                                    Scene scene = new Scene(wyloguj);
                                    mainStage.setScene(scene);
                                    repPane.getScene().getWindow().hide();
                                    mainStage.getIcons().add(image);
                                    mainStage.show();
                                    break;
                                case ("wyjscieExit"):
                                    System.exit(0);
                                    break;
                                case ("twoiKursanci"):
                                    label.setText("TWOI KURSANCI");
                                    repPane.getChildren().clear();
                                    repPane.getChildren().add((Node) kursanci);
                                    break;
                                case ("twojeKursy"):
                                    label.setText("TWOJE KURSY");
                                    repPane.getChildren().clear();
                                    repPane.getChildren().add((Node) kursy);
                                    break;
                                case ("zaliczenia"):
                                    label.setText("ZALICZENIA");
                                    repPane.getChildren().clear();
                                    repPane.getChildren().add((Node) zaliczenia);
                                    break;
                             }
                         }
                         
                     });
                 }
                 
             }
            
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        
        
    }    

    @FXML
    private void showDrawer(MouseEvent event) {
            if (!drawer.isShown()) {
                drawer.open();
        }
    }
    
}
