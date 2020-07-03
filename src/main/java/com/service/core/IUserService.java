package com.service.core;

import com.service.api.beans.PostResponse;
import com.service.api.beans.RegisterRequest;
import com.service.api.beans.UserResponse;

public interface IUserService {

    PostResponse register(RegisterRequest registerRequest);

    UserResponse login(String username, String password);

    PostResponse friend(String username, String followerUsername);

/*    ApiResponse register(RegisterRequest registerRequest);

    ApiResponse login(String username, String password);

    ApiResponse friend(String username, String followerUsername);*/
}
