package com.service.resources;

import com.google.inject.Inject;
import com.service.ServiceConfiguration;
import com.service.core.IFeedService;
import jdk.nashorn.internal.objects.annotations.Getter;

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
    public String write(/*@Suspended final AsyncResponse asyncResponse,*/
            @QueryParam("name") String name) {
/*        CompletableFuture.runAsync(() -> service.register(configuration.getName() + name))
                .thenApply(asyncResponse::resume)
                .exceptionally(e -> asyncResponse.resume(Response.status(500).entity(e).build()));*/
        return feedService.write(configuration.getName() + name);
    }

    @GET
    public String getFeed() {
        return feedService.getFeed();
    }

    @GET
    @Path("/profile")
    public String profile() {
        return feedService.profile();
    }

}
