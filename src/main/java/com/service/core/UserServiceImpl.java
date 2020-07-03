package com.service.core;

import com.google.inject.Inject;
import com.service.ServiceConfiguration;
import com.service.api.beans.*;
import com.service.api.dao.UserDao;

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
        if(requestValidator(registerRequest)) {
            status = userDao.insertUser(registerRequest);
        }
        return generateMeta(new PostResponse(status));
    }

    //TODO: Func lambda
    private boolean requestValidator(RegisterRequest registerRequest) {
        return true;
    }

    @Override
    public ApiResponse login(String username, String password) {
        return generateMeta(userDao.validateUser(username, password));
    }

    @Override
    public ApiResponse friend(String username, String followerUsername) {
        return generateMeta(new PostResponse(userDao.insertFriend(username, followerUsername)));
    }
}
