/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package szkolajezykowa.logowanie.admin.MainView.kursy;

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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import szkolajezykowa.SzkolaJezykowa;
import szkolajezykowa.details.DodajKursDetails;
import szkolajezykowa.logowanie.admin.MainView.MainAdminViewController;
import szkolajezykowa.polaczenie.Polaczenie;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author BlackHawk
 */
public class KursyViewController implements Initializable {

    @FXML
    private TableView<DodajKursDetails> tableKursy;
    @FXML
    private TableColumn<DodajKursDetails, String> columnNazwa;
    @FXML
    private TableColumn<DodajKursDetails, String> columnPoziom;
    @FXML
    private TableColumn<DodajKursDetails, String> columnJezyk;
    @FXML
    private TableColumn<DodajKursDetails, Integer> columnGodziny;
    @FXML
    private TableColumn<DodajKursDetails, String> columnProwadzacy;
    @FXML
    private TableColumn<DodajKursDetails, Integer> columnSala;
    @FXML
    private TableColumn<DodajKursDetails, Integer> columnMiejsca;
    @FXML
    private TableColumn<DodajKursDetails, Double> columnCena;
    @FXML
    private JFXButton dodajKursBtn;
    @FXML
    private JFXButton edytujKursBtn;
    @FXML
    private JFXButton usunKursBtn;

    private ObservableList<DodajKursDetails> dataKursy;
    private AnchorPane dodaj, edytuj;
    public static Integer idKursu;
    public static Integer idSali;
    public static Integer idWykladowcy;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        edytujKursBtn.setDisable(true);
        usunKursBtn.setDisable(true);

        dataKursy = FXCollections.observableArrayList();

        try {
            Connection con = Polaczenie.ConnectDB();
            PreparedStatement ps = con.prepareStatement("SELECT kurs.id_kursu,kurs.nazwa_kursu,kurs.poziom,kurs.jezyk,kurs.ilosc_godzin,"
                    + "(wykladowcy.imie || ' ' ||wykladowcy.nazwisko) as prowadzacy,sala.nr_sali,policz_wolne_miejsca(kurs.id_kursu) as miejsca,"
                    + "oblicz_cene(kurs.id_kursu) as cena FROM kurs,wykladowcy,sala WHERE sala.id_sali=kurs.id_sali AND "
                    + "wykladowcy.id_wykladowcy=kurs.id_wykladowcy");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                dataKursy.add(new DodajKursDetails(rs.getInt("id_kursu"), rs.getString("nazwa_kursu"), rs.getString("poziom"), rs.getString("jezyk"),
                        rs.getInt("ilosc_godzin"), rs.getString("prowadzacy"), rs.getInt("nr_sali"), rs.getInt("miejsca"), rs.getDouble("cena")));
            }

            con.close();
            ps.close();
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(KursyViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

        columnNazwa.setCellValueFactory(new PropertyValueFactory<>("nazwa_kursu"));
        columnPoziom.setCellValueFactory(new PropertyValueFactory<>("poziom"));
        columnJezyk.setCellValueFactory(new PropertyValueFactory<>("jezyk"));
        columnGodziny.setCellValueFactory(new PropertyValueFactory<>("ilosc_godzin"));
        columnProwadzacy.setCellValueFactory(new PropertyValueFactory<>("prowadzacy"));
        columnSala.setCellValueFactory(new PropertyValueFactory<>("nr_sali"));
        columnMiejsca.setCellValueFactory(new PropertyValueFactory<>("miejsca"));
        columnCena.setCellValueFactory(new PropertyValueFactory<>("cena"));

        tableKursy.setItems(null);
        tableKursy.setItems(dataKursy);
    }

    @FXML
    private void dodajKurs(ActionEvent event) throws IOException {

        try {
            dodaj = FXMLLoader.load(getClass().getResource("DodajKursView.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(KursyViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

        AnchorPane home_parent2 = (AnchorPane) dodajKursBtn.getParent().getParent().getParent();
        //System.out.println(home_parent2.getChildren().size());
        //System.out.println(home_parent2.getChildren().get(0));
        home_parent2.getChildren().clear();
        home_parent2.getChildren().add(dodaj);

    }

    @FXML
    private void edytujKurs(ActionEvent event) throws SQLException {
        try {            
            edytuj = FXMLLoader.load(getClass().getResource("EdytujKursView.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(KursyViewController.class.getName()).log(Level.SEVERE, null, ex);
        }        
        
        if (tableKursy.getSelectionModel().getSelectedItem() != null){
            idKursu = tableKursy.getSelectionModel().getSelectedItem().getId_kursu();
            Connection con = Polaczenie.ConnectDB();
            PreparedStatement ps = con.prepareStatement("SELECT kurs.id_sali,kurs.id_wykladowcy FROM kurs WHERE kurs.id_kursu="+idKursu);
            ResultSet rs = ps.executeQuery();
            rs.next();
            idWykladowcy = rs.getInt("id_wykladowcy");
            idSali = rs.getInt("id_sali");
            
            
            con.close();
            rs.close();
            ps.close();
        }

        AnchorPane home_parent3 = (AnchorPane) edytujKursBtn.getParent().getParent().getParent();        
        home_parent3.getChildren().clear();
        home_parent3.getChildren().add(edytuj);
    }

    @FXML
    private void usunKurs(ActionEvent event) throws SQLException {
         String query = "{CALL KRZYSZTOF.usun_kurs(?)}";
        Connection con = Polaczenie.ConnectDB();
        CallableStatement cst = con.prepareCall(query);
        cst.setInt(1, tableKursy.getSelectionModel().getSelectedItem().getId_kursu());

            cst.execute();
            cst.close();
            con.close();

            AudioClip powiadomMnie = new AudioClip((SzkolaJezykowa.class.getResource("audio/Notify.wav")).toString());
            powiadomMnie.play();

            TrayNotification tray = new TrayNotification();
            tray.setTitle("Sukces!");
            tray.setMessage("Kurs został usunięty");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(1500));
    }

    @FXML
    private void zaznacz(MouseEvent event) throws SQLException {
        if (tableKursy.getSelectionModel().getSelectedItem() != null) {
            edytujKursBtn.setDisable(false);
            usunKursBtn.setDisable(false);
            idKursu = tableKursy.getSelectionModel().getSelectedItem().getId_kursu();  
            

            
            
        }
    }

}
