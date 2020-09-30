/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package szkolajezykowa.logowanie.admin.MainView.sale;

import com.jfoenix.controls.JFXButton;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;
import szkolajezykowa.SzkolaJezykowa;
import szkolajezykowa.details.SaleDetails;
import szkolajezykowa.logowanie.admin.MainView.kursy.KursyViewController;
import static szkolajezykowa.logowanie.admin.MainView.kursy.KursyViewController.idKursu;
import szkolajezykowa.polaczenie.Polaczenie;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author BlackHawk
 */
public class SaleViewController implements Initializable {

    
    private AnchorPane dodaj;
    @FXML
    private TableView<SaleDetails> tableSale;
    @FXML
    private TableColumn<SaleDetails, Integer> nrSaliColumn;
    @FXML
    private TableColumn<SaleDetails, Integer> iloscMiejscColumn;
    
    private ObservableList<SaleDetails> dataSale;
    @FXML
    private JFXButton dodajSaleBtn;
    @FXML
    private JFXButton edytujSaleBtn;
    @FXML
    private JFXButton usunSaleBtn;
    
    public static Integer idSali;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dataSale = FXCollections.observableArrayList();
        edytujSaleBtn.setDisable(true);
        usunSaleBtn.setDisable(true);
        
        try {
            Connection con = Polaczenie.ConnectDB();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM sala");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                dataSale.add(new SaleDetails(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
            }
            con.close();
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(SaleViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //nrSaliColumn.setStyle( "-fx-alignment: CENTER;");
        //iloscMiejscColumn.setStyle( "-fx-alignment: CENTER;");
        
        nrSaliColumn.setCellValueFactory(new PropertyValueFactory<>("nr_sali"));
        iloscMiejscColumn.setCellValueFactory(new PropertyValueFactory<>("ilosc_miejsc"));
        tableSale.setItems(null);
        tableSale.setItems(dataSale);
        
    }    

    @FXML
    private void dodajSale(ActionEvent event) {
        try { 
             dodaj = FXMLLoader.load(getClass().getResource("DodajSale.fxml"));        
        } catch (IOException ex) {
            Logger.getLogger(SaleViewController.class.getName()).log(Level.SEVERE, null, ex);
        } 
             
        AnchorPane home_parent1 = (AnchorPane) dodajSaleBtn.getParent().getParent();        
        home_parent1.getChildren().clear();
        home_parent1.getChildren().add(dodaj);
        
        
        
        
    }

    @FXML
    private void edytujSale(ActionEvent event) {
        if (tableSale.getSelectionModel().getSelectedItem() != null) {
            idSali = tableSale.getSelectionModel().getSelectedItem().getId_sali();            
        }
        try { 
             dodaj = FXMLLoader.load(getClass().getResource("EdytujSale.fxml"));        
        } catch (IOException ex) {
            Logger.getLogger(SaleViewController.class.getName()).log(Level.SEVERE, null, ex);
        } 
             
        AnchorPane home_parent1 = (AnchorPane) edytujSaleBtn.getParent().getParent();        
        home_parent1.getChildren().clear();
        home_parent1.getChildren().add(dodaj);
    }

    @FXML
    private void usunSale(ActionEvent event) throws SQLException {
         String query = "{CALL KRZYSZTOF.usun_sale(?)}";
        Connection con = Polaczenie.ConnectDB();
        CallableStatement cst = con.prepareCall(query);
        cst.setInt(1, tableSale.getSelectionModel().getSelectedItem().getId_sali());

            cst.execute();
            cst.close();
            con.close();

            AudioClip powiadomMnie = new AudioClip((SzkolaJezykowa.class.getResource("audio/Notify.wav")).toString());
            powiadomMnie.play();

            TrayNotification tray = new TrayNotification();
            tray.setTitle("Sukces!");
            tray.setMessage("Sala została usunięta");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(1500));
    }

    @FXML
    private void zaznacz(MouseEvent event) {
        if (tableSale.getSelectionModel().getSelectedItem() != null) {
            edytujSaleBtn.setDisable(false);
            usunSaleBtn.setDisable(false);
            idSali = tableSale.getSelectionModel().getSelectedItem().getId_sali();  
        }
        
        
    }
    
}
