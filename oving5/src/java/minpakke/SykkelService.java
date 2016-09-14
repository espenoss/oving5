/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minpakke;

import java.util.ArrayList;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/sykler/")
public class SykkelService {
    
    private static ArrayList<Sykkel> sykler = new ArrayList<Sykkel>(){{
      add(new Sykkel("1", "Dragvoll")); 
      add(new Sykkel("2", "Gløshaugen"));
    }};
    
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public ArrayList<Sykkel> getSykler() {
        return sykler;
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void endreMelding(String nyMelding) {
//        serverMelding = nyMelding;
    }   
}