/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package szkolajezykowa.logowanie.admin.MainView.kursanci;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
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
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
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
public class DodajKursantaViewController implements Initializable {

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
    private JFXComboBox<String> statusCb;
    @FXML
    private JFXButton rejestrujBtn;
    @FXML
    private Label bladLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        statusCb.getItems().addAll(
                "uczeń",
                "student",
                "emeryt/rencista",
                "inny"
        );
        rKobieta.setSelectedColor(Color.RED);
        rMezczyzna.setSelectedColor(Color.BLUE);
        statusCb.setStyle("-fx-font: 18px \"System\";");
    }

    @FXML
    private void rejestrujKursanta(ActionEvent event) throws SQLException, IOException {
        //sprawdzic unikalnosc loginu
        Connection con = Polaczenie.ConnectDB();
        PreparedStatement ps = con.prepareStatement("SELECT login FROM kursanci");
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
            String query = "{CALL KRZYSZTOF.rejestruj_kursanta(?,?,?,?,?,?,?,?,?)}";
            CallableStatement cst = con.prepareCall(query);
            cst.setString(1, fImie.getText());
            cst.setString(2, fNazwisko.getText());
            cst.setString(3, fPesel.getText());
            cst.setString(4, fTelefon.getText());
            cst.setString(5, fEmail.getText());
            cst.setString(6, fLogin.getText());
            cst.setString(7, fHaslo.getText());
            cst.setString(8, (String) statusCb.getSelectionModel().getSelectedItem().toString());
            String plec;
            if (rKobieta.isSelected()) {
                plec = "K";
            } else {
                plec = "M";
            }
            cst.setString(9, plec);

            cst.execute();
            cst.close();
            rs.close();
            ps.close();
            con.close();

            AudioClip powiadomMnie = new AudioClip((SzkolaJezykowa.class.getResource("audio/Notify.wav")).toString());
            powiadomMnie.play();

            TrayNotification tray = new TrayNotification();
            tray.setTitle("Sukces!");
            tray.setMessage("Rejestracja przebiegła pomyślnie. Możesz się teraz zalogować.");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(1500));

            //zmienić
            /*
            AnchorPane logowanie = FXMLLoader.load(SzkolaJezykowa.class.getResource("logowanie/LoginView.fxml"));
            AnchorPane home_parent2 = (AnchorPane) rejestrujBtn.getParent().getParent().getParent();
            
            home_parent2.getChildren().clear();
            home_parent2.getChildren().add(logowanie);*/
            AnchorPane dodaj = FXMLLoader.load(getClass().getResource("KursanciView.fxml"));
            AnchorPane home_parent3 = (AnchorPane) rejestrujBtn.getParent().getParent().getParent().getParent();
            home_parent3.getChildren().clear();
            home_parent3.getChildren().add(dodaj);

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
