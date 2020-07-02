package com.service.resources;

import com.google.inject.Inject;
import com.service.ServiceConfiguration;
import com.service.core.IUserService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/service/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    private final IUserService userService;
    private final ServiceConfiguration configuration;

    @Inject
    public UserResource(final IUserService userService, ServiceConfiguration configuration) {
        this.userService = userService;
        this.configuration = configuration;
    }

    @POST
    @Path("/register")
    public String registerUser(/*@Suspended final AsyncResponse asyncResponse,*/
                             @QueryParam("name") String name) {
/*        CompletableFuture.runAsync(() -> service.register(configuration.getName() + name))
                .thenApply(asyncResponse::resume)
                .exceptionally(e -> asyncResponse.resume(Response.status(500).entity(e).build()));*/
        return userService.register(configuration.getName() + name);
    }

    @GET
    @Path("/login")
    public String loginUser() {
        return userService.login();
    }

    @PUT
    @Path("/follow")
    public String followRequest() {
        return userService.follow();
    }

}
