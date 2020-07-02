package com.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceConfiguration extends Configuration {

    @JsonProperty("name")
    private String name;

    private DataSourceFactory database;

}
