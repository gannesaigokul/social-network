package com.service.resources;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Scopes;
import com.google.inject.Singleton;
import com.service.ServiceConfiguration;
import com.service.core.FeedServiceImpl;
import com.service.core.IFeedService;
import com.service.core.IUserService;
import com.service.core.UserServiceImpl;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ServiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(IUserService.class).to(UserServiceImpl.class).in(Scopes.SINGLETON);
        bind(IFeedService.class).to(FeedServiceImpl.class).in(Scopes.SINGLETON);
    }

    @Provides
    @Singleton
    public DBI getJdbi(ServiceConfiguration configuration, Environment environment) {
        final DBIFactory factory = new DBIFactory();
        return factory.build(environment, configuration.getDatabase(), "sqlserver");
    }

    @Provides
    @Singleton
    public Connection getConnection(ServiceConfiguration configuration, Environment environment) throws SQLException {
        return DriverManager.getConnection(configuration.getDatabase().getUrl());
    }

}
