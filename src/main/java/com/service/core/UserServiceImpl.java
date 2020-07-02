package com.service.core;

import com.google.inject.Inject;
import com.service.ServiceConfiguration;
import com.service.api.beans.ApiResponse;
import com.service.api.beans.RegisterRequest;
import com.service.api.beans.RegisterResponse;
import com.service.api.constants.SqlConstants;
import com.service.api.dao.UserDao;

import java.util.HashMap;
import java.util.Map;

import static com.service.api.constants.CommonConstants.RESPONSE_SUCCESS_CODE;
import static com.service.api.constants.CommonConstants.RESPONSE_SUCCESS_MSG;
import static com.service.api.utils.CommonUtils.generateMeta;

public class UserServiceImpl implements IUserService{

/*
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
*/

    @Inject
    private UserDao userDao;

    @Inject
    private ServiceConfiguration configuration;

    @Override
    public ApiResponse register(RegisterRequest registerRequest) {
        boolean status = false;
        if(validate(registerRequest)) {
            status = userDao.registerUser(registerRequest);
        }
        return generateMeta(new RegisterResponse(status), RESPONSE_SUCCESS_MSG, RESPONSE_SUCCESS_CODE);
    }

    private boolean validate(RegisterRequest registerRequest) {
        return true;
    }

    @Override
    public String login() {
        return null;
    }

    @Override
    public String friend() {
        return null;
    }
}
