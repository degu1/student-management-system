package se.iths.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NoConnectionWithEntityMapper implements ExceptionMapper<NoConnectionWithEntityException> {

    @Override
    public Response toResponse(NoConnectionWithEntityException e) {
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(ExceptionDTO.of(e))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
