package se.iths.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
public class ExceptionMapper implements javax.ws.rs.ext.ExceptionMapper<Exception> {
    @Override
    public Response toResponse(Exception e) {
        return Response.serverError()
                .type(MediaType.APPLICATION_JSON)
                .entity(ExceptionDTO.of(e))
                .build();
    }
}
