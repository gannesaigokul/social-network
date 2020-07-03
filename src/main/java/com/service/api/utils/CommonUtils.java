package com.service.api.utils;

import javax.ws.rs.core.MultivaluedMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CommonUtils {

/*    public static ApiResponse generateMeta(IBaseResponse response) {
        ApiResponse apiResponse = new ApiResponse();
        Meta meta = Meta.builder().message(RESPONSE_SUCCESS_MSG).status(RESPONSE_SUCCESS_CODE).timeStamp(now().toString()).build();
        apiResponse.setData(response);
        apiResponse.setMeta(meta);
        return apiResponse;
    }*/

    public static Map<String, String> getRequestHeaders(MultivaluedMap<String, String> multivaluedMap) {
        return multivaluedMap.keySet().stream().collect(Collectors.toMap(x -> x, multivaluedMap::getFirst));
    }

}
