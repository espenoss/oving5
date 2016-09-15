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
    
    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    public void addSykkel(Sykkel sykkel){
        sykler.put(sykkel.getNr(), sykkel);
    }
    
    @POST
    @Path("/{sykkelNr}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void endreSykkel(@PathParam("sykkelNr") String sykkelNr, Sykkel sykkel){
        sykler.replace(sykkelNr, sykkel);
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Collection<Sykkel> getSykler(){
        return sykler.values();
    }    
    
    @GET
    @Path("/{sykkelNr}")
    @Produces({MediaType.APPLICATION_JSON})
    public Sykkel getSykkel(@PathParam("sykkelNr") String sykkelNr) throws NotFoundException{
        Sykkel sykkel = sykler.get(sykkelNr);
        if(sykkel == null){
            throw new NotFoundException(); 
        }else{
            return sykkel;
        }
    }
    
    @DELETE
    @Path("/{sykkelNr}")
    public void removeSykkel(@PathParam("sykkelNr") String sykkelNr){
        sykler.remove(sykkelNr);
    }
}