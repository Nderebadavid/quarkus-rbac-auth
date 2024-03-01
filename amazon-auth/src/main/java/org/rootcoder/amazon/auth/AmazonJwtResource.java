/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.rootcoder.amazon.auth;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author coder
 */
@Path("/jwt")
@ApplicationScoped
public class AmazonJwtResource {

    @Inject
    AmazonJwtService jwtService;



    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJwt()
    {
        String jwt = jwtService.generateJwt();
        return Response.ok(jwt).build();
    }

}
