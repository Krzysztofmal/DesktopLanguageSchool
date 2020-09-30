/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package szkolajezykowa.statystyki;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import szkolajezykowa.polaczenie.Polaczenie;

/**
 * FXML Controller class
 *
 * @author BlackHawk
 */
public class StatystykiViewController implements Initializable {

    @FXML
    private Label labelRezultat1;
    @FXML
    private Label labelRezultat2;
    @FXML
    private Label labelRezultat3;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            Connection con = Polaczenie.ConnectDB();
            PreparedStatement ps = con.prepareStatement("SELECT sredni_wynik_efektu('ustny') as ustny from dual");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                labelRezultat2.setText(rs.getString("ustny"));
            }
            
            ps = con.prepareStatement("SELECT sredni_wynik_efektu('pisemny') as pisemny from dual");
            rs = ps.executeQuery();
            while (rs.next()){
                labelRezultat3.setText(rs.getString("pisemny"));
            }
            
             ps = con.prepareStatement("SELECT czesty_jezyk() as jezyk from dual");
            rs = ps.executeQuery();
            while (rs.next()){
                labelRezultat1.setText(rs.getString("jezyk"));
            }
            
            
            con.close();
            ps.close();
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(StatystykiViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }    
    
}
