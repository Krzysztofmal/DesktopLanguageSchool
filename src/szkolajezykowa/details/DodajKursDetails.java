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
public class DodajKursDetails {
    
    private final IntegerProperty id_kursu;
    private final StringProperty nazwa_kursu;
    private final StringProperty poziom;
    private final StringProperty jezyk;
    private final IntegerProperty ilosc_godzin;
    private final StringProperty prowadzacy;
    private final IntegerProperty nr_sali;
    private final IntegerProperty miejsca;
    private final DoubleProperty cena;
    
    public DodajKursDetails (int id_kursu, String nazwa_kursu, String poziom, String jezyk, int ilosc_godzin, String prowadzacy,int nr_sali, int miejsca, double cena) {
        this.id_kursu = new SimpleIntegerProperty(id_kursu);
        this.nazwa_kursu = new SimpleStringProperty(nazwa_kursu);
        this.jezyk = new SimpleStringProperty(jezyk);
        this.poziom = new SimpleStringProperty(poziom);
        this.ilosc_godzin = new SimpleIntegerProperty(ilosc_godzin);
        this.prowadzacy = new SimpleStringProperty(prowadzacy);
        this.cena = new SimpleDoubleProperty(cena);
        this.nr_sali = new SimpleIntegerProperty(nr_sali);
        this.miejsca = new SimpleIntegerProperty(miejsca);
}
    
    public int getId_kursu() {
        return id_kursu.get();
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
    
    public String getProwadzacy(){
        return prowadzacy.get();
    }
    
    public Double getCena(){
        return cena.get();
    }
    public int getNr_sali(){
        return nr_sali.get();
    }
    public int getMiejsca(){
        return miejsca.get();
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
    
    public void setProwadzacy(String value){
        prowadzacy.set(value);
    }
    
    public void setCena(Double value){
        cena.set(value);
    }
    public void setNr_sali(int value){
        nr_sali.set(value);
    }
    
    
    public IntegerProperty id_kursu(){
        return id_kursu;
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
    
    public StringProperty prowadzacy(){
        return prowadzacy;
    }
    
    public DoubleProperty cena(){
        return cena;
    }
    public IntegerProperty nr_sali(){
        return nr_sali;
    }
    public IntegerProperty miejsca(){
        return miejsca;
    }
    
}
