/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package szkolajezykowa;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import tray.notification.TrayNotification;
import javafx.scene.Node;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;
import tray.notification.NotificationType;

/**
 *
 * @author BlackHawk
 */
public class FXMLMainController implements Initializable {
    
    VBox bocznyPanel;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXComboBox depCombo;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label labelPanel;
    TrayNotification tray;
    
    
    @FXML
    public void showDrawer(MouseEvent me) {
        if (!drawer.isShown()) {
            drawer.open();
        } else {
            drawer.isShown();
        }
    }
    
    @FXML
    private void hideDrawer(MouseEvent event) {
        drawer.close();
    }
    
    public void setNode(Node node){
        
        
        anchorPane.getChildren().clear();
        anchorPane.getChildren().add((Node) node);
        
        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        labelPanel.setText("");
        
        try {
            bocznyPanel = FXMLLoader.load(getClass().getResource("FXMLDrawer.fxml"));
            AnchorPane loginPane = FXMLLoader.load(getClass().getResource("logowanie/LoginView.fxml"));
            AnchorPane zarejestrujPane = FXMLLoader.load(getClass().getResource("rejestracja/RejestracjaView.fxml"));
            AnchorPane statystykiPane = FXMLLoader.load(getClass().getResource("statystyki/StatystykiView.fxml"));
            AnchorPane opiniaPane = FXMLLoader.load(getClass().getResource("opinia/OpiniaView.fxml"));
            drawer.setSidePane(bocznyPanel);
            
            for (Node node : bocznyPanel.getChildren()){
                if(node.getAccessibleText() != null){
                    node.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
                        
                        switch(node.getAccessibleText()){
                            case ("btnZaloguj"):
                                drawer.close();
                                labelPanel.setText("PANEL LOGOWANIA");
                                setNode(loginPane);
                                break;
                            case ("btnZarejestruj"):
                                drawer.close();
                                labelPanel.setText("PANEL REJESTRACJI");
                                setNode(zarejestrujPane);
                                break;
                            case ("btnStatystyki"):
                                drawer.close();
                                labelPanel.setText("STATYSTYKI");
                                setNode(statystykiPane);
                                break;
                            case ("btnOpinia"):
                                drawer.close();
                                labelPanel.setText("PODZIEL SIĘ SWOJĄ OPINIĄ");
                                setNode(opiniaPane);
                                break;
                            case ("btnWyjscie"):
                                System.exit(0);
                                break;
                        }
                    });
                }
            }
            
            } catch (IOException ex) {
            ex.printStackTrace();
        }
      
    }    


    
}
