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
    
    int nr;
    boolean ledig = false;
    String plassering;
    int batteriStatus = 100;

    Sykkel(int nr, String plassering){
        this.nr = nr;
        this.plassering = plassering;
    }
    
    public int getBatteriStatus() {
        return batteriStatus;
    }

    public void setBatteriStatus(int batteriStatus) {
        this.batteriStatus = batteriStatus;
    }
    

    public String getPlassering() {
        return plassering;
    }

    public void setPlassering(String plassering) {
        this.plassering = plassering;
    }

    public int getNr() {
        return nr;
    }

    public void setNr(int nr) {
        this.nr = nr;
    }

    public boolean isLedig() {
        return ledig;
    }

    public void setLedig(boolean ledig) {
        this.ledig = ledig;
    }
    
}
