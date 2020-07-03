package com.service.core;

import com.google.inject.Inject;
import com.service.ServiceConfiguration;
import com.service.api.beans.PostResponse;
import com.service.api.beans.RegisterRequest;
import com.service.api.beans.UserResponse;
import com.service.api.dao.UserDao;

public class UserServiceImpl implements IUserService{

/*
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
*/

    @Inject
    private UserDao userDao;

    @Inject
    private ServiceConfiguration configuration;

    @Override
    public PostResponse register(RegisterRequest registerRequest) {
        boolean status = false;
        if(requestValidator(registerRequest)) {
            status = userDao.insertUser(registerRequest);
        }
        /*return generateMeta(new PostResponse(status));*/
        return new PostResponse(status);
    }

    //TODO: Func lambda
    private boolean requestValidator(RegisterRequest registerRequest) {
        return true;
    }

    @Override
    public UserResponse login(String username, String password) {
        /*return generateMeta(userDao.validateUser(username, password));*/
        return userDao.validateUser(username, password);
    }

    @Override
    public PostResponse friend(String username, String followerUsername) {
        /*return generateMeta(new PostResponse(userDao.insertFriend(username, followerUsername)));*/
        return new PostResponse(userDao.insertFriend(username, followerUsername));
    }
}
