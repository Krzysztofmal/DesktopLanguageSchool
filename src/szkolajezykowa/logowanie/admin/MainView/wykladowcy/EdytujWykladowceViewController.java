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
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class EdytujWykladowceViewController implements Initializable {

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
    private JFXButton aktualizujBtn;
    @FXML
    private Label bladLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            Connection con = Polaczenie.ConnectDB();
            PreparedStatement ps = con.prepareStatement("SELECT imie,nazwisko,email,telefon,jezyk,login,haslo FROM wykladowcy WHERE id_wykladowcy="+WykladowcyViewController.idWykladowcy);
            ResultSet rs = ps.executeQuery();
            rs.next();
            fImie.setText(rs.getString("imie"));
            fNazwisko.setText(rs.getString("nazwisko"));
            fTelefon.setText(rs.getString("telefon"));
            fEmail.setText(rs.getString("email"));
            fJezyk.setText(rs.getString("jezyk"));
            fJezyk.setEditable(false);
            fLogin.setText(rs.getString("login"));
            fHaslo.setText(rs.getString("haslo"));
            fHaslo2.setText(rs.getString("haslo"));
            con.close();
            rs.close();
            ps.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(EdytujWykladowceViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    

    @FXML
    private void aktualizujWykladowce(ActionEvent event) throws SQLException, IOException {
        Connection con = Polaczenie.ConnectDB();


        boolean rejestruj = true;

        if (!fHaslo.getText().equals(fHaslo2.getText())) {
            //rejestruj = false;
        }
        
        
        
        if (rejestruj){
        String query = "{CALL KRZYSZTOF.update_wykladowca(?,?,?,?,?,?,?,?)}";
        //Connection con = Polaczenie.ConnectDB();
        CallableStatement cst = con.prepareCall(query);
        cst.setInt(1, WykladowcyViewController.idWykladowcy);
        cst.setString(2, fImie.getText());
        cst.setString(3, fNazwisko.getText());
        cst.setString(4, fEmail.getText());
        cst.setString(5, fTelefon.getText());
        cst.setString(6, fJezyk.getText());
        cst.setString(7, fLogin.getText());
        cst.setString(8, fHaslo.getText());
        

            cst.execute();
            cst.close();
            con.close();

            AudioClip powiadomMnie = new AudioClip((SzkolaJezykowa.class.getResource("audio/Notify.wav")).toString());
            powiadomMnie.play();

            TrayNotification tray = new TrayNotification();
            tray.setTitle("Sukces!");
            tray.setMessage("Wykładowca został dodany");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(1500));
            
            AnchorPane dodaj = FXMLLoader.load(getClass().getResource("WykladowcyView.fxml"));
        AnchorPane home_parent2 = (AnchorPane) aktualizujBtn.getParent().getParent().getParent().getParent().getParent();
        home_parent2.getChildren().clear();
        home_parent2.getChildren().add(dodaj);
    }
        
         if (rejestruj == false) {
            //komunikat o błędzie
            AudioClip powiadomMnie = new AudioClip((SzkolaJezykowa.class.getResource("audio/Notify.wav")).toString());
            powiadomMnie.play();

            TrayNotification tray = new TrayNotification();
            tray.setTitle("Błąd!");
            tray.setMessage("Podczas aktualizacji wystąpił błąd.");
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.millis(1500));
        }
    }
    
}
