/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package szkolajezykowa.logowanie.admin.MainView.wykladowcy;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;
import szkolajezykowa.SzkolaJezykowa;
import szkolajezykowa.polaczenie.Polaczenie;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author BlackHawk
 */
public class DodajWykladowceViewController implements Initializable {

    @FXML
    private JFXTextField fLogin;
    @FXML
    private JFXPasswordField fHaslo;
    @FXML
    private JFXPasswordField fHaslo2;
    @FXML
    private JFXTextField fImie;
    @FXML
    private JFXTextField fNazwisko;
    @FXML
    private JFXTextField fTelefon;
    @FXML
    private JFXTextField fEmail;
    @FXML
    private JFXTextField fJezyk;
    @FXML
    private JFXButton rejestrujBtn;
    @FXML
    private Label bladLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void rejestrujWykladowce(ActionEvent event) throws SQLException, IOException {
        Connection con = Polaczenie.ConnectDB();
        PreparedStatement ps = con.prepareStatement("SELECT login FROM wykladowcy");
        ResultSet rs = ps.executeQuery();
        boolean rejestruj = true;
        while (rs.next()) {
            if (fLogin.getText().equals(rs.getString("login"))) {
                rejestruj = false;
            }
            // wyrzucić błąd o nieunikalnosci loginu
        }
        if (!fHaslo.getText().equals(fHaslo2.getText())) {
            //rejestruj = false;
        }

        if (rejestruj) {
            String query = "{CALL KRZYSZTOF.dodaj_wykladowce(?,?,?,?,?,?,?)}";
            CallableStatement cst = con.prepareCall(query);
            cst.setString(1, fImie.getText());
            cst.setString(2, fNazwisko.getText());
            cst.setString(3, fEmail.getText());
            cst.setString(4, fTelefon.getText());
            cst.setString(5, fJezyk.getText());
            cst.setString(6, fLogin.getText());
            cst.setString(7, fHaslo.getText());
            

            cst.execute();
            cst.close();
            rs.close();
            ps.close();
            con.close();

            AudioClip powiadomMnie = new AudioClip((SzkolaJezykowa.class.getResource("audio/Notify.wav")).toString());
            powiadomMnie.play();

            TrayNotification tray = new TrayNotification();
            tray.setTitle("Sukces!");
            tray.setMessage("Rejestracja przebiegła pomyślnie. ");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(1500));

            
            AnchorPane dodaj = FXMLLoader.load(getClass().getResource("WykladowcyView.fxml"));
        AnchorPane home_parent2 = (AnchorPane) rejestrujBtn.getParent().getParent().getParent().getParent().getParent();
        home_parent2.getChildren().clear();
        home_parent2.getChildren().add(dodaj);
            
            //zmienić
            /*
            AnchorPane logowanie = FXMLLoader.load(SzkolaJezykowa.class.getResource("logowanie/LoginView.fxml"));
            AnchorPane home_parent2 = (AnchorPane) rejestrujBtn.getParent().getParent().getParent();
            
            home_parent2.getChildren().clear();
            home_parent2.getChildren().add(logowanie);*/
        }

        if (rejestruj == false) {
            //komunikat o błędzie
            AudioClip powiadomMnie = new AudioClip((SzkolaJezykowa.class.getResource("audio/Notify.wav")).toString());
            powiadomMnie.play();

            TrayNotification tray = new TrayNotification();
            tray.setTitle("Błąd!");
            tray.setMessage("Podczas rejestracji wystąpił błąd.");
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.millis(1500));
        }
    }
    
}
