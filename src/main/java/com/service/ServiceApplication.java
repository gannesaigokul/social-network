package com.service;

import com.google.inject.Stage;
import com.hubspot.dropwizard.guice.GuiceBundle;
import com.service.resources.FeedResource;
import com.service.resources.ServiceModule;
import com.service.resources.UserResource;
import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class ServiceApplication extends Application<ServiceConfiguration> {

    @Override
    public void run(ServiceConfiguration serviceConfiguration, Environment environment) {
        environment.jersey().register(UserResource.class);
        environment.jersey().register(FeedResource.class);
/*        environment.jersey().getResourceConfig().register(EntryExitFilter.class);
        environment.jersey().getResourceConfig().register(ResponseMetaFilter.class);*/
    }

    @Override
    public void initialize(Bootstrap<ServiceConfiguration> bootstrap) {
        GuiceBundle<ServiceConfiguration> guiceBundle = GuiceBundle.<ServiceConfiguration>newBuilder()
                .addModule(new ServiceModule())
                .setConfigClass(ServiceConfiguration.class)
                /*.enableAutoConfig(getClass().getPackage().getName())*/
                .build(Stage.DEVELOPMENT);
        bootstrap.addBundle(guiceBundle);
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(), new EnvironmentVariableSubstitutor()));
    }

    public static void main(String[] args) throws Exception {
        new ServiceApplication().run(args);
    }

}
