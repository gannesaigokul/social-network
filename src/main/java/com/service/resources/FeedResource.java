package com.service.resources;

import com.google.inject.Inject;
import com.service.ServiceConfiguration;
import com.service.api.beans.PostRequest;
import com.service.core.IFeedService;

import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;

@Path("/service/feed")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FeedResource {

    private final IFeedService feedService;

    @Inject
    public FeedResource(IFeedService feedService, ServiceConfiguration configuration) {
        this.feedService = feedService;
    }

    @POST
    @Path("/write")
    public void write(@Suspended final AsyncResponse asyncResponse,
                      PostRequest postRequest) {
        asyncResponse.resume(feedService.write(postRequest));
    }

    @GET
    public void getFeed(@Suspended final AsyncResponse asyncResponse,
                          @QueryParam("username") String username) {
        asyncResponse.resume(feedService.getFeed(username));
    }

    @GET
    @Path("/profile")
    public void profile(@Suspended final AsyncResponse asyncResponse,
                          @QueryParam("username") String username) {
        asyncResponse.resume(feedService.profile(username));

    }

}
