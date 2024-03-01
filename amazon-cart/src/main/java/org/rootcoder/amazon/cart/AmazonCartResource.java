package org.rootcoder.amazon.cart;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.json.JsonObject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Path("/cart")
public class AmazonCartResource {
    
    private static final Logger logger = LoggerFactory.getLogger(AmazonCartResource.class.getSimpleName());
    List<AmazonItem> items = new ArrayList<>();

    @GET
    @PermitAll
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItems(){
        return  Response.ok(items).build();
    }

    @POST
    @RolesAllowed({"admin", "master"})
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public  Response addItem( JsonObject item){
        
        var amazonItem = new AmazonItem(item);
        
                logger.info("Item has been testedjbkndk {}", amazonItem);

        
        items.add(amazonItem);
        logger.info("Item has been added successfully {}", items);
        return  Response.ok(items).build();
    }

    @DELETE
        @RolesAllowed("admin")

    @Path("{id}")
    public Response deleteItem(@PathParam("id")Long id){
        items.stream()
                .filter(amazonItem -> amazonItem.id().equals(id))
                .findFirst()
                .ifPresent(amazonItem -> items.remove(amazonItem));

        return  Response.noContent().build();
    }
}
