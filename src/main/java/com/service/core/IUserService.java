package com.service.core;

import com.service.api.beans.ApiResponse;
import com.service.api.beans.RegisterRequest;

public interface IUserService {

    ApiResponse register(RegisterRequest registerRequest);

    String login();

    String friend();
}
