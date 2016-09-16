package minpakke;

import java.util.ArrayList;
import java.util.Random;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/sykler/")
public class SykkelService {
    
    private static ArrayList<Sykkel> sykler = new ArrayList<Sykkel>(){{
        add(new Sykkel("1", "Dragvoll"));
    }};
    
    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    public void addSykkel(Sykkel sykkel){
        sykler.add(sykkel);
    }
    
    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Bestilling opprettBestilling(String plassering, String dato){
        Sykkel ledigSykkel = null;
        for(Sykkel s: sykler){
            if(s.getLedig().equals("Ledig") && s.getPlassering().equals(plassering)){
                ledigSykkel = s;
            }
        }

        int kode = new Random().nextInt(1000);
        String kodeString = Integer.toString(kode);
        
        return new Bestilling(dato, kodeString, ledigSykkel);
    }
    
    
    @POST
    @Path("/{sykkelNr}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void endreSykkel(@PathParam("sykkelNr") String sykkelNr, Sykkel sykkel){
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public ArrayList<Sykkel> getSykler(){
        return sykler;
    }    
    
}