package com.service.api.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
@Builder
public class Meta {

    @JsonProperty("timeTaken")
    private Long timeTaken;

    @JsonProperty("correlationId")
    private String correlationId;

    @JsonProperty("timeStamp")
    private String timeStamp;

    @JsonProperty("status")
    private Integer status;

    @JsonProperty("message")
    private String message;

}
