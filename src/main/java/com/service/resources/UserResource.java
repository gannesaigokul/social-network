package com.service.resources;

import com.google.inject.Inject;
import com.service.ServiceConfiguration;
import com.service.core.IUserService;

import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.concurrent.CompletableFuture;

@Path("/service")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    private final IUserService service;
    private final ServiceConfiguration configuration;

    @Inject
    public UserResource(final IUserService service, ServiceConfiguration configuration) {
        this.service = service;
        this.configuration = configuration;
    }

    @GET
    @Path("/name")
    public String registerUser(/*@Suspended final AsyncResponse asyncResponse,*/
                             @QueryParam("name") String name) {
/*        CompletableFuture.runAsync(() -> service.check(configuration.getName() + name))
                .thenApply(asyncResponse::resume)
                .exceptionally(e -> asyncResponse.resume(Response.status(500).entity(e).build()));*/
        return service.check(configuration.getName() + name);
    }

}
