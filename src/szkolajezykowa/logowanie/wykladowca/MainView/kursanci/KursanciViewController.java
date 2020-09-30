/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package szkolajezykowa.logowanie.wykladowca.MainView.kursanci;

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
import szkolajezykowa.details.WykladowcaTwoiKursanciDetails;
import szkolajezykowa.logowanie.LoginViewController;
import szkolajezykowa.polaczenie.Polaczenie;

/**
 * FXML Controller class
 *
 * @author BlackHawk
 */
public class KursanciViewController implements Initializable {

    @FXML
    private TableView<WykladowcaTwoiKursanciDetails> tableKursanci;
    @FXML
    private TableColumn<WykladowcaTwoiKursanciDetails, String> columnKursant;
    @FXML
    private TableColumn<WykladowcaTwoiKursanciDetails, String> columnTelefon;
    @FXML
    private TableColumn<WykladowcaTwoiKursanciDetails, String> columnEmail;
    @FXML
    private TableColumn<WykladowcaTwoiKursanciDetails, String> columnPlec;
    @FXML
    private TableColumn<WykladowcaTwoiKursanciDetails, String> columnStatus;
    private ObservableList<WykladowcaTwoiKursanciDetails> dataKursanci;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        dataKursanci = FXCollections.observableArrayList();

        try {
            Connection con = Polaczenie.ConnectDB();
            PreparedStatement ps = con.prepareStatement("SELECT distinct kursanci.imie||' '||kursanci.nazwisko as kursant,kursanci.nr_tel,kursanci.email,kursanci.plec,kursanci.status FROM kursanci,kurskursanci,kurs,wykladowcy\n"
                    + "WHERE wykladowcy.id_wykladowcy=kurs.id_wykladowcy AND kurs.id_kursu=kurskursanci.id_kursu AND kurskursanci.id_kursanta=kursanci.id_kursanta AND wykladowcy.id_wykladowcy=" + LoginViewController.r);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                dataKursanci.add(new WykladowcaTwoiKursanciDetails(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
            con.close();
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(KursanciViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

        columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        columnKursant.setCellValueFactory(new PropertyValueFactory<>("kursant"));
        columnPlec.setCellValueFactory(new PropertyValueFactory<>("plec"));
        columnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        columnTelefon.setCellValueFactory(new PropertyValueFactory<>("nr_tel"));

        tableKursanci.setItems(null);
        tableKursanci.setItems(dataKursanci);

    }

}
