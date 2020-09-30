/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package szkolajezykowa.logowanie.admin.MainView;

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
public class MainAdminViewController implements Initializable {

    @FXML
    private JFXDrawer drawer;
    @FXML
    private AnchorPane dynamicPane;
    @FXML
    private AnchorPane repPane;
    @FXML
    private Label label;
    AnchorPane kursy, wykladowcy, kursanci, zaliczenia, sale, wyloguj;
    VBox bocznyPanel;
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        try {
            System.out.println(LoginViewController.r);
            wyloguj = FXMLLoader.load(SzkolaJezykowa.class.getResource("FXMLMain.fxml"));
            kursy = FXMLLoader.load(getClass().getResource("kursy/KursyView.fxml"));
            wykladowcy = FXMLLoader.load(getClass().getResource("wykladowcy/WykladowcyView.fxml"));
            kursanci = FXMLLoader.load(getClass().getResource("kursanci/KursanciView.fxml"));
            zaliczenia = FXMLLoader.load(getClass().getResource("zaliczenia/ZaliczeniaView.fxml"));
            sale = FXMLLoader.load(getClass().getResource("sale/SaleView.fxml"));
            bocznyPanel = FXMLLoader.load(getClass().getResource("AdminDrawer.fxml"));
            drawer.setSidePane(bocznyPanel);
            drawer.open();

            for (Node node : bocznyPanel.getChildren()) {
                if (node.getAccessibleText() != null) {

                    node.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent e) {
                            switch (node.getAccessibleText()) {
                                case ("zarzadzajKursami"):
                                    label.setText("ZARZĄDZANIE KURSAMI");
                                    repPane.getChildren().clear();
                                    repPane.getChildren().add((Node) kursy);
                                    break;
                                case ("zarzadzajWykladowcami"):
                                    label.setText("ZARZĄDZANIE WYKŁADOWCAMI");
                                    repPane.getChildren().clear();
                                    repPane.getChildren().add((Node) wykladowcy);
                                    break;
                                case ("zarzadzajKursantami"):
                                    label.setText("ZARZĄDZANIE KURSANTAMI");
                                    repPane.getChildren().clear();
                                    repPane.getChildren().add((Node) kursanci);
                                    break;
                                case ("zarzadzajSalami"):
                                    label.setText("ZARZĄDZANIE SALAMI");
                                    repPane.getChildren().clear();
                                    repPane.getChildren().add((Node) sale);
                                    break;
                                case ("zarzadzajZaliczeniami"):
                                    label.setText("ZARZĄDZANIE ZALICZENIAMI");
                                    repPane.getChildren().clear();
                                    repPane.getChildren().add((Node) zaliczenia);
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
