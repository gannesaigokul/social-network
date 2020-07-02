package com.service.resources;

import com.google.inject.Inject;
import com.service.ServiceConfiguration;
import com.service.api.beans.ApiResponse;
import com.service.api.beans.RegisterRequest;
import com.service.core.IUserService;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.concurrent.CompletableFuture;

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
    public void registerUser(@Suspended final AsyncResponse asyncResponse,
            RegisterRequest registerRequest) {
        //System.out.println(registerRequest.toString());
/*        CompletableFuture.runAsync(() -> userService.register(registerRequest))
                .thenApply(res -> asyncResponse.resume(res))
                .exceptionally(e -> asyncResponse.resume(Response.status(500).entity(e).build()));*/
        asyncResponse.resume(userService.register(registerRequest));
        //return userService.register(registerRequest);
    }

    @GET
    @Path("/login")
    public String loginUser() {
        return userService.login();
    }

    @PUT
    @Path("/friend")
    public String friendRequest() {
        return userService.friend();
    }

}
