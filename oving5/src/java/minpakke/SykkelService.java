package minpakke;

import java.util.ArrayList;
import java.util.Random;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/sykkelService/")
public class SykkelService {
    
    
    private static ArrayList<Sykkel> sykler = new ArrayList<Sykkel>(){{
        add(new Sykkel("1", "Dragvoll"));
        add(new Sykkel("2", "Gløshaugen"));
    }};
    
    private static ArrayList<Kunde> kunder = new ArrayList<Kunde>(){{
       add(new Kunde("espen", "e@google.no"));
       add(new Kunde("maria", "@hotmail.ru"));
    }};
  
    @PUT
    @Path("/kunder/")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.TEXT_PLAIN})
    public String leggTilKunde(Kunde kunde){
        for(Kunde k: kunder){
            if(k.getBrukerNavn().equals(kunde.getBrukerNavn())){
                return "Brukernavn eksisterer allerede";
            }
        }
        kunder.add(kunde);
        return "Kunde registrert";
    }
    
    @GET
    @Path("/kunder/")    
    @Produces({MediaType.APPLICATION_JSON})
    public ArrayList<Kunde> getKunder(){
        return kunder;
    }
    
    @GET
    @Path("/kunder/{brukerNavn}/")
    @Produces({MediaType.APPLICATION_JSON})
    public Kunde getKunde(@PathParam("brukerNavn") String brukerNavn){
        
        for(Kunde k:kunder){
            if(k.getBrukerNavn().equals(brukerNavn)){
                return k;
            }
        }
        throw new NotFoundException();
    }
    
    @POST
    @Path("/kunder/{brukerNavn}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String leggTilBestilling(@PathParam("brukerNavn") String brukerNavn, Sykkel sykkel){
        
        Kunde kunde = null;
        
        for(Kunde k:kunder){
            if(k.getBrukerNavn().equals(brukerNavn)){
                kunde = k;
            }
        }
        if(kunde == null){
            throw new NotFoundException();            
        }
        
        int kode = new Random().nextInt(1000);
        String kodeString = Integer.toString(kode);
        
        String dato = new java.util.Date().toString();

        kunde.getBestillinger().add(new Bestilling(dato, kodeString, sykkel));
        
        return kodeString;
    }
    
    @GET
    @Path("kunder/{brukerNavn}/bestillinger")
    @Produces({MediaType.APPLICATION_JSON})
    public ArrayList<Bestilling> getBestillinger(@PathParam("brukerNavn") String brukerNavn){
        
        for(Kunde k:kunder){
            if(k.getBrukerNavn().equals(brukerNavn)){
                return k.getBestillinger();
            }
        }
        throw new NotFoundException();
    }
    
    @PUT
    @Path("/sykler/")
    @Consumes({MediaType.TEXT_PLAIN})
    public void addSykkel(String plassering){
        int sisteNummer = Integer.parseInt(sykler.get(sykler.size()-1).getNr()); // Hent siste nummer
        String nyttNr = Integer.toString(++sisteNummer);              // inkrementer med en og konverter til string
        Sykkel nySykkel= new Sykkel(nyttNr, plassering);
        sykler.add(nySykkel);
    }
    
    @POST
    @Path("/sykler/{sykkelNr}/reserver")
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
    @Path("/sykler/{sykkelNr}/lever")
    @Consumes({MediaType.TEXT_PLAIN})
    @Produces({MediaType.TEXT_PLAIN})
    public String leverSykkel(@PathParam("sykkelNr") String sykkelNr, String plassering){
        for(Sykkel s: sykler){
            if(s.getNr().equals(sykkelNr)){
                s.setLedig("Ledig");
                s.setPlassering(plassering);
                return "Sykkel levert";
            }
        }
        return "Sykkelnummer ikke funnet";
    }
    
    @POST
    @Path("/sykler/{sykkelNr}/hent")
    public void hentSykkel(@PathParam("sykkelNr") String sykkelNr){
        for(Sykkel s: sykler){
            if(s.getNr().equals(sykkelNr)){
                s.setPlassering("Ute");
                return;
            }
        }
        throw new NotFoundException();
    }
    
    @GET
    @Path("/sykler/")
    @Produces({MediaType.APPLICATION_JSON})
    public ArrayList<Sykkel> getSykler(){
        return sykler;
    }    
    
    @GET
    @Path("/sykler/{plassering}")
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