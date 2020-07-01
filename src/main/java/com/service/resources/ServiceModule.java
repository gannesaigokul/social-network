package com.service.resources;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.service.core.FeedServiceImpl;
import com.service.core.IFeedService;
import com.service.core.IUserService;
import com.service.core.UserServiceImpl;

public class ServiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(IUserService.class).to(UserServiceImpl.class).in(Scopes.SINGLETON);
        bind(IFeedService.class).to(FeedServiceImpl.class).in(Scopes.SINGLETON);
    }

}
