/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package szkolajezykowa.logowanie.uzytkownik.MainView.zaliczenia;

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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import szkolajezykowa.details.KursanciZaliczeniaDetails;
import szkolajezykowa.logowanie.LoginViewController;
import szkolajezykowa.polaczenie.Polaczenie;

/**
 * FXML Controller class
 *
 * @author BlackHawk
 */
public class ZaliczeniaController implements Initializable {

    @FXML
    private TableView<KursanciZaliczeniaDetails> tableZaliczenia;
    @FXML
    private TableColumn<KursanciZaliczeniaDetails, Integer> columnNazwaKursu;
    @FXML
    private TableColumn<KursanciZaliczeniaDetails, String> columnProwadzacy;
    @FXML
    private TableColumn<KursanciZaliczeniaDetails, String> columnJezyk;
    @FXML
    private TableColumn<KursanciZaliczeniaDetails, String> columnPoziom;
    @FXML
    private TableColumn<KursanciZaliczeniaDetails, String> columnData;
    @FXML
    private TableColumn<KursanciZaliczeniaDetails, String> columnTyp;
    @FXML
    private TableColumn<KursanciZaliczeniaDetails, Integer> columnWynik;
    @FXML
    private Label labelRezultat;

    private ObservableList<KursanciZaliczeniaDetails> dataZaliczenia;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        dataZaliczenia = FXCollections.observableArrayList();
        dataZaliczenia.clear();

        try {
            Connection con = Polaczenie.ConnectDB();

            PreparedStatement ps = con.prepareStatement("SELECT zaliczenia.id_zaliczenia,kurs.nazwa_kursu, wykladowcy.imie ||' '||wykladowcy.nazwisko as prowadzacy,kurs.jezyk,kurs.poziom,zaliczenia.data_zal,zaliczenia.typ,zaliczenia.wynik,kurs.id_kursu\n"
                    + "FROM kurs,wykladowcy,zaliczenia,kursanci,kurskursanci WHERE kurs.id_wykladowcy=wykladowcy.id_wykladowcy AND kurs.id_kursu = zaliczenia.id_kursu AND zaliczenia.id_kursanta=kursanci.id_kursanta AND\n"
                    + "kurs.id_kursu=kurskursanci.id_kursu AND kurskursanci.id_kursanta=kursanci.id_kursanta AND kursanci.id_kursanta =" + LoginViewController.r);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){
                dataZaliczenia.add(new KursanciZaliczeniaDetails(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getInt(9)));
            }

            con.close();
            ps.close();
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(ZaliczeniaController.class.getName()).log(Level.SEVERE, null, ex);
        }

        columnNazwaKursu.setCellValueFactory(new PropertyValueFactory<>("nazwa_kursu"));
        columnProwadzacy.setCellValueFactory(new PropertyValueFactory<>("prowadzacy"));
        columnJezyk.setCellValueFactory(new PropertyValueFactory<>("jezyk"));
        columnPoziom.setCellValueFactory(new PropertyValueFactory<>("poziom"));
        columnData.setCellValueFactory(new PropertyValueFactory<>("data_zal"));
        columnTyp.setCellValueFactory(new PropertyValueFactory<>("typ"));
        columnWynik.setCellValueFactory(new PropertyValueFactory<>("wynik"));
        
        
        tableZaliczenia.setItems(null);
        tableZaliczenia.setItems(dataZaliczenia);

    }

    @FXML
    private void zaznacz(MouseEvent event) throws SQLException {
        if (tableZaliczenia.getSelectionModel().getSelectedItem() != null) {
            //wypisanie do labelu rezultatu egzaminu
            Connection con = Polaczenie.ConnectDB();
            PreparedStatement ps = con.prepareStatement("SELECT czy_zdane("+ LoginViewController.r +","+ tableZaliczenia.getSelectionModel().getSelectedItem().getId_kursu() +") FROM dual");
            ResultSet rs = ps.executeQuery();
            rs.next();
            switch (rs.getString(1)){
                case "pozytywny":
                    labelRezultat.setText("");
                    labelRezultat.setText("pozytywny");
                    labelRezultat.setTextFill(Color.GREEN);
                    break;
                case "negatywny":
                    labelRezultat.setText("");
                    labelRezultat.setText("negatywny");
                    labelRezultat.setTextFill(Color.RED);
                    break;
            }
            
            con.close();
            ps.close();
            rs.close();
        }

    }

}
