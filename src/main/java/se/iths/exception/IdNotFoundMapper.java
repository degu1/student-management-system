package se.iths.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class IdNotFoundMapper implements ExceptionMapper<IdNotFoundException> {
    @Override
    public Response toResponse(IdNotFoundException e) {
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(ExceptionDTO.of(e))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}

