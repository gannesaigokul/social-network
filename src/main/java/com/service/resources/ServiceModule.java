package com.service.resources;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.service.core.IUserService;
import com.service.core.UserServiceImpl;

public class ServiceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(IUserService.class).to(UserServiceImpl.class).in(Scopes.SINGLETON);
    }
}
