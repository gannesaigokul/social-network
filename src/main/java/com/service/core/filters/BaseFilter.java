package com.service.core.filters;

import com.google.common.base.Splitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.UUID;

import static com.service.api.constants.CommonConstants.*;

public class BaseFilter {

    private static final Logger logger = LoggerFactory.getLogger(BaseFilter.class);

    protected String getCorrelationId(Map<String, String> headerMap) {
        String correlationId = headerMap.get(X_CORRELATION_ID);
        return correlationId == null ? UUID.randomUUID().toString() : correlationId;
    }

    protected String getIpAddress(HttpServletRequest request, Map<String, String> headerMap) {
        String ip = null;
        try {
            if (headerMap.get(X_FORWARDED_FOR) != null)
                ip = Splitter.on(',').trimResults().omitEmptyStrings().split(request.getHeader(X_FORWARDED_FOR)).iterator().next();
            if (ip == null) {
                ip = request.getRemoteAddr();
            }
        } catch (Exception e) {
            logger.error("Unable to get IP", e);
        }
        logger.info("client IP is {}", ip);
        return ip;
    }

    protected String getApiKey(Map<String, String> headerMap) {
        return headerMap.get(X_API_KEY);
    }

}