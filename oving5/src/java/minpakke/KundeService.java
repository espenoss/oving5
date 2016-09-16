package minpakke;

import java.util.ArrayList;
import java.util.Random;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path("/sykkelService/kunder/")
public class KundeService{

    private static ArrayList<Kunde> kunder = new ArrayList<Kunde>(){{
       add(new Kunde("espen", "e@google.no"));
       add(new Kunde("maria", "@hotmail.ru"));
    }};
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public ArrayList<Kunde> getKunder(){
        return kunder;
    }

    @GET
    @Path("/{brukerNavn}/")
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
    @Path("/{brukerNavn}")
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
    @Path("/{brukerNavn}/bestillinger")
    @Produces({MediaType.APPLICATION_JSON})
    public ArrayList<Bestilling> getBestillinger(@PathParam("brukerNavn") String brukerNavn){
        
        for(Kunde k:kunder){
            if(k.getBrukerNavn().equals(brukerNavn)){
                return k.getBestillinger();
            }
        }
        throw new NotFoundException();
    }
}
