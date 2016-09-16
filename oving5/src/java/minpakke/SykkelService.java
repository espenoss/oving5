package minpakke;

import java.util.ArrayList;
import java.util.Random;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/sykkelService/sykler")
public class SykkelService {
    
    
    private static ArrayList<Sykkel> sykler = new ArrayList<Sykkel>(){{
        add(new Sykkel("1", "Dragvoll"));
        add(new Sykkel("2", "Glos"));
    }};
     
  
   @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    public void addSykkel(Sykkel sykkel){
        sykler.add(sykkel);
    }
    
    @POST
    @Path("/{sykkelNr}/reserver")
    @Consumes({MediaType.APPLICATION_JSON})
    public void reserverSykkel(@PathParam("sykkelNr") String sykkelNr){
        for(Sykkel s: sykler){
            if(s.getNr().equals(sykkelNr)){
                s.setLedig("Opptatt");
                return;
            }
        }
        throw new NotFoundException();
    }

    @POST
    @Path("/{sykkelNr}/lever")
    @Consumes({MediaType.APPLICATION_JSON})
    public void leverSykkel(@PathParam("sykkelNr") String sykkelNr, String plassering){
        for(Sykkel s: sykler){
            if(s.getNr().equals(sykkelNr)){
                s.setLedig("Ledig");
                s.setPlassering(plassering);
                return;
            }
        }
        throw new NotFoundException();
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public ArrayList<Sykkel> getSykler(){
        return sykler;
    }    
    
    @GET
    @Path("/{plassering}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Sykkel getLedigSykkel(@PathParam("plassering") String plassering){
        
        for(Sykkel s: sykler){
            if(s.getPlassering().equals(plassering) && s.getLedig().equals("Ledig")){
                return s;
            }
        }
        throw new NotFoundException();
    }
    
}