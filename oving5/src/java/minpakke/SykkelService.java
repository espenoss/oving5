/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minpakke;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/sykler/")
public class SykkelService {
    
    private static Map<String,Sykkel> sykler = new HashMap(){{
      put("1", new Sykkel("1", "Dragvoll")); 
      put("2", new Sykkel("2", "Gløshaugen"));
      put("3", new Sykkel("3", "Dragvoll"));
    }};
    
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Collection<Sykkel> getSykler() {
        return sykler.values();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void leggTilSykkel(){
        
    }
}