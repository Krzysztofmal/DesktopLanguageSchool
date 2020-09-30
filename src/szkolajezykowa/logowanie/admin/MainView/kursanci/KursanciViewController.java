/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package szkolajezykowa.logowanie.admin.MainView.kursanci;

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
import szkolajezykowa.details.KursanciDetails;
import szkolajezykowa.details.SaleDetails;
import szkolajezykowa.polaczenie.Polaczenie;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author BlackHawk
 */
public class KursanciViewController implements Initializable {

    @FXML
    private TableView<KursanciDetails> tableKursanci;
    @FXML
    private TableColumn<KursanciDetails, Integer> columnImie;
    @FXML
    private TableColumn<KursanciDetails, String> columnNazwisko;
    @FXML
    private TableColumn<KursanciDetails, String> columnPesel;
    @FXML
    private TableColumn<KursanciDetails, String> columnTelefon;
    @FXML
    private TableColumn<KursanciDetails, String> columnEmail;
    @FXML
    private TableColumn<KursanciDetails, String> columnLogin;
    @FXML
    private TableColumn<KursanciDetails, String> columnPlec;
    @FXML
    private TableColumn<KursanciDetails, String> columnStatus;
    @FXML
    private JFXButton dodajKursantaBtn;
    @FXML
    private JFXButton edytujKursantaBtn;
    @FXML
    private JFXButton usunKursantaBtn;
    private ObservableList<KursanciDetails> dataKursanci;
    public static Integer idKursanta;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            edytujKursantaBtn.setDisable(true);
            usunKursantaBtn.setDisable(true);
            
            dataKursanci = FXCollections.observableArrayList();
            
            Connection con = Polaczenie.ConnectDB();
            PreparedStatement ps = con.prepareStatement("SELECT id_kursanta,imie,nazwisko,pesel,nr_tel,email,login,haslo,plec,status FROM kursanci");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){
                dataKursanci.add(new KursanciDetails(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10)));
            }
            
            con.close();
            ps.close();
            rs.close();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(KursanciViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        columnImie.setCellValueFactory(new PropertyValueFactory<>("imie"));
        columnNazwisko.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
        columnPesel.setCellValueFactory(new PropertyValueFactory<>("pesel"));
        columnTelefon.setCellValueFactory(new PropertyValueFactory<>("nr_tel"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        columnLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
        columnPlec.setCellValueFactory(new PropertyValueFactory<>("plec"));
        columnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        
        tableKursanci.setItems(null);
        tableKursanci.setItems(dataKursanci);
        
    }    

    @FXML
    private void zaznacz(MouseEvent event) {
        if (tableKursanci.getSelectionModel().getSelectedItem() != null) {
            edytujKursantaBtn.setDisable(false);
            usunKursantaBtn.setDisable(false);
        }
    }

    @FXML
    private void dodajKursanta(ActionEvent event) {
        AnchorPane dodaj = null;
        try {            
             dodaj = FXMLLoader.load(getClass().getResource("DodajKursantaView.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(KursanciViewController.class.getName()).log(Level.SEVERE, null, ex);
        }        

                

        AnchorPane home_parent3 = (AnchorPane) dodajKursantaBtn.getParent().getParent().getParent();        
        home_parent3.getChildren().clear();
        home_parent3.getChildren().add(dodaj);
    }

    @FXML
    private void edytujKursanta(ActionEvent event) throws IOException {
        if (tableKursanci.getSelectionModel().getSelectedItem() != null) {
            idKursanta = tableKursanci.getSelectionModel().getSelectedItem().getId_kursanta();         
            
        }
        AnchorPane edytuj = FXMLLoader.load(getClass().getResource("EdytujKursantaView.fxml"));
        AnchorPane home_parent3 = (AnchorPane) edytujKursantaBtn.getParent().getParent().getParent();        
        home_parent3.getChildren().clear();
        home_parent3.getChildren().add(edytuj);
    }

    @FXML
    private void usunKursanta(ActionEvent event) throws SQLException {
         String query = "{CALL KRZYSZTOF.usun_kursanta(?)}";
        Connection con = Polaczenie.ConnectDB();
        CallableStatement cst = con.prepareCall(query);
        cst.setInt(1, tableKursanci.getSelectionModel().getSelectedItem().getId_kursanta());

            cst.execute();
            cst.close();
            con.close();

            AudioClip powiadomMnie = new AudioClip((SzkolaJezykowa.class.getResource("audio/Notify.wav")).toString());
            powiadomMnie.play();

            TrayNotification tray = new TrayNotification();
            tray.setTitle("Sukces!");
            tray.setMessage("Kursant został usunięty");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(1500));
    }
    
}
