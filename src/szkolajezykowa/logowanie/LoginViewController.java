/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package szkolajezykowa.logowanie;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.sun.javaws.Main;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import javafx.util.Duration;
import szkolajezykowa.FXMLMainController;
import szkolajezykowa.SzkolaJezykowa;
import szkolajezykowa.logowanie.admin.MainView.MainAdminViewController;
import szkolajezykowa.polaczenie.Polaczenie;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author BlackHawk
 */
public class LoginViewController implements Initializable {

    //public static Statement stmt;
    //public static PreparedStatement prep;
    private FXMLMainController mc;

    Stage stage;

    @FXML
    private JFXTextField loginPole;
    @FXML
    private JFXPasswordField hasloPole;
    @FXML
    private JFXComboBox rolaCb;
    @FXML
    private JFXButton loginBtn;
    @FXML
    private Label label;
    public static String r;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rolaCb.getItems().addAll(
                "Kursant",
                "Wykładowca",
                "Administrator"
        );
        rolaCb.setValue("Kursant");
        rolaCb.setStyle("-fx-font: 18px \"System\";");
    }

    @FXML
    private void zaloguj(ActionEvent event) {

        try {

            Connection con = Polaczenie.ConnectDB();
            String rola = rolaCb.getSelectionModel().getSelectedItem().toString().toLowerCase();
            //r = rolaCb.getSelectionModel().getSelectedItem().toString().toLowerCase();
            PreparedStatement ps = null;
            if (rola.equals("administrator")) {
                rola = "admin";
            }
            if (rola.equals("kursant")) {
                rola = "kursanci";
            }
            if (rola.equals("wykładowca")){
                rola = "wykladowcy";
            }
            //ps = con.prepareStatement("SELECT login,haslo FROM " + rola );
            ResultSet rs = null;

            boolean sprawdzenie = false;

            switch (rola) {
                case ("admin"):
                    ps = con.prepareStatement("SELECT id_admin,login,haslo FROM admin");
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        if (rs.getString("login").equals(loginPole.getText())) {
                            if (rs.getString("haslo").equals(hasloPole.getText())) {
                                sprawdzenie = true;
                                r = rs.getString("id_admin");
                                break;
                            }
                        }
                    }
                    break;
                case ("kursanci"):
                    ps = con.prepareStatement("SELECT id_kursanta,login,haslo FROM kursanci");
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        if (rs.getString("login").equals(loginPole.getText())) {
                            if (rs.getString("haslo").equals(hasloPole.getText())) {
                                sprawdzenie = true;
                                r = rs.getString("id_kursanta");
                                break;
                            }
                        }
                    }
                    break;
                case ("wykladowcy"):
                    ps = con.prepareStatement("SELECT id_wykladowcy,login,haslo FROM wykladowcy");
                    rs = ps.executeQuery();
                    while (rs.next()){
                        if (rs.getString("login").equals(loginPole.getText())){
                            if (rs.getString("haslo").equals(hasloPole.getText())){
                                sprawdzenie = true;
                                r = rs.getString("id_wykladowcy");
                                break;
                            }
                        }
                    }
                    break;
            }
            //ResultSet rs = ps.executeQuery();
            //boolean sprawdzenie = false;
            /*
            while(rs.next()){
                if (rs.getString("login").equals(loginPole.getText())){
                    if (rs.getString("haslo").equals(hasloPole.getText())){
                        sprawdzenie = true;
                        break;
                    }
                }
            }*/

            //while (rs.next()){
            //if (rs.getString("login").equals(loginPole.getText())){
            if (sprawdzenie) {
                System.out.println("Logowanie przebiegło pomyślnie!");
                label.setVisible(false);

                AudioClip powiadomMnie = new AudioClip((SzkolaJezykowa.class.getResource("audio/Notify.wav")).toString());
                powiadomMnie.play();

                TrayNotification tray = new TrayNotification();
                tray.setTitle("Sukces!");
                tray.setMessage("Logowanie przebiegło pomyślnie");
                tray.setNotificationType(NotificationType.SUCCESS);
                tray.showAndDismiss(Duration.millis(1500));

                Parent root = null;
                switch (rola) {
                    case ("admin"):
                        root = FXMLLoader.load(LoginViewController.class.getResource("admin/MainView/MainAdminView.fxml"));
                        break;
                    case ("kursanci"):
                        root = FXMLLoader.load(LoginViewController.class.getResource("uzytkownik/MainView/MainUzytkownikView.fxml"));
                        break;
                    case ("wykladowcy"):
                        root = FXMLLoader.load(LoginViewController.class.getResource("wykladowca/MainView/MainWykladowcaView.fxml"));
                        break;
                }
                stage = new Stage();
                Image icon = new Image(SzkolaJezykowa.class.getResourceAsStream("obrazki/fawicon.png"));
                stage.getIcons().add(icon);
                if (rola.equals("admin")) {
                    rola = "administratora";
                }
                if (rola.equals("kursanci")) {
                    rola = "kursanta";
                }
                if (rola.equals("wykladowcy")){
                    rola = "wykładowcy";
                }
                stage.setTitle("Panel " + rola);
                Scene scene = new Scene(root);
                stage.setScene(scene);
                loginBtn.getScene().getWindow().hide();
                stage.show();
                loginPole.setText(null);
                hasloPole.setText(null);
                //}
            } else {
                loginPole.setText(null);
                hasloPole.setText(null);
                label.setVisible(true);

                AudioClip powiadomMnie = new AudioClip((SzkolaJezykowa.class.getResource("audio/Notify.wav")).toString());
                powiadomMnie.play();

                TrayNotification tray = new TrayNotification();
                tray.setTitle("Błąd!");
                tray.setMessage("Logowanie zakończone fiaskiem.");
                tray.setNotificationType(NotificationType.ERROR);
                tray.showAndDismiss(Duration.millis(1500));
                //}
            }


            ps.close();
            rs.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println("Błąd SQL!");
        } catch (IOException ex) {
            Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

        /* test
         try {
                Parent root = FXMLLoader.load(getClass().getResource("admin/MainView/MainAdminView.fxml"));
                stage = new Stage();
                Image icon = new Image(SzkolaJezykowa.class.getResourceAsStream("obrazki/fawicon.png"));
                stage.getIcons().add(icon);
                stage.setTitle("Admin Portal - Please obtain authorization before viewing this portal as admin");
                Scene scene = new Scene(root);
                stage.setScene(scene);
                loginBtn.getScene().getWindow().hide();
                stage.show();                
            } catch (IOException ex) {
                System.out.println("Error loading the admin stage !");
                ex.printStackTrace();
            } */
    }

}
