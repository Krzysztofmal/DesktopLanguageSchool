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
public class KursanciDetails {
    
    private final IntegerProperty id_kursanta;
    private final StringProperty imie;
    private final StringProperty nazwisko;
    private final StringProperty pesel;
    private final StringProperty nr_tel;
    private final StringProperty email;    
    private final StringProperty login;
    private final StringProperty haslo;
    private final StringProperty plec;
    private final StringProperty status;
    
    
    public KursanciDetails (int id_kursanta, String imie, String nazwisko, String pesel, String nr_tel, String email, String login, String haslo, String plec, String status){
        this.id_kursanta = new SimpleIntegerProperty(id_kursanta);
        this.imie = new SimpleStringProperty (imie);
        this.nazwisko = new SimpleStringProperty (nazwisko);
        this.pesel = new SimpleStringProperty (pesel);
        this.nr_tel = new SimpleStringProperty (nr_tel);
        this.email = new SimpleStringProperty (email);
        this.login = new SimpleStringProperty (login);
        this.haslo = new SimpleStringProperty (haslo);
        this.plec = new SimpleStringProperty (plec);
        this.status = new SimpleStringProperty (status);        
    }
    
     public int getId_kursanta() {
        return id_kursanta.get();
    }

    public String getImie() {
        return imie.get();
    }

    public String getNazwisko() {
        return nazwisko.get();
    }
    
    public String getPesel() {
        return pesel.get();
    }
    
    public String getNr_tel() {
        return nr_tel.get();
    }
    
    public String getEmail() {
        return email.get();
    }
    
    public String getLogin() {
        return login.get();
    }
    
    public String getHaslo() {
        return haslo.get();
    }
    
    public String getPlec() {
        return plec.get();
    }
    
    public String getStatus() {
        return status.get();
    }
    
    
    
    public void setImie(String value){
        imie.set(value);
    }
    
    public void setNazwisko(String value){
        nazwisko.set(value);
    }
    
    public void setPesel(String value){
        pesel.set(value);
    }
    
    public void setNr_tel(String value){
        nr_tel.set(value);
    }
    
    public void setEmail(String value){
        email.set(value);
    }
    
    public void setLogin(String value){
        login.set(value);
    }
    
    public void setHaslo(String value){
        haslo.set(value);
    }
    
    public void setPlec(String value){
        plec.set(value);
    }
    
    public void setStatus(String value){
        status.set(value);
    }
    
    
    
    public IntegerProperty id_kursanta(){
        return id_kursanta;
    }
    
    public StringProperty imie(){
        return imie;
    }
    
    public StringProperty nazwisko(){
        return nazwisko;
    }
    
    public StringProperty pesel(){
        return pesel;
    }
    
    public StringProperty nr_tel(){
        return nr_tel;
    }
    
    public StringProperty email(){
        return email;
    }
    
    public StringProperty login(){
        return login;
    }
    
    public StringProperty haslo(){
        return haslo;
    }
    
    public StringProperty plec(){
        return plec;
    }
    
    public StringProperty status(){
        return status;
    }
    
}
