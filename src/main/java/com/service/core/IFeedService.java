package com.service.core;

import com.service.api.beans.PostRequest;
import com.service.api.beans.PostResponse;
import com.service.api.beans.ProfileResponse;

public interface IFeedService {
    ProfileResponse profile(String username);

    ProfileResponse getFeed(String username, int page);

    PostResponse write(PostRequest postRequest);

/*    ApiResponse profile(String username);

    ApiResponse getFeed(String username, int page);

    ApiResponse write(PostRequest postRequest);*/
}
