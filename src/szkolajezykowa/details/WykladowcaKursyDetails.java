/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package szkolajezykowa.details;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author BlackHawk
 */
public class WykladowcaKursyDetails {
    private final StringProperty nazwa_kursu;
    private final StringProperty jezyk;
    private final StringProperty poziom;
    private final IntegerProperty ilosc_godzin;
    private final IntegerProperty nr_sali;
    
    public WykladowcaKursyDetails (String nazwa_kursu, String jezyk, String poziom, int ilosc_godzin, int nr_sali) {
        this.nazwa_kursu = new SimpleStringProperty(nazwa_kursu);
        this.jezyk = new SimpleStringProperty(jezyk);
        this.poziom = new SimpleStringProperty(poziom);
        this.ilosc_godzin = new SimpleIntegerProperty(ilosc_godzin);
        this.nr_sali = new SimpleIntegerProperty(nr_sali);
}
    

    
    public String getNazwa_kursu(){
        return nazwa_kursu.get();
    }
    
    public String getJezyk(){
        return jezyk.get();
    }
    
    public String getPoziom(){
        return poziom.get();
    }
    
    public int getIlosc_godzin(){
        return ilosc_godzin.get();
    }
    
    public Integer getNr_sali(){
        return nr_sali.get();
    }
    
    
    
    
    public void setNazwa_kursu(String value){
        nazwa_kursu.set(value);
    }
    
    public void setJezyk(String value){
        jezyk.set(value);
    }
    
    public void setPoziom(String value){
        poziom.set(value);
    }
    
    public void setIlosc_godzin(int value){
        ilosc_godzin.set(value);
    }
    

    
    

    
    public StringProperty nazwa_kursu(){
        return nazwa_kursu;
    }
    
    public StringProperty jezyk(){
        return jezyk;
    }
    
    public StringProperty poziom(){
        return poziom;
    }
    
    public IntegerProperty ilosc_godzin(){
        return ilosc_godzin;
    }
    
    public IntegerProperty nr_sali(){
        return nr_sali;
    }
    

}
