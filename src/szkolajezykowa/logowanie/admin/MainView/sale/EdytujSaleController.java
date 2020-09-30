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
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class EdytujSaleController implements Initializable {

    @FXML
    private JFXTextField fNrSali;
    @FXML
    private JFXTextField fPojemnosc;
    @FXML
    private JFXButton fZapiszSale;
    @FXML
    private JFXButton fAnuluj;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Connection con = Polaczenie.ConnectDB();
            PreparedStatement ps = con.prepareStatement("SELECT id_sali,nr_sali,ilosc_miejsc FROM sala WHERE id_sali="+SaleViewController.idSali);
            ResultSet rs = ps.executeQuery();
            
            rs.next();
            fNrSali.setText(rs.getString("nr_sali"));
            fPojemnosc.setText(rs.getString("ilosc_miejsc"));
            
            con.close();
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(EdytujSaleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void zapiszSale(ActionEvent event) throws IOException, SQLException {
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
            String query = "{CALL KRZYSZTOF.update_sala(?,?,?)}";
            CallableStatement cst = con.prepareCall(query);

            Integer nr = Integer.parseInt(fNrSali.getText());
            Integer poj = Integer.parseInt(fPojemnosc.getText());
            //System.out.println(nr + " " + poj);

            cst.setInt(1, SaleViewController.idSali); //id 
            cst.setInt(2, nr);
            cst.setInt(3, poj);

            cst.execute();
            con.close();
            cst.close();
            
            AudioClip powiadomMnie = new AudioClip((SzkolaJezykowa.class.getResource("audio/Notify.wav")).toString());
            powiadomMnie.play();

            TrayNotification tray = new TrayNotification();
            tray.setTitle("Sukces!");
            tray.setMessage("Edycja sali przebiegła pomyślnie.");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(1500));


            AnchorPane home_parent1 = (AnchorPane) fZapiszSale.getParent().getParent().getParent();        
            home_parent1.getChildren().clear();
            home_parent1.getChildren().add(dodaj);
        } else {
            AudioClip powiadomMnie = new AudioClip((SzkolaJezykowa.class.getResource("audio/Notify.wav")).toString());
            powiadomMnie.play();

            TrayNotification tray = new TrayNotification();
            tray.setTitle("Błąd!");
            tray.setMessage("Aktualizacja sali nie powiodła się.");
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
