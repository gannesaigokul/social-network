package com.service.api.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class DataFeed {

    @JsonProperty("postId")
    private Integer postId;

    @JsonProperty("userName")
    private String userName;

    @JsonProperty("post")
    private String post;

    @JsonProperty("creationTime")
    private String creationTime;

}
