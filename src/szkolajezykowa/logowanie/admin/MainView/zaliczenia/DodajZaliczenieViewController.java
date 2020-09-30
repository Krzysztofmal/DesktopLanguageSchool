/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package szkolajezykowa.logowanie.admin.MainView.zaliczenia;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.util.Callback;
import javafx.util.Duration;
import javafx.util.StringConverter;
import szkolajezykowa.SzkolaJezykowa;
import szkolajezykowa.details.KursanciDetails;
import szkolajezykowa.details.WykladowcaKursyDetails2;
import szkolajezykowa.logowanie.LoginViewController;
import szkolajezykowa.polaczenie.Polaczenie;
import szkolajezykowa.sprawdzanie.Sprawdzanie;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author BlackHawk
 */
public class DodajZaliczenieViewController implements Initializable {

    @FXML
    private JFXComboBox<WykladowcaKursyDetails2> cbKurs;
    @FXML
    private JFXComboBox<KursanciDetails> cbKursant;
    @FXML
    private JFXDatePicker dtData;
    @FXML
    private JFXComboBox<String> cbTyp;
    @FXML
    private JFXTextField fPunkty;
    @FXML
    private JFXButton dodajZaliczenieBtn;
    @FXML
    private JFXButton anulujBtn;
        ObservableList<WykladowcaKursyDetails2> dataKursy;
    ObservableList<KursanciDetails> dataKursanci;
    private Integer idKursu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dtData.setEditable(false);
        cbKurs.setStyle("-fx-font: 18px \"System\";");
        cbKursant.setStyle("-fx-font: 18px \"System\";");
        cbTyp.setStyle("-fx-font: 18px \"System\";");
        dtData.setStyle("-fx-font: 18px \"System\";");
        cbTyp.getItems().addAll(
                "ustny",
                "pisemny"
        );
        dataKursy = FXCollections.observableArrayList();

