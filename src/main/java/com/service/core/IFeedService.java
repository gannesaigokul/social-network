package com.service.core;

import com.service.api.beans.ApiResponse;
import com.service.api.beans.PostRequest;
import com.service.api.beans.ProfileResponse;

public interface IFeedService {
    ApiResponse profile(String username);

    ApiResponse getFeed(String username);

    ApiResponse write(PostRequest postRequest);
}
