package com.service.core;

import com.google.inject.Inject;
import com.service.ServiceConfiguration;
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
    public String register(String name1) {

        return "Hello " + name1;
    }

    @Override
    public String login() {
        return null;
    }

    @Override
    public String follow() {
        return null;
    }
}
