/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package szkolajezykowa.logowanie.admin.MainView.kursy;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
import szkolajezykowa.details.SaleDetails;
import szkolajezykowa.details.WykladowcyDetails;
import szkolajezykowa.polaczenie.Polaczenie;
import szkolajezykowa.sprawdzanie.Sprawdzanie;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author BlackHawk
 */
public class EdytujKursViewController implements Initializable {

    @FXML
    private JFXTextField fNazwaKursu;
    @FXML
    private JFXComboBox<String> cbPoziom;
    @FXML
    private JFXTextField fIloscGodzin;
    @FXML
    private JFXComboBox<String> cbJezyk;
    @FXML
    private JFXComboBox<WykladowcyDetails> cbProwadzacy;
    @FXML
    private JFXComboBox<SaleDetails> cbNrSali;
    @FXML
    private JFXButton zapiszBtn;
    @FXML
    private JFXButton anulujBtn;

    ObservableList dataJezyki;
    ObservableList<WykladowcyDetails> dataWykladowcy;
    ObservableList<SaleDetails> dataSale;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbPoziom.setStyle("-fx-font: 18px \"System\";");
        cbJezyk.setStyle("-fx-font: 18px \"System\";");
        cbProwadzacy.setStyle("-fx-font: 18px \"System\";");
        cbNrSali.setStyle("-fx-font: 18px \"System\";");
        cbPoziom.getItems().addAll(
                "podstawowy",
                "średnio-zaawansowany",
                "zaawansowany"
        );

        dataJezyki = FXCollections.observableArrayList();
        dataSale = FXCollections.observableArrayList();

        try {
            Connection con = Polaczenie.ConnectDB();
            PreparedStatement ps = con.prepareStatement("SELECT distinct jezyk FROM wykladowcy order by jezyk asc");
            PreparedStatement ps2 = con.prepareStatement("SELECT id_sali,nr_sali,ilosc_miejsc FROM sala");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                dataJezyki.add(rs.getString("jezyk"));
            }
            rs = ps2.executeQuery();
            while (rs.next()) {
                dataSale.add(new SaleDetails(rs.getInt("id_sali"), rs.getInt("nr_sali"), rs.getInt("ilosc_miejsc")));
            }

