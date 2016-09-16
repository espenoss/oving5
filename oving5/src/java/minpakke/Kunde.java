/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minpakke;

import java.util.ArrayList;

/**
 *
 * @author espen
 */
public class Kunde {
    
    String brukerNavn;
    String epost;
    ArrayList<Bestilling> bestillinger = new ArrayList<Bestilling>();
    
    public Kunde(String brukerNavn, String epost){
        this.brukerNavn = brukerNavn;
        this. epost = epost;
    }

    public String getBrukerNavn() {
        return brukerNavn;
    }

    public void setBrukerNavn(String brukerNavn) {
        this.brukerNavn = brukerNavn;
    }

    public String getEpost() {
        return epost;
    }

    public void setEpost(String epost) {
        this.epost = epost;
    }

    public ArrayList<Bestilling> getBestillinger() {
        return bestillinger;
    }

    public void setBestillinger(ArrayList<Bestilling> bestillinger) {
        this.bestillinger = bestillinger;
    }


    
    public Kunde(){
        
    }
}
