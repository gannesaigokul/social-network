package com.service.api.utils;

import com.service.api.beans.ApiResponse;
import com.service.api.beans.IBaseResponse;
import com.service.api.beans.Meta;

import static java.time.LocalDateTime.now;

public class CommonUtils {

    public static ApiResponse generateMeta(IBaseResponse response, String message, int status) {
        ApiResponse apiResponse = new ApiResponse();
        Meta meta = Meta.builder().message(message).status(status).timeStamp(now().toString()).build();
        apiResponse.setData(response);
        apiResponse.setMeta(meta);
        return apiResponse;
    }

}
