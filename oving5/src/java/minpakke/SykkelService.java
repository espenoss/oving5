/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minpakke;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/melding/")

public class SykkelService {
    
    private static String serverMelding = new String("test");

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getMelding() {
        return serverMelding;
    }

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public void endreMelding(String nyMelding) {
        serverMelding = nyMelding;
    }   
}