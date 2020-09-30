/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package szkolajezykowa.logowanie.wykladowca.MainView.zaliczenia;

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
import szkolajezykowa.details.WykladowcaZaliczenia;
import szkolajezykowa.logowanie.LoginViewController;
import szkolajezykowa.polaczenie.Polaczenie;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author BlackHawk
 */
public class ZaliczeniaViewController implements Initializable {

    @FXML
    private TableView<WykladowcaZaliczenia> tableZaliczenia;
    @FXML
    private TableColumn<WykladowcaZaliczenia, String> columnKurs;
    @FXML
    private TableColumn<WykladowcaZaliczenia, String> columnKursant;
    @FXML
    private TableColumn<WykladowcaZaliczenia, String> columnJezyk;
    @FXML
    private TableColumn<WykladowcaZaliczenia, String> columnPoziom;
    @FXML
    private TableColumn<WykladowcaZaliczenia, String> columnData;
    @FXML
    private TableColumn<WykladowcaZaliczenia, String> columnTyp;
    @FXML
    private TableColumn<WykladowcaZaliczenia, Integer> columnWynik;
    @FXML
    private JFXButton dodajZaliczenieBtn;
    @FXML
    private JFXButton edytujZaliczenieBtn;
    @FXML
    private JFXButton usunZaliczenieBtn;
    private ObservableList<WykladowcaZaliczenia> dataKursy;
    public static Integer idKursu,idKursanta,idZaliczenia;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        edytujZaliczenieBtn.setDisable(true);
        usunZaliczenieBtn.setDisable(true);
        
        dataKursy = FXCollections.observableArrayList();
        
        try {
            Connection con = Polaczenie.ConnectDB();
            PreparedStatement ps = con.prepareStatement("SELECT distinct kurs.id_kursu,kursanci.id_kursanta,zaliczenia.id_zaliczenia,kurs.nazwa_kursu,kursanci.imie||' '||kursanci.nazwisko as kursant,kurs.jezyk,kurs.poziom,zaliczenia.data_zal,zaliczenia.typ,zaliczenia.wynik FROM kurs,kursanci,zaliczenia,kurskursanci " +
"WHERE kurs.id_wykladowcy="+LoginViewController.r+" AND kurs.id_kursu=kurskursanci.id_kursu AND kurskursanci.id_kursanta=kursanci.id_kursanta AND kurs.id_kursu=zaliczenia.id_kursu AND zaliczenia.id_kursanta=kursanci.id_kursanta");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                dataKursy.add(new WykladowcaZaliczenia(rs.getInt("id_zaliczenia"), rs.getString("nazwa_kursu"), rs.getString("jezyk"), rs.getString("poziom"),
                        rs.getString("data_zal"), rs.getString("typ"), rs.getInt("wynik"), rs.getInt("id_kursu"), rs.getInt("id_kursanta"),rs.getString("kursant")));
            }

            con.close();
            ps.close();
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(ZaliczeniaViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        columnData.setCellValueFactory(new PropertyValueFactory<>("data_zal"));
        columnJezyk.setCellValueFactory(new PropertyValueFactory<>("jezyk"));
        columnKurs.setCellValueFactory(new PropertyValueFactory<>("nazwa_kursu"));
        columnKursant.setCellValueFactory(new PropertyValueFactory<>("kursant"));
        columnPoziom.setCellValueFactory(new PropertyValueFactory<>("poziom"));
        columnTyp.setCellValueFactory(new PropertyValueFactory<>("typ"));
        columnWynik.setCellValueFactory(new PropertyValueFactory<>("wynik"));
        
        
        tableZaliczenia.setItems(null);
        tableZaliczenia.setItems(dataKursy);
        
        
    }    

    @FXML
    private void zaznacz(MouseEvent event) {
        if (tableZaliczenia.getSelectionModel().getSelectedItem() != null){
            edytujZaliczenieBtn.setDisable(false);
            usunZaliczenieBtn.setDisable(false);
            
        }
    }

    @FXML
    private void dodajZaliczenie(ActionEvent event) throws IOException {
        AnchorPane dodaj = FXMLLoader.load(getClass().getResource("DodajZaliczenieView.fxml"));
        AnchorPane home_parent2 = (AnchorPane) dodajZaliczenieBtn.getParent().getParent().getParent();
        home_parent2.getChildren().clear();
        home_parent2.getChildren().add(dodaj);
    }

    @FXML
    private void edytujZaliczenie(ActionEvent event) throws IOException {
        idZaliczenia = tableZaliczenia.getSelectionModel().getSelectedItem().getId_zaliczenia();
        idKursanta = tableZaliczenia.getSelectionModel().getSelectedItem().getId_kursanta();
        idKursu = tableZaliczenia.getSelectionModel().getSelectedItem().getId_kursu();
        AnchorPane edytuj = FXMLLoader.load(getClass().getResource("EdytujZaliczenieView.fxml"));
        AnchorPane home_parent2 = (AnchorPane) edytujZaliczenieBtn.getParent().getParent().getParent();
        home_parent2.getChildren().clear();
        home_parent2.getChildren().add(edytuj);
    }

    @FXML
    private void usunZaliczenie(ActionEvent event) throws SQLException {
        String query = "{CALL KRZYSZTOF.usun_zaliczenie(?)}";
        Connection con = Polaczenie.ConnectDB();
        CallableStatement cst = con.prepareCall(query);
        cst.setInt(1, tableZaliczenia.getSelectionModel().getSelectedItem().getId_zaliczenia());

            cst.execute();
            cst.close();
            con.close();

            AudioClip powiadomMnie = new AudioClip((SzkolaJezykowa.class.getResource("audio/Notify.wav")).toString());
            powiadomMnie.play();

            TrayNotification tray = new TrayNotification();
            tray.setTitle("Sukces!");
            tray.setMessage("Zaliczenie zostało usunięte");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(1500));
            
            //odswiezyc 
    }
    
}
