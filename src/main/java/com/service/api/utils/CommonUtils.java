package com.service.api.utils;

import com.service.api.beans.ApiResponse;
import com.service.api.beans.IBaseResponse;
import com.service.api.beans.Meta;

import static com.service.api.constants.CommonConstants.RESPONSE_SUCCESS_CODE;
import static com.service.api.constants.CommonConstants.RESPONSE_SUCCESS_MSG;
import static java.time.LocalDateTime.now;

public class CommonUtils {

    public static ApiResponse generateMeta(IBaseResponse response) {
        ApiResponse apiResponse = new ApiResponse();
        Meta meta = Meta.builder().message(RESPONSE_SUCCESS_MSG).status(RESPONSE_SUCCESS_CODE).timeStamp(now().toString()).build();
        apiResponse.setData(response);
        apiResponse.setMeta(meta);
        return apiResponse;
    }

}
