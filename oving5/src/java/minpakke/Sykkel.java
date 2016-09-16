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
public class Sykkel {
    
    String nr;
    String ledig = "Ledig";
    String plassering;
    String batteriStatus = "100";

    public Sykkel(String nr, String plassering){
        this.nr = nr;
        this.plassering = plassering;
    }

    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }

    public String getLedig() {
        return ledig;
    }

    public void setLedig(String ledig) {
        this.ledig = ledig;
    }

    public String getPlassering() {
        return plassering;
    }

    public void setPlassering(String plassering) {
        this.plassering = plassering;
    }

    public String getBatteriStatus() {
        return batteriStatus;
    }

    public void setBatteriStatus(String batteriStatus) {
        this.batteriStatus = batteriStatus;
    }
    
    
    public Sykkel(){
        
    }
    
}
