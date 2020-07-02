package com.service.api.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequest {

    @JsonProperty("userName")
    private String userName;

    @JsonProperty("message")
    private String message;

}
