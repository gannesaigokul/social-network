package com.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceConfiguration extends Configuration {

    @JsonProperty("name")
    private String name;

}
