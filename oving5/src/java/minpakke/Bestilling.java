/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minpakke;

/**
 *
 * @author espen
 */
public class Bestilling {
    
    String dato;
    String kode;
    Sykkel sykkel;

    public Bestilling(String dato, String kode, Sykkel sykkel) {
        this.dato = dato;
        this.kode = kode;
        this.sykkel = sykkel;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public Sykkel getSykkel() {
        return sykkel;
    }

    public void setSykkel(Sykkel sykkel) {
        this.sykkel = sykkel;
    }
   
    public Bestilling(){
        
    }
}