        try {
            Connection con = Polaczenie.ConnectDB();
            PreparedStatement ps = con.prepareStatement("SELECT distinct kurs.nazwa_kursu,kurs.jezyk,kurs.poziom,kurs.ilosc_godzin,kurs.id_kursu FROM kurs,wykladowcy");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                dataKursy.add(new WykladowcaKursyDetails2(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5)));
            }

            con.close();
            ps.close();
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(szkolajezykowa.logowanie.admin.MainView.zaliczenia.DodajZaliczenieViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        cbKurs.setItems(null);
        cbKurs.setItems(dataKursy);

        cbKurs.setCellFactory(new Callback<ListView<WykladowcaKursyDetails2>, ListCell<WykladowcaKursyDetails2>>() {

            @Override
            public ListCell<WykladowcaKursyDetails2> call(ListView<WykladowcaKursyDetails2> p) {

                final ListCell<WykladowcaKursyDetails2> cell = new ListCell<WykladowcaKursyDetails2>() {

                    @Override
                    protected void updateItem(WykladowcaKursyDetails2 t, boolean bln) {
                        super.updateItem(t, bln);

                        if (t != null) {
                            setText(t.getNazwa_kursu());
                        } else {
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        });

        cbKurs.setConverter(new StringConverter<WykladowcaKursyDetails2>() {
            @Override
            public String toString(WykladowcaKursyDetails2 object) {
                if (object == null) {
                    return "";
                } else {
                    return object.getNazwa_kursu();
                }
            }

            @Override
            public WykladowcaKursyDetails2 fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        //idKursu = cbKurs.getSelectionModel().getSelectedItem().getId_kursu();
        
        cbKurs.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (newValue != null) {        
                    dataKursanci = FXCollections.observableArrayList(); 
                    dataKursanci.clear();
                    idKursu = cbKurs.getSelectionModel().getSelectedItem().getId_kursu();
                    try {
                        Connection con = Polaczenie.ConnectDB();
                        PreparedStatement ps = con.prepareStatement("SELECT distinct kursanci.id_kursanta,kursanci.imie,kursanci.nazwisko,kursanci.pesel,kursanci.nr_tel,kursanci.email,kursanci.login,kursanci.haslo,kursanci.plec,kursanci.status \n" +
"FROM kursanci,kurskursanci,kurs WHERE kurs.id_kursu=" + idKursu + " AND kurs.id_kursu=kurskursanci.id_kursu AND kurskursanci.id_kursanta=kursanci.id_kursanta");
                        ResultSet rs = ps.executeQuery();                        
                        while (rs.next()) {
                            dataKursanci.add(new KursanciDetails(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10)));
                        }

                    } catch (SQLException ex) {
                        Logger.getLogger(szkolajezykowa.logowanie.admin.MainView.zaliczenia.DodajZaliczenieViewController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //cbProwadzacy.setValue(null);
                                        
                    cbKursant.setItems(null);
                    cbKursant.setItems(dataKursanci);
        
                    
                    
                           cbKursant.setCellFactory(new Callback<ListView<KursanciDetails>, ListCell<KursanciDetails>>() {

            @Override
            public ListCell<KursanciDetails> call(ListView<KursanciDetails> p) {

                final ListCell<KursanciDetails> cell = new ListCell<KursanciDetails>() {

                    @Override
                    protected void updateItem(KursanciDetails t, boolean bln) {
                        super.updateItem(t, bln);

                        if (t != null) {
                            setText(t.getImie() + " " + t.getNazwisko());
                        } else {
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        });

        cbKursant.setConverter(new StringConverter<KursanciDetails>() {
            @Override
            public String toString(KursanciDetails object) {
                if (object == null) {
                    return "";
                } else {
                    return object.getImie() + " " + object.getNazwisko();
                }
            }

            @Override
            public KursanciDetails fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
                    
                    
                }
            }
        });
        
    }    

    @FXML
    private void dodajZaliczenie(ActionEvent event) throws IOException, SQLException {
        Connection con = Polaczenie.ConnectDB();
        boolean add = true;
        if (Integer.parseInt(fPunkty.getText())<0 ||   Integer.parseInt(fPunkty.getText())>100){
            add = false;
        }
        
        if (cbKurs.getSelectionModel().getSelectedItem()==null || dtData.getValue().toString().isEmpty() || cbTyp.getSelectionModel().getSelectedItem()==null){
            add = false;
        }
        
        if (!Sprawdzanie.czyLiczby(fPunkty.getText())){
            add = false;
        }
        
        if (add){
            String query = "{CALL KRZYSZTOF.dodaj_zaliczenie(?,?,?,?,?)}";
            CallableStatement cst = con.prepareCall(query);
            cst.setString(1, dtData.getValue().toString());
            cst.setInt(2, Integer.parseInt(fPunkty.getText()));
            cst.setString(3, cbTyp.getSelectionModel().getSelectedItem().toString());
            cst.setInt(4, cbKurs.getSelectionModel().getSelectedItem().getId_kursu());
            cst.setInt(5, cbKursant.getSelectionModel().getSelectedItem().getId_kursanta());

            cst.execute();
            cst.close();
            con.close();

            AudioClip powiadomMnie = new AudioClip((SzkolaJezykowa.class.getResource("audio/Notify.wav")).toString());
            powiadomMnie.play();

            TrayNotification tray = new TrayNotification();
            tray.setTitle("Sukces!");
            tray.setMessage("Zaliczenie zostało dodane");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(1500));
        
        
            AnchorPane dodaj = FXMLLoader.load(getClass().getResource("ZaliczeniaView.fxml"));
            AnchorPane home_parent2 = (AnchorPane) dodajZaliczenieBtn.getParent().getParent().getParent();
            home_parent2.getChildren().clear();
            home_parent2.getChildren().add(dodaj);
        }
        
        if (!add){
            AudioClip powiadomMnie = new AudioClip((SzkolaJezykowa.class.getResource("audio/Notify.wav")).toString());
            powiadomMnie.play();

            TrayNotification tray = new TrayNotification();
            tray.setTitle("Błąd!");
            tray.setMessage("Zaliczenie nie zostało dodane");
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.millis(1500));
        }
    }

    @FXML
    private void anuluj(ActionEvent event) throws IOException {
        AnchorPane anuluj = FXMLLoader.load(getClass().getResource("ZaliczeniaView.fxml"));
        AnchorPane home_parent2 = (AnchorPane) anulujBtn.getParent().getParent().getParent();
        home_parent2.getChildren().clear();
        home_parent2.getChildren().add(anuluj);
    }
    
}
