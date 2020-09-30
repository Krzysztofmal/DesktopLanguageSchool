/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package szkolajezykowa.logowanie.wykladowca.MainView.kursy;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import szkolajezykowa.details.KursantTwojeKursyDetails;
import szkolajezykowa.details.SaleDetails;
import szkolajezykowa.details.WykladowcaKursyDetails;
import szkolajezykowa.logowanie.LoginViewController;
import szkolajezykowa.logowanie.admin.MainView.sale.SaleViewController;
import szkolajezykowa.polaczenie.Polaczenie;

/**
 * FXML Controller class
 *
 * @author BlackHawk
 */
public class KursyViewController implements Initializable {

    @FXML
    private TableView<WykladowcaKursyDetails> tableKursy;
    @FXML
    private TableColumn<WykladowcaKursyDetails, String> columnNazwaKursu;
    @FXML
    private TableColumn<WykladowcaKursyDetails, Integer> columnGodziny;
    @FXML
    private TableColumn<WykladowcaKursyDetails, String> columnPoziom;
    @FXML
    private TableColumn<WykladowcaKursyDetails, String> columnJezyk;
    @FXML
    private TableColumn<WykladowcaKursyDetails, Integer> columnSala;
    private ObservableList<WykladowcaKursyDetails> dataKursy;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        dataKursy = FXCollections.observableArrayList();
        
        
        try {
            Connection con = Polaczenie.ConnectDB();
            PreparedStatement ps = con.prepareStatement("SELECT distinct kurs.nazwa_kursu,kurs.jezyk,kurs.poziom,kurs.ilosc_godzin,sala.nr_sali FROM sala,kurs,wykladowcy WHERE kurs.id_sali=sala.id_sali AND kurs.id_wykladowcy ="+LoginViewController.r);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                dataKursy.add(new WykladowcaKursyDetails(rs.getString(1), rs.getString(2), rs.getString(3),rs.getInt(4),rs.getInt(5)));
            }
            con.close();
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(KursyViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        columnNazwaKursu.setCellValueFactory(new PropertyValueFactory<>("nazwa_kursu"));
        columnGodziny.setCellValueFactory(new PropertyValueFactory<>("ilosc_godzin"));
        columnJezyk.setCellValueFactory(new PropertyValueFactory<>("jezyk"));
        columnSala.setCellValueFactory(new PropertyValueFactory<>("nr_sali"));
        columnPoziom.setCellValueFactory(new PropertyValueFactory<>("poziom"));
        
        
        tableKursy.setItems(null);
        tableKursy.setItems(dataKursy);
        
        
    }    
    
}
