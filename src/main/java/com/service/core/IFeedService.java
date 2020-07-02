package com.service.core;

import com.service.api.beans.ApiResponse;
import com.service.api.beans.PostRequest;

public interface IFeedService {
    String profile();

    String getFeed();

    ApiResponse write(PostRequest postRequest);
}
