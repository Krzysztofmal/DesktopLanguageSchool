/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package szkolajezykowa.details;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author BlackHawk
 */
public class WykladowcaTwoiKursanciDetails {
    private final StringProperty kursant;
    private final StringProperty nr_tel;
    private final StringProperty email;
    private final StringProperty plec;
    private final StringProperty status;
    
    
    public WykladowcaTwoiKursanciDetails(String kursant,String nr_tel,String email,String plec,String status){
        this.kursant = new SimpleStringProperty(kursant);
        this.nr_tel = new SimpleStringProperty(nr_tel);
        this.email = new SimpleStringProperty(email);
        this.plec = new SimpleStringProperty(plec);
        this.status = new SimpleStringProperty(status);
    }
    
    public String getKursant(){
        return kursant.get();
    }
    public String getNr_tel(){
        return nr_tel.get();
    }
    public String getEmail(){
        return email.get();
    }
    public String getPlec(){
        return plec.get();
    }
    public String getStatus(){
        return status.get();
    }
    
    public StringProperty kursant(){
        return kursant;
    }
    public StringProperty nr_tel(){
        return nr_tel;
    }
    public StringProperty email(){
        return email;
    }
    public StringProperty plec(){
        return plec;
    }
    public StringProperty status(){
        return status;
    }
    
}
