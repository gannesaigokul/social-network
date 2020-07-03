package com.service.core.filters;

import com.service.api.beans.ApiResponse;
import com.service.api.beans.IBaseResponse;
import com.service.api.beans.Meta;
import io.dropwizard.jersey.errors.ErrorMessage;
import io.dropwizard.jersey.validation.ValidationErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;
import java.time.LocalDateTime;
import java.util.List;

import static com.service.api.constants.CommonConstants.RQ_CORRELATION_ID;
import static com.service.api.constants.CommonConstants.RQ_REQUEST_TIME;

@Provider
@ResponseMeta
public class ResponseMetaFilter extends BaseFilter implements ContainerResponseFilter {
    private static final Logger logger = LoggerFactory.getLogger(ResponseMetaFilter.class);

    @Context
    private HttpServletRequest servletRequestProvider;

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
        logger.debug("ResponseMetaFilter invoked");
        setResponseHeader(responseContext);
        setResponseEntity(responseContext);
    }

    private void setResponseEntity(ContainerResponseContext responseContext) {
        IBaseResponse baseResponse = null;
        ValidationErrorMessage validationErrorMessage;
        ErrorMessage errorMessage;
        String statusMsg = "FAILED";
        int statusCode = 500;
        if (responseContext.getEntity() instanceof IBaseResponse) {
            statusMsg = "SUCCESS";
            statusCode = 200;
            baseResponse = (IBaseResponse) responseContext.getEntity();
        } else if (responseContext.getEntity() instanceof ValidationErrorMessage) {
            validationErrorMessage = (ValidationErrorMessage) responseContext.getEntity();
            List<String> errors = validationErrorMessage.getErrors();
            statusMsg = errors == null ? "FAILED" : errors.toString();
            statusCode = 500;
            logger.error("Validation error {}", statusMsg);
        } else if (responseContext.getEntity() instanceof ErrorMessage) {
            errorMessage = (ErrorMessage) responseContext.getEntity();
            statusMsg = errorMessage == null ? "FAILED" : errorMessage.getMessage();
            statusCode = 500;
            logger.error("Error message {}", statusMsg);
        }
        HttpServletRequest servletRequest = servletRequestProvider;
        long startTime = (Long) servletRequest.getAttribute(RQ_REQUEST_TIME);
        long endTime = System.currentTimeMillis();
        Meta meta = Meta.builder().timeTaken(endTime - startTime).correlationId((String) servletRequest.getAttribute(RQ_CORRELATION_ID))
                .timeStamp(LocalDateTime.now().toString()).status(statusCode).message(statusMsg).build();

        ApiResponse apiResponse = ApiResponse.builder().data(baseResponse).meta(meta).build();
        responseContext.setEntity(apiResponse);

        logResponseTime(apiResponse);
    }

    private void logResponseTime(ApiResponse responseBean) {
        HttpServletRequest servletRequest = servletRequestProvider;
        if (null != servletRequest.getAttribute(RQ_REQUEST_TIME)) {
            long endTime = System.currentTimeMillis();
            long startTime = (Long) servletRequest.getAttribute(RQ_REQUEST_TIME);
            logger.info("status={} message={} responsetime={} requestmethod={} uri={}", responseBean.getMeta().getStatus(),
                    responseBean.getMeta().getMessage(), endTime - startTime, servletRequest.getMethod(), servletRequest.getRequestURI());
        }
    }

    private void setResponseHeader(ContainerResponseContext responseContext) {
        responseContext.getHeaders().add("X-Powered-By", "Travelguru Microservices");
    }

}