            con.close();
            ps.close();
            ps2.close();
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(DodajKursViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        cbJezyk.setItems(null);
        cbJezyk.setItems(dataJezyki);

        cbJezyk.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (newValue != null) {

                    //cbProwadzacy.setPromptText("");
                    // przemyśleć :D
                    //WykladowcyDetails wd = new WykladowcyDetails(0, "", "", "", "", "", "", "");
                    //WykladowcyDetails wd;
                    //cbProwadzacy.setValue(wd);
                    dataWykladowcy = FXCollections.observableArrayList();
                    dataWykladowcy.clear();
                    try {
                        Connection con = Polaczenie.ConnectDB();
                        PreparedStatement ps = con.prepareStatement("SELECT id_wykladowcy,imie,nazwisko,email,telefon,jezyk,login,haslo FROM wykladowcy where jezyk='" + newValue.toString() + "'");
                        ResultSet rs = ps.executeQuery();
                        dataWykladowcy.clear();
                        while (rs.next()) {
                            dataWykladowcy.add(new WykladowcyDetails(rs.getInt("id_wykladowcy"), rs.getString("imie"), rs.getString("nazwisko"),
                                    rs.getString("email"), rs.getString("telefon"), rs.getString("jezyk"), rs.getString("login"), rs.getString("haslo")));
                        }

                    } catch (SQLException ex) {
                        Logger.getLogger(DodajKursViewController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //cbProwadzacy.setValue(null);

                    cbProwadzacy.setItems(null);
                    cbProwadzacy.setItems(dataWykladowcy);

                    cbProwadzacy.setCellFactory(new Callback<ListView<WykladowcyDetails>, ListCell<WykladowcyDetails>>() {

                        @Override
                        public ListCell<WykladowcyDetails> call(ListView<WykladowcyDetails> p) {

                            final ListCell<WykladowcyDetails> cell = new ListCell<WykladowcyDetails>() {

                                @Override
                                protected void updateItem(WykladowcyDetails t, boolean bln) {
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

                    cbProwadzacy.setConverter(new StringConverter<WykladowcyDetails>() {
                        @Override
                        public String toString(WykladowcyDetails object) {
                            if (object == null) {
                                return "";
                            } else {
                                return object.getImie() + " " + object.getNazwisko();
                            }
                        }

                        @Override
                        public WykladowcyDetails fromString(String string) {
                            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }
                    });
                    //if (cbProwadzacy.getPromptText().equals("")) cbProwadzacy.setPromptText("Prowadzący");
                    //cbProwadzacy.promptTextProperty().setValue("Prowadzący");

                    //cbProwadzacy.getSelectionModel().selectFirst();
                }
            }
        });

        cbNrSali.setItems(null);
        cbNrSali.setItems(dataSale);

        cbNrSali.setCellFactory(new Callback<ListView<SaleDetails>, ListCell<SaleDetails>>() {

            @Override
            public ListCell<SaleDetails> call(ListView<SaleDetails> p) {

                final ListCell<SaleDetails> cell = new ListCell<SaleDetails>() {

                    @Override
                    protected void updateItem(SaleDetails t, boolean bln) {
                        super.updateItem(t, bln);

                        if (t != null) {
                            setText("Sala " + t.getNr_sali() + " (miejsca " + t.getIlosc_miejsc() + ")");
                        } else {
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        });

        cbNrSali.setConverter(new StringConverter<SaleDetails>() {
            @Override
            public String toString(SaleDetails object) {
                if (object == null) {
                    return "";
                } else {
                    return "Sala " + object.getNr_sali() + " (miejsca " + object.getIlosc_miejsc() + ")";
                }
            }

            @Override
            public SaleDetails fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

        //uzupełnianie pól danymi
        try {
            Connection con = Polaczenie.ConnectDB();
            PreparedStatement ps = con.prepareStatement("SELECT kurs.nazwa_kursu,kurs.ilosc_godzin,kurs.poziom,kurs.jezyk,sala.id_sali,sala.nr_sali,sala.ilosc_miejsc,wykladowcy.id_wykladowcy,wykladowcy.imie,wykladowcy.nazwisko FROM kurs,wykladowcy,sala WHERE kurs.id_kursu=" + KursyViewController.idKursu + " AND kurs.id_wykladowcy=wykladowcy.id_wykladowcy AND kurs.id_sali=sala.id_sali");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                fNazwaKursu.setText(rs.getString("nazwa_kursu"));
                cbPoziom.setValue(rs.getString("poziom"));
                fIloscGodzin.setText(rs.getString("ilosc_godzin"));
                cbJezyk.setValue(rs.getString("jezyk"));
//              if (tableKursy.getSelectionModel().getSelectedItem() != null){
                for (WykladowcyDetails wd : cbProwadzacy.getItems()) {
                    if (wd.getId_wykladowcy() == rs.getInt("id_wykladowcy")) {
                        cbProwadzacy.setValue(wd);
                    }
                }
//            
                for (SaleDetails sd : cbNrSali.getItems()) {
                    if (sd.getId_sali() == rs.getInt("id_sali")) {
                        cbNrSali.setValue(sd);
                    }
                }

            }

            rs.close();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DodajKursViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void zapiszKurs(ActionEvent event) throws IOException, SQLException {
        AudioClip powiadomMnie = new AudioClip((SzkolaJezykowa.class.getResource("audio/Notify.wav")).toString());
        TrayNotification tray = new TrayNotification();
        boolean sprawdzenie = false;

        if (!fNazwaKursu.getText().isEmpty() && Sprawdzanie.czyLiczby(fIloscGodzin.getText()) && !fIloscGodzin.getText().isEmpty() && !cbPoziom.getSelectionModel().getSelectedItem().isEmpty() && !cbJezyk.getSelectionModel().getSelectedItem().isEmpty() && !cbProwadzacy.getSelectionModel().getSelectedItem().equals(null) && !cbNrSali.getSelectionModel().getSelectedItem().equals(null)) {
            sprawdzenie = true;
        }

        if (sprawdzenie) {

            Connection con = Polaczenie.ConnectDB();

            String query = "{CALL KRZYSZTOF.update_kurs(?,?,?,?,?,?,?)}";
            CallableStatement cst = con.prepareCall(query);

            cst.setInt(1, KursyViewController.idKursu);
            cst.setString(2, fNazwaKursu.getText());
            cst.setInt(3, Integer.parseInt(fIloscGodzin.getText()));
            cst.setString(4, (String) cbPoziom.getSelectionModel().getSelectedItem().toString());
            cst.setString(5, (String) cbJezyk.getSelectionModel().getSelectedItem().toString());
            cst.setInt(6, cbNrSali.getSelectionModel().getSelectedItem().getId_sali());
            cst.setInt(7, cbProwadzacy.getSelectionModel().getSelectedItem().getId_wykladowcy());

            cst.execute();
            con.close();
            cst.close();

            powiadomMnie.play();
            tray.setTitle("Sukces!");
            tray.setMessage("Aktualizacja kursu zakończyła się sukcesem!");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(1500));

            AnchorPane dodaj = FXMLLoader.load(getClass().getResource("KursyView.fxml"));
            AnchorPane home_parent2 = (AnchorPane) anulujBtn.getParent().getParent().getParent();
            home_parent2.getChildren().clear();
            home_parent2.getChildren().add(dodaj);

            if (!sprawdzenie) {
                powiadomMnie.play();
                tray.setTitle("Błąd!");
                tray.setMessage("Aktualizacja kursu nie powiodła się. \n"
                        + "Sprawdź czy wprowadziłeś poprawnie dane.");
                tray.setNotificationType(NotificationType.ERROR);
                tray.showAndDismiss(Duration.millis(1500));
            }

        }
    }

    @FXML
    private void anuluj(ActionEvent event) throws IOException {
        AnchorPane edytuj = FXMLLoader.load(getClass().getResource("KursyView.fxml"));
        AnchorPane home_parent2 = (AnchorPane) anulujBtn.getParent().getParent().getParent();
        home_parent2.getChildren().clear();
        home_parent2.getChildren().add(edytuj);
    }

}
