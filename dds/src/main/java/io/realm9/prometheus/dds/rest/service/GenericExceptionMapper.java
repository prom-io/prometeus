package io.realm9.prometheus.dds.rest.service;

import io.realm9.prometheus.dds.rest.response.ErrorResponse;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

public class GenericExceptionMapper implements ExceptionMapper<Throwable> {
    @Override
    public Response toResponse(Throwable ex) {
        var errorMessage = new ErrorResponse();
        setHttpStatus(ex, errorMessage);
        errorMessage.setDetail(ex.getMessage());

        var errorStackTrace = new StringWriter();
        ex.printStackTrace(new PrintWriter(errorStackTrace));

        return Response.status(Integer.parseInt(errorMessage.getStatus()))
                .entity(Map.of("errors", List.of(errorMessage)))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    private void setHttpStatus(Throwable ex, ErrorResponse errorMessage) {
        if (ex instanceof WebApplicationException) {
            errorMessage.setStatus(String.valueOf(((WebApplicationException) ex).getResponse().getStatus()));
            errorMessage.setTitle(((WebApplicationException) ex).getResponse().getStatusInfo().getReasonPhrase());
        } else {
            //defaults to internal server error 500
            errorMessage.setStatus(String.valueOf(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()));
            errorMessage.setTitle(Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
    }
}
