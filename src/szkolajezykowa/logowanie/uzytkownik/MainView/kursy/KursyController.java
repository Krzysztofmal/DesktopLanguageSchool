/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package szkolajezykowa.logowanie.uzytkownik.MainView.kursy;

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
import szkolajezykowa.logowanie.LoginViewController;
import szkolajezykowa.polaczenie.Polaczenie;

/**
 * FXML Controller class
 *
 * @author BlackHawk
 */
public class KursyController implements Initializable {

    @FXML
    private TableView<KursantTwojeKursyDetails> tableTwojeKursy;
    @FXML
    private TableColumn<KursantTwojeKursyDetails, String> columnNazwaKursu;
    @FXML
    private TableColumn<KursantTwojeKursyDetails, String> columnProwadzacy;
    @FXML
    private TableColumn<KursantTwojeKursyDetails, String> columnJezyk;
    @FXML
    private TableColumn<KursantTwojeKursyDetails, String> columnPoziom;
    @FXML
    private TableColumn<KursantTwojeKursyDetails, Integer> columnIloscGodzin;
    @FXML
    private TableColumn<KursantTwojeKursyDetails, Double> columnCena;
    
    private ObservableList<KursantTwojeKursyDetails> dataTwojeKursy;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        odswiez();
    }    
    
    private void odswiez(){
        
        dataTwojeKursy = FXCollections.observableArrayList();
        
         try {
            Connection con = Polaczenie.ConnectDB();            
            PreparedStatement ps = con.prepareStatement("SELECT kurs.id_kursu,kurs.nazwa_kursu, kurs.jezyk, kurs.poziom, kurs.ilosc_godzin, "
                    + "(wykladowcy.imie || ' ' || wykladowcy.nazwisko) as prowadzacy,kurskursanci.cena "
                    + "FROM kurs,wykladowcy,kurskursanci,kursanci WHERE kurs.id_wykladowcy=wykladowcy.id_wykladowcy AND kurs.id_kursu=kurskursanci.id_kursu AND "
                    + "kursanci.id_kursanta=kurskursanci.id_kursanta AND kursanci.id_kursanta=" + LoginViewController.r);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){
                dataTwojeKursy.add(new KursantTwojeKursyDetails(rs.getInt("id_kursu"), rs.getString("nazwa_kursu"), rs.getString("jezyk"), rs.getString("poziom"), 
                        rs.getInt("ilosc_godzin"), rs.getString("prowadzacy"), rs.getDouble("cena") ));
            }
            
            con.close();
            ps.close();
            rs.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(KursyController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        columnNazwaKursu.setCellValueFactory(new PropertyValueFactory<>("nazwa_kursu"));
        columnJezyk.setCellValueFactory(new PropertyValueFactory<>("jezyk"));
        columnPoziom.setCellValueFactory(new PropertyValueFactory<>("poziom"));
        columnIloscGodzin.setCellValueFactory(new PropertyValueFactory<>("ilosc_godzin"));
        columnProwadzacy.setCellValueFactory(new PropertyValueFactory<>("prowadzacy"));
        columnCena.setCellValueFactory(new PropertyValueFactory<>("cena"));
         
        tableTwojeKursy.setItems(null);
        tableTwojeKursy.setItems(dataTwojeKursy);
        
        
        

    }
    
}
