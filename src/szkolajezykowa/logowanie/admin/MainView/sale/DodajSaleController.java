/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package szkolajezykowa.logowanie.admin.MainView.sale;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;
import szkolajezykowa.SzkolaJezykowa;
import szkolajezykowa.polaczenie.Polaczenie;
import szkolajezykowa.sprawdzanie.Sprawdzanie;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author BlackHawk
 */
public class DodajSaleController implements Initializable {

    
    @FXML
    private JFXButton fDodajSale;
    @FXML
    private JFXButton fAnuluj;
    @FXML
    private JFXTextField fNrSali;
    @FXML
    private JFXTextField fPojemnosc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void dodajSale(ActionEvent event) throws SQLException, IOException {
        AnchorPane dodaj = FXMLLoader.load(getClass().getResource("SaleView.fxml")); 
        
        //NR SALI UNIKALNY ZROBIĆ!!!!!!
        Connection con = Polaczenie.ConnectDB();
        PreparedStatement ps = con.prepareStatement("SELECT nr_sali FROM sala");
        ResultSet rs = ps.executeQuery();
        boolean dodajSale = true;
        while (rs.next()){
            if (rs.getInt(1) == Integer.parseInt(fNrSali.getText())){
                dodajSale = false;
            }
        }
        rs.close();
        ps.close();
        if (!Sprawdzanie.czyLiczby(fNrSali.getText()) || !Sprawdzanie.czyLiczby(fPojemnosc.getText())){
            dodajSale = false;
        }
        
        
        if (dodajSale){
            String query = "{CALL KRZYSZTOF.dodaj_sale(?,?)}";
            CallableStatement cst = con.prepareCall(query);

            Integer nr = Integer.parseInt(fNrSali.getText());
            Integer poj = Integer.parseInt(fPojemnosc.getText());
            //System.out.println(nr + " " + poj);

            cst.setInt(1, nr);
            cst.setInt(2, poj);

            cst.execute();
            con.close();
            cst.close();
            
            AudioClip powiadomMnie = new AudioClip((SzkolaJezykowa.class.getResource("audio/Notify.wav")).toString());
            powiadomMnie.play();

            TrayNotification tray = new TrayNotification();
            tray.setTitle("Sukces!");
            tray.setMessage("Dodawanie sali przebiegło pomyślnie.");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(1500));


            AnchorPane home_parent1 = (AnchorPane) fDodajSale.getParent().getParent().getParent();        
            home_parent1.getChildren().clear();
            home_parent1.getChildren().add(dodaj);
        } else {
            AudioClip powiadomMnie = new AudioClip((SzkolaJezykowa.class.getResource("audio/Notify.wav")).toString());
            powiadomMnie.play();

            TrayNotification tray = new TrayNotification();
            tray.setTitle("Błąd!");
            tray.setMessage("Dodawanie sali nie powiodło się.");
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.millis(1500));
        }
        
    }

    @FXML
    private void anuluj(ActionEvent event) throws IOException {
        AnchorPane anuluj = FXMLLoader.load(getClass().getResource("SaleView.fxml"));        
        
        AnchorPane home_parent2 = (AnchorPane) fAnuluj.getParent().getParent().getParent();        
        home_parent2.getChildren().clear();
        home_parent2.getChildren().add(anuluj);
    }
    
}
