/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package szkolajezykowa.details;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author BlackHawk
 */
public class KursanciZaliczeniaDetails {
    
    private final IntegerProperty id_zaliczenia;
    private final StringProperty nazwa_kursu;
    private final StringProperty prowadzacy;
    private final StringProperty jezyk;
    private final StringProperty poziom;
    private final StringProperty data_zal;
    private final StringProperty typ;
    private final IntegerProperty wynik;
    private final IntegerProperty id_kursu;
    
    public KursanciZaliczeniaDetails (int id_zaliczenia, String nazwa_kursu, String prowadzacy, String jezyk, String poziom, String data_zal, String typ, int wynik, int id_kursu){
        this.id_zaliczenia = new SimpleIntegerProperty(id_zaliczenia);
        this.nazwa_kursu = new SimpleStringProperty(nazwa_kursu);
        this.prowadzacy = new SimpleStringProperty(prowadzacy);
        this.jezyk = new SimpleStringProperty(jezyk);
        this.poziom = new SimpleStringProperty(poziom);
        this.data_zal = new SimpleStringProperty(data_zal);
        this.typ = new SimpleStringProperty(typ);
        this.wynik = new SimpleIntegerProperty(wynik);
        this.id_kursu = new SimpleIntegerProperty(id_kursu);
    }
    
    
    public int getId_zaliczenia(){
        return id_zaliczenia.get();
    }
    
    public String getNazwa_kursu(){
        return nazwa_kursu.get();
    }
    
    public String getProwadzacy(){
        return prowadzacy.get();
    }
    
    public String getJezyk(){
        return jezyk.get();
    }
    
    public String getPoziom(){
        return poziom.get();
    }
    
    public String getData_zal(){
        return data_zal.get();
    }
    
    public String getTyp(){
        return typ.get();
    }
    
    public int getWynik(){
        return wynik.get();
    }
    
    public int getId_kursu(){
        return id_kursu.get();
    }
    
    
    
    public IntegerProperty id_zaliczenia(){
        return id_zaliczenia;
    }
    
    public StringProperty nazwa_kursu(){
        return nazwa_kursu;
    }
    
    public StringProperty prowadzacy(){
        return prowadzacy;
    }
    
    public StringProperty jezyk(){
        return jezyk;
    }
    
    public StringProperty poziom(){
        return poziom;
    }
    
    public StringProperty data_zal(){
        return data_zal;
    }
    
    public StringProperty typ(){
        return typ;
    }
    
    public IntegerProperty wynik(){
        return wynik;
    }
    
    public IntegerProperty id_kursu(){
        return id_kursu;
    }
    
    
    
    
    
}
