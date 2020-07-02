package com.service.resources;

import com.service.ServiceConfiguration;
import com.service.api.beans.ApiResponse;
import com.service.api.beans.PostRequest;
import com.service.core.IFeedService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/service/feed")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FeedResource {

    private final IFeedService feedService;
    private final ServiceConfiguration configuration;

    public FeedResource(IFeedService feedService, ServiceConfiguration configuration) {
        this.feedService = feedService;
        this.configuration = configuration;
    }

    @POST
    @Path("/write")
    public ApiResponse write(/*@Suspended final AsyncResponse asyncResponse,*/
            @BeanParam PostRequest postRequest) {
/*        CompletableFuture.runAsync(() -> service.register(configuration.getName() + name))
                .thenApply(asyncResponse::resume)
                .exceptionally(e -> asyncResponse.resume(Response.status(500).entity(e).build()));*/
        return feedService.write(postRequest);
    }

    @GET
    public String getFeed(@QueryParam("username") String username) {
        return feedService.getFeed();
    }

    @GET
    @Path("/profile")
    public String profile() {
        return feedService.profile();
    }

}
