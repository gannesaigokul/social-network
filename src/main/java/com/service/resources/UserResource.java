package com.service.resources;

import com.google.inject.Inject;
import com.service.ServiceConfiguration;
import com.service.api.beans.RegisterRequest;
import com.service.core.IUserService;

import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;

@Path("/service/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    private final IUserService userService;

    @Inject
    public UserResource(final IUserService userService, ServiceConfiguration configuration) {
        this.userService = userService;
    }

    @POST
    @Path("/register")
    public void registerUser(@Suspended final AsyncResponse asyncResponse,
                             RegisterRequest registerRequest) {
        asyncResponse.resume(userService.register(registerRequest));
    }

    @GET
    @Path("/login")
    public void loginUser(@Suspended final AsyncResponse asyncResponse,
                            @QueryParam("username") String username,
                            @QueryParam("password") String password) {
        asyncResponse.resume(userService.login(username, password));
    }

    @PUT
    @Path("/friend")
    public void friendRequest(@Suspended final AsyncResponse asyncResponse,
                              @QueryParam("username") String username,
                              @QueryParam("followerUsername") String followerUsername) {
        asyncResponse.resume(userService.friend(username, followerUsername));
    }

}
