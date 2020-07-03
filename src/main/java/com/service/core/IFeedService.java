package com.service.core;

import com.service.api.beans.ApiResponse;
import com.service.api.beans.PostRequest;

public interface IFeedService {
    ApiResponse profile(String username);

    ApiResponse getFeed(String username, int page);

    ApiResponse write(PostRequest postRequest);
}
