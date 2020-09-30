/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package szkolajezykowa.logowanie.admin.MainView.wykladowcy;

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
import szkolajezykowa.details.WykladowcyDetails2;
import szkolajezykowa.logowanie.LoginViewController;
import szkolajezykowa.polaczenie.Polaczenie;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author BlackHawk
 */
public class WykladowcyViewController implements Initializable {

    @FXML
    private TableView<WykladowcyDetails2> tableWykladowcy;
    @FXML
    private TableColumn<WykladowcyDetails2, String> columnImie;
    @FXML
    private TableColumn<WykladowcyDetails2, String> columnNazwisko;
    @FXML
    private TableColumn<WykladowcyDetails2, String> columnEmail;
    @FXML
    private TableColumn<WykladowcyDetails2, String> columnTelefon;
    @FXML
    private TableColumn<WykladowcyDetails2, String> columnJezyk;
    @FXML
    private TableColumn<WykladowcyDetails2, String> columnLogin;
    @FXML
    private TableColumn<WykladowcyDetails2, Integer> columnZdawalnosc;
    @FXML
    private JFXButton dodajWykladowceBtn;
    @FXML
    private JFXButton edytujWykladowceBtn;
    @FXML
    private JFXButton usunWykladowceBtn;
    public static int idWykladowcy;
     private ObservableList<WykladowcyDetails2> dataWykladowcy;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        edytujWykladowceBtn.setDisable(true);
        usunWykladowceBtn.setDisable(true);

        try {
            dataWykladowcy = FXCollections.observableArrayList();
            

            Connection con = Polaczenie.ConnectDB();
            PreparedStatement ps = con.prepareStatement("SELECT id_wykladowcy,imie,nazwisko,email,telefon,jezyk,login,haslo,zdawalnosc(id_wykladowcy) as wynik FROM wykladowcy");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){
                dataWykladowcy.add(new WykladowcyDetails2(rs.getInt("id_wykladowcy"),rs.getString("imie"),rs.getString("nazwisko"),rs.getString("email"),rs.getString("telefon"),rs.getString("jezyk"),rs.getString("login"),rs.getString("haslo"),rs.getInt("wynik")));
            }
            

        } catch (SQLException ex) {
            Logger.getLogger(WykladowcyViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        columnImie.setCellValueFactory(new PropertyValueFactory<>("imie"));
        columnNazwisko.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        columnTelefon.setCellValueFactory(new PropertyValueFactory<>("telefon"));
        columnJezyk.setCellValueFactory(new PropertyValueFactory<>("jezyk"));
        columnLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
        columnZdawalnosc.setCellValueFactory(new PropertyValueFactory<>("wynik"));
            
        
        
        tableWykladowcy.setItems(null);
        tableWykladowcy.setItems(dataWykladowcy);

        
        
    }

    @FXML
    private void dodajWykladowce(ActionEvent event) throws IOException {
        AnchorPane dodaj = FXMLLoader.load(getClass().getResource("DodajWykladowceView.fxml"));
        AnchorPane home_parent2 = (AnchorPane) dodajWykladowceBtn.getParent().getParent().getParent();
        home_parent2.getChildren().clear();
        home_parent2.getChildren().add(dodaj);

    }

    @FXML
    private void edytujWykladowce(ActionEvent event) throws IOException {
        if (tableWykladowcy.getSelectionModel().getSelectedItem() != null) {
            idWykladowcy = tableWykladowcy.getSelectionModel().getSelectedItem().getId_wykladowcy();
            AnchorPane edytuj = FXMLLoader.load(getClass().getResource("EdytujWykladowceView.fxml"));
            AnchorPane home_parent2 = (AnchorPane) edytujWykladowceBtn.getParent().getParent().getParent();
            home_parent2.getChildren().clear();
            home_parent2.getChildren().add(edytuj);
        }

    }

    @FXML
    private void usunWykladowce(ActionEvent event) throws SQLException {
        String query = "{CALL KRZYSZTOF.usun_wykladowca(?)}";
        Connection con = Polaczenie.ConnectDB();
        CallableStatement cst = con.prepareCall(query);
        cst.setInt(1, tableWykladowcy.getSelectionModel().getSelectedItem().getId_wykladowcy());

        cst.execute();
        cst.close();
        con.close();

        AudioClip powiadomMnie = new AudioClip((SzkolaJezykowa.class.getResource("audio/Notify.wav")).toString());
        powiadomMnie.play();

        TrayNotification tray = new TrayNotification();
        tray.setTitle("Sukces!");
        tray.setMessage("Wykładowca został usunięty");
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(1500));

    }

    @FXML
    private void zaznacz(MouseEvent event) {
        if (tableWykladowcy.getSelectionModel().getSelectedItem() != null) {
            edytujWykladowceBtn.setDisable(false);
            usunWykladowceBtn.setDisable(false);
        }
    }

}
