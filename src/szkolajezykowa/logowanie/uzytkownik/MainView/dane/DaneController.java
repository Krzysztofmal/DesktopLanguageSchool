/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package szkolajezykowa.logowanie.uzytkownik.MainView.dane;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import szkolajezykowa.logowanie.LoginViewController;
import szkolajezykowa.polaczenie.Polaczenie;
import szkolajezykowa.sprawdzanie.Sprawdzanie;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author BlackHawk
 */
public class DaneController implements Initializable {

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
    private JFXTextField fPesel;
    @FXML
    private JFXTextField fEmail;
    @FXML
    private JFXTextField fTelefon;
    @FXML
    private JFXRadioButton rKobieta;
    @FXML
    private ToggleGroup plecRadio;
    @FXML
    private JFXRadioButton rMezczyzna;
    @FXML
    private JFXComboBox statusCb;
    @FXML
    private JFXButton rejestrujBtn;
    @FXML
    private Label bladLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //fLogin.setDisable(true);
        fLogin.setEditable(false);
        statusCb.setDisable(true);
        rKobieta.setDisable(true);
        rMezczyzna.setDisable(true);
        statusCb.getItems().addAll(
            "uczeń",
            "student",
            "emeryt/rencista",
            "inny"
        );
        rKobieta.setSelectedColor(Color.RED);
        rMezczyzna.setSelectedColor(Color.BLUE);
        statusCb.setStyle("-fx-font: 18px \"System\";");
        
         try {
            Connection con = Polaczenie.ConnectDB();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM kursanci WHERE id_kursanta=" + LoginViewController.r);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                fLogin.setText(rs.getString("login"));
                fHaslo.setText(rs.getString("haslo"));
                fHaslo2.setText(rs.getString("haslo"));
                fImie.setText(rs.getString("imie"));
                fNazwisko.setText(rs.getString("nazwisko"));
                fPesel.setText(rs.getString("pesel"));
                fEmail.setText(rs.getString("email"));
                fTelefon.setText(rs.getString("nr_tel"));
                if (rs.getString("plec").equals("M")){
                    plecRadio.selectToggle(rMezczyzna);
                } else {
                    plecRadio.selectToggle(rKobieta);
                }
                
                switch (rs.getString("status")){
                    case ("uczeń"):
                        statusCb.setValue("uczeń");
                        break;
                    case ("student"):
                        statusCb.setValue("student");
                        break;
                    case ("emeryt/rencista"):
                        statusCb.setValue("emeryt/rencista");
                        break;
                    case ("inny"):
                        statusCb.setValue("inny");
                        break;
                }
                
                
            }
            con.close();
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }


    private void pokazKomunikat(String text){
        TrayNotification tray = new TrayNotification();
        tray.setTitle("Błąd!");
        tray.setMessage(text);
        tray.setNotificationType(NotificationType.ERROR);
        tray.showAndDismiss(Duration.millis(1500));
    }
    

    @FXML
    private void zapiszDane(ActionEvent event) throws SQLException {
        
        if  (Sprawdzanie.czyLitery(fImie.getText()) && Sprawdzanie.czyLitery(fNazwisko.getText()) && Sprawdzanie.peselPoprawny(fPesel.getText()) && Sprawdzanie.poprawnyEmail(fEmail.getText()) && Sprawdzanie.telefonPoprawny(fTelefon.getText())) {
            if (fHaslo.getText().equals(fHaslo2.getText())) {
                //wykonaj update danych
                
                
                Connection con = Polaczenie.ConnectDB();
                String query = "{CALL KRZYSZTOF.update_kursant(?,?,?,?,?,?,?)}";
                CallableStatement cst = con.prepareCall(query);
                cst.setString(1, LoginViewController.r);
                cst.setString(2, fImie.getText());
                cst.setString(3, fNazwisko.getText());
                cst.setString(4, fPesel.getText());
                cst.setString(5, fTelefon.getText());
                cst.setString(6, fEmail.getText());                
                cst.setString(7, fHaslo.getText());
                
                cst.execute();
                cst.close();
                con.close();
                
                
                
                
                
                TrayNotification tray = new TrayNotification();
                tray.setTitle("Sukces!");
                tray.setMessage("Aktualizacja danych przebiegła pomyślnie.");
                tray.setNotificationType(NotificationType.SUCCESS);
                tray.showAndDismiss(Duration.millis(1500));
                
                bladLabel.setVisible(false);
                
            } else {
                bladLabel.setVisible(true);
                pokazKomunikat("Aktualizacja danych zakończyła się fiaskiem!");
            }
        } else {
            
            pokazKomunikat("Dane wejściowe zostały uzupełnione nieprawidłowo!");
            bladLabel.setVisible(false);
        }
        
    }
    
}
