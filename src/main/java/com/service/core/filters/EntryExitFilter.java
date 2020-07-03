package com.service.core.filters;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.Map;

import static com.service.api.constants.CommonConstants.*;
import static com.service.api.utils.CommonUtils.getRequestHeaders;

@Provider
@EntryExit
@Priority(Integer.MIN_VALUE)
public class EntryExitFilter extends BaseFilter implements ContainerRequestFilter, ContainerResponseFilter {
    private static Logger logger = LoggerFactory.getLogger(EntryExitFilter.class);

    @Context
    private com.google.inject.Provider<HttpServletRequest> servletRequestProvider;

    @Inject
    com.google.inject.Provider<HttpHeaders> httpHeadersProvider;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        logger.debug("EntryFilter invoked");
        HttpServletRequest servletRequest = servletRequestProvider.get();
        HttpHeaders httpHeaders = httpHeadersProvider.get();
        Map<String, String> requestHeaders = getRequestHeaders(httpHeadersProvider.get().getRequestHeaders());
        String id = getCorrelationId(requestHeaders);
        String apiKey = getApiKey(requestHeaders);
        String ip = getIpAddress(servletRequest, requestHeaders);
        httpHeaders.getRequestHeaders().putSingle(X_CORRELATION_ID, id);
        servletRequest.setAttribute(RQ_REQUEST_TIME, System.currentTimeMillis());
        httpHeaders.getRequestHeaders().putSingle(IP_ADDRESS, ip);
        setMDC(servletRequest);

        if (StringUtils.isBlank(apiKey)) {
            throw new WebApplicationException(Response.Status.UNAUTHORIZED);
        }

        logger.info("requestmethod={} uri={}", servletRequest.getMethod(), servletRequest.getRequestURI());
    }

    private void setMDC(HttpServletRequest servletRequest) {
        Map<String, String> requestHeaders = getRequestHeaders(httpHeadersProvider.get().getRequestHeaders());
        String correlationId = getCorrelationId(requestHeaders);
        MDC.put(X_CORRELATION_ID, correlationId);
        String ip = getIpAddress(servletRequest, requestHeaders);
        MDC.put(IP_ADDRESS, ip);
        MDC.put(X_API_KEY, getApiKey(requestHeaders));
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
        logger.debug("ExitFilter invoked");
        MDC.clear();
    }
}

