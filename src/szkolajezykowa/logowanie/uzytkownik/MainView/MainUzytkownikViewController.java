/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package szkolajezykowa.logowanie.uzytkownik.MainView;

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
import javafx.scene.layout.VBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import szkolajezykowa.SzkolaJezykowa;
import szkolajezykowa.logowanie.LoginViewController;

/**
 * FXML Controller class
 *
 * @author BlackHawk
 */
public class MainUzytkownikViewController implements Initializable {

    @FXML
    private JFXDrawer drawer;
    @FXML
    private AnchorPane dynamicPane;
    @FXML
    private AnchorPane repPane;
    @FXML
    private Label label;
    AnchorPane zapiszSie, twojeZaliczenia, twojeDane, twojeKursy, wyloguj;
    VBox bocznyPanel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            System.out.println(LoginViewController.r);
            //boczny panel uzytkownikDrawer
            wyloguj = FXMLLoader.load(SzkolaJezykowa.class.getResource("FXMLMain.fxml"));
            bocznyPanel = FXMLLoader.load(getClass().getResource("UzytkownikDrawer.fxml"));
            zapiszSie = FXMLLoader.load(getClass().getResource("zapiszNaKurs/ZapiszNaKurs.fxml"));
            twojeZaliczenia = FXMLLoader.load(getClass().getResource("zaliczenia/Zaliczenia.fxml"));
            twojeDane = FXMLLoader.load(getClass().getResource("dane/Dane.fxml"));
            twojeKursy = FXMLLoader.load(getClass().getResource("kursy/Kursy.fxml"));
            drawer.setSidePane(bocznyPanel);
            drawer.open();

            for (Node node : bocznyPanel.getChildren()) {
                if (node.getAccessibleText() != null) {

                    node.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent e) {

                            switch (node.getAccessibleText()) {
                                case ("zapiszNaKurs"):
                                    label.setText("ZAPISZ SIĘ NA KURS");
                                    repPane.getChildren().clear();
                                    repPane.getChildren().add((Node) zapiszSie);
                                    break;
                                case ("twojeZaliczenia"):
                                    label.setText("TWOJE ZALICZENIA");
                                    repPane.getChildren().clear();
                                    repPane.getChildren().add((Node) twojeZaliczenia);
                                    break;
                                case ("twojeDane"):
                                    label.setText("TWOJE DANE");
                                    repPane.getChildren().clear();
                                    repPane.getChildren().add((Node) twojeDane);
                                    break;
                                case ("twojeKursy"):
                                    label.setText("TWOJE KURSY");
                                    repPane.getChildren().clear();
                                    repPane.getChildren().add((Node) twojeKursy);
                                    break;
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
