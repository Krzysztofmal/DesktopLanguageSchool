/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package szkolajezykowa.logowanie.admin.MainView.zaliczenia;

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
import szkolajezykowa.details.AdminZaliczeniaDetails;
import szkolajezykowa.details.SaleDetails;
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
    private JFXButton dodajZaliczenieBtn;
    @FXML
    private JFXButton edytujZaliczenieBtn;
    @FXML
    private JFXButton usunZaliczenieBtn;
    @FXML
    private TableView<AdminZaliczeniaDetails> tableZaliczenia;
    @FXML
    private TableColumn<AdminZaliczeniaDetails, String> columnNazwaKursu;
    @FXML
    private TableColumn<AdminZaliczeniaDetails, String> columnJezyk;
    @FXML
    private TableColumn<AdminZaliczeniaDetails, String> columnPoziom;
    @FXML
    private TableColumn<AdminZaliczeniaDetails, String> columnProwadzacy;
    @FXML
    private TableColumn<AdminZaliczeniaDetails, String> columnKursant;
    @FXML
    private TableColumn<AdminZaliczeniaDetails, String> columnData;
    @FXML
    private TableColumn<AdminZaliczeniaDetails, String> columnTyp;
    @FXML
    private TableColumn<AdminZaliczeniaDetails, String> columnWynik;

    private ObservableList<AdminZaliczeniaDetails> dataZaliczenia;
    public static Integer idZaliczenia,idKursu,idWykladowcy,idKursanta;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            dataZaliczenia = FXCollections.observableArrayList();
            edytujZaliczenieBtn.setDisable(true);
            usunZaliczenieBtn.setDisable(true);

            Connection con = Polaczenie.ConnectDB();
            PreparedStatement ps = con.prepareStatement("SELECT zaliczenia.id_zaliczenia,kurs.nazwa_kursu,wykladowcy.imie||' '||wykladowcy.nazwisko as prowadzacy,kurs.jezyk,kurs.poziom,zaliczenia.data_zal,zaliczenia.typ,zaliczenia.wynik,\n"
                    + "kurs.id_kursu,kursanci.id_kursanta,kursanci.imie||' '||kursanci.nazwisko as kursant,wykladowcy.id_wykladowcy FROM kursanci,wykladowcy,kurs,zaliczenia WHERE \n"
                    + "zaliczenia.id_kursu=kurs.id_kursu AND kurs.id_wykladowcy=wykladowcy.id_wykladowcy AND zaliczenia.id_kursanta=kursanci.id_kursanta");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){
                dataZaliczenia.add(new AdminZaliczeniaDetails(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getInt(9),rs.getInt(10),rs.getString(11),rs.getInt(12)));
            }
            

        } catch (SQLException ex) {
            Logger.getLogger(ZaliczeniaViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        columnNazwaKursu.setCellValueFactory(new PropertyValueFactory<>("nazwa_kursu"));
        columnJezyk.setCellValueFactory(new PropertyValueFactory<>("jezyk"));
        columnPoziom.setCellValueFactory(new PropertyValueFactory<>("poziom"));
        columnProwadzacy.setCellValueFactory(new PropertyValueFactory<>("prowadzacy"));
        columnKursant.setCellValueFactory(new PropertyValueFactory<>("kursant"));
        columnData.setCellValueFactory(new PropertyValueFactory<>("data_zal"));
        columnTyp.setCellValueFactory(new PropertyValueFactory<>("typ"));
        columnWynik.setCellValueFactory(new PropertyValueFactory<>("wynik"));        
        
        
        tableZaliczenia.setItems(null);
        tableZaliczenia.setItems(dataZaliczenia);

    }

    @FXML
    private void zaznacz(MouseEvent event) {
        if (tableZaliczenia.getSelectionModel().getSelectedItem() != null) {
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
        if (tableZaliczenia.getSelectionModel().getSelectedItem() != null) {
            idZaliczenia = tableZaliczenia.getSelectionModel().getSelectedItem().getId_zaliczenia();
            idKursu = tableZaliczenia.getSelectionModel().getSelectedItem().getId_kursu();
            idWykladowcy = tableZaliczenia.getSelectionModel().getSelectedItem().getId_wykladowcy();
            idKursanta = tableZaliczenia.getSelectionModel().getSelectedItem().getId_kursanta();
            AnchorPane edytuj = FXMLLoader.load(getClass().getResource("EdytujZaliczenieView.fxml"));
        AnchorPane home_parent2 = (AnchorPane) edytujZaliczenieBtn.getParent().getParent().getParent();
        home_parent2.getChildren().clear();
        home_parent2.getChildren().add(edytuj);
            
        }
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
    }

}
