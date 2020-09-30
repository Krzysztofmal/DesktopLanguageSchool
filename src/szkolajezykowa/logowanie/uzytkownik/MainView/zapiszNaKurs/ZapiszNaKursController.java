/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package szkolajezykowa.logowanie.uzytkownik.MainView.zapiszNaKurs;

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
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;
import szkolajezykowa.SzkolaJezykowa;
import szkolajezykowa.details.KursantZapiszSieNaKursDetails;
import szkolajezykowa.logowanie.LoginViewController;
import szkolajezykowa.polaczenie.Polaczenie;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author BlackHawk
 */
public class ZapiszNaKursController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TableView<KursantZapiszSieNaKursDetails> tableZapiszNaKurs;
    @FXML
    private TableColumn<KursantZapiszSieNaKursDetails, String> columnNazwaKursu;
    @FXML
    private TableColumn<KursantZapiszSieNaKursDetails, String> columnJezyk;
    @FXML
    private TableColumn<KursantZapiszSieNaKursDetails, String> columnPoziom;
    @FXML
    private TableColumn<KursantZapiszSieNaKursDetails, Integer> columnIloscGodzin;
    @FXML
    private TableColumn<KursantZapiszSieNaKursDetails, Double> columnOtrzymanyRabat;
    @FXML
    private TableColumn<KursantZapiszSieNaKursDetails, Double> columnCena;
    @FXML
    private JFXButton zapiszBtn;

    private ObservableList<KursantZapiszSieNaKursDetails> dataTwojeKursy;
    @FXML
    private TableColumn<KursantZapiszSieNaKursDetails, Integer> columnWolneMiejsca;
    private Double cenaZaKurs;

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
            PreparedStatement ps = con.prepareStatement("SELECT id_kursu,nazwa_kursu,jezyk,poziom,ilosc_godzin,otrzymany_rabat"
                    + "(" + LoginViewController.r + ",id_kursu) as rabat,oblicz_cene_znizki(" + LoginViewController.r + ",id_kursu) as cena, "
                    + "policz_wolne_miejsca(id_kursu) as wolne FROM kurs WHERE id_kursu not in (SELECT kurs.id_kursu FROM kursanci,kurs,kurskursanci WHERE "
                    + "kursanci.id_kursanta=kurskursanci.id_kursanta AND kurskursanci.id_kursanta=" + LoginViewController.r + " "
                    + "AND kurskursanci.id_kursu=kurs.id_kursu)");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                if (rs.getInt("wolne") > 0) {
                    dataTwojeKursy.add(new KursantZapiszSieNaKursDetails(rs.getInt("id_kursu"), rs.getString("nazwa_kursu"), rs.getString("jezyk"), rs.getString("poziom"),
                            rs.getInt("ilosc_godzin"), rs.getDouble("rabat"), rs.getDouble("cena"), rs.getInt("wolne")));
                }
            }

            con.close();
            ps.close();
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(ZapiszNaKursController.class.getName()).log(Level.SEVERE, null, ex);
        }

        columnNazwaKursu.setCellValueFactory(new PropertyValueFactory<>("nazwa_kursu"));
        columnJezyk.setCellValueFactory(new PropertyValueFactory<>("jezyk"));
        columnPoziom.setCellValueFactory(new PropertyValueFactory<>("poziom"));
        columnIloscGodzin.setCellValueFactory(new PropertyValueFactory<>("ilosc_godzin"));
        columnOtrzymanyRabat.setCellValueFactory(new PropertyValueFactory<>("rabat"));
        columnCena.setCellValueFactory(new PropertyValueFactory<>("cena"));
        columnWolneMiejsca.setCellValueFactory(new PropertyValueFactory<>("wolne"));

        tableZapiszNaKurs.setItems(null);
        tableZapiszNaKurs.setItems(dataTwojeKursy);
    }
    
    
    
    

    @FXML
    private void zapiszNaKurs(ActionEvent event) throws IOException, SQLException {

        Integer idKursanta = Integer.parseInt(LoginViewController.r);
        Integer idKursu = tableZapiszNaKurs.getSelectionModel().getSelectedItem().getId_kursu();
        cenaZaKurs = tableZapiszNaKurs.getSelectionModel().getSelectedItem().getCena();
        System.out.println(cenaZaKurs);
        Connection con = Polaczenie.ConnectDB();
        String query = "{CALL KRZYSZTOF.kursant_zapisz_na_kurs(?,?,?)}";
        CallableStatement cst = con.prepareCall(query);
        cst.setInt(1, idKursu);
        cst.setInt(2, idKursanta);
        cst.setDouble(3, cenaZaKurs);
        cst.execute();
        cst.close();
        con.close();

        AudioClip powiadomMnie = new AudioClip((SzkolaJezykowa.class.getResource("audio/Notify.wav")).toString());
        powiadomMnie.play();

        TrayNotification tray = new TrayNotification();
        tray.setTitle("Sukces!");
        tray.setMessage("Zostałeś zapisany na kurs.");
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(1500));        
        odswiez();
        
        
        /*
        AnchorPane twojeKursy = FXMLLoader.load(SzkolaJezykowa.class.getResource("logowanie/uzytkownik/MainView/zapiszNaKurs/ZapiszNaKurs.fxml"));
        AnchorPane home_parent2 = (AnchorPane) zapiszBtn.getParent().getParent().getParent();

        home_parent2.getChildren().clear();
        home_parent2.getChildren().add(twojeKursy);*/

    }

}
