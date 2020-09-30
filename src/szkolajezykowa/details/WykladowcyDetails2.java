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
public class WykladowcyDetails2 {
    
    private final IntegerProperty id_wykladowcy;
    private final StringProperty imie;
    private final StringProperty nazwisko;
    private final StringProperty email;
    private final StringProperty telefon;
    private final StringProperty jezyk;
    private final StringProperty login;
    private final StringProperty haslo;
    private final IntegerProperty wynik;

    public WykladowcyDetails2(int id_wykladowcy, String imie, String nazwisko, String email, String telefon, String jezyk, String login, String haslo,int wynik) {
        this.id_wykladowcy = new SimpleIntegerProperty(id_wykladowcy);
        this.imie = new SimpleStringProperty (imie);
        this.nazwisko = new SimpleStringProperty (nazwisko);
        this.email = new SimpleStringProperty (email);
        this.telefon = new SimpleStringProperty(telefon);
        this.jezyk = new SimpleStringProperty (jezyk);  
        this.login = new SimpleStringProperty (login);   
        this.haslo = new SimpleStringProperty (haslo); 
        this.wynik = new SimpleIntegerProperty (wynik);
    }

    public int getId_wykladowcy() {
        return id_wykladowcy.get();
    }

    public String getImie() {
        return imie.get();
    }

    public String getNazwisko() {
        return nazwisko.get();
    }

    public String getEmail() {
        return email.get();
    }

    public String getTelefon() {
        return telefon.get();
    }

    public String getJezyk() {
        return jezyk.get();
    }
    public String getLogin() {
        return login.get();
    }
    public String getHaslo() {
        return haslo.get();
    }
    public int getWynik(){
        return wynik.get();}
    
    
    
    public void setImie(String value){
        imie.set(value);
    }
    
    public void setNazwisko(String value){
        nazwisko.set(value);
    }
    
    public void setEmail(String value){
        email.set(value);
    }
    
    public void setTelefon(String value){
        telefon.set(value);
    }
    
    public void setJezyk(String value){
        jezyk.set(value);
    } 
    public void setLogin(String value){
        login.set(value);
    }   
    public void setHaslo(String value){
        haslo.set(value);
    }   
    
    
    public IntegerProperty id_wykladowcy(){
        return id_wykladowcy;
    }
    
    public StringProperty imie(){
        return imie;
    }
    
    public StringProperty nazwisko(){
        return nazwisko;
    }
    
    public StringProperty email(){
        return email;
    }
    
    public StringProperty telefon(){
        return telefon;
    }
    
    public StringProperty jezyk(){
        return jezyk;
    }
    public StringProperty login(){
        return login;
    }
    
    public StringProperty haslo(){
        return haslo;
    }
    public IntegerProperty wynik(){
        return wynik;
    
}

}
