package com.service.api.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import static com.service.api.constants.CommonConstants.RESPONSE_DB_WRITE_FAIL;
import static com.service.api.constants.CommonConstants.RESPONSE_DB_WRITE_SUCCESS;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
@AllArgsConstructor
public class PostResponse implements IBaseResponse{

    @JsonProperty("isSuccess")
    private Boolean success;

    @JsonProperty("message")
    private String message;

    public PostResponse(Boolean success) {
        this.success = success;
        this.message = success ? RESPONSE_DB_WRITE_SUCCESS : RESPONSE_DB_WRITE_FAIL;
    }

}
