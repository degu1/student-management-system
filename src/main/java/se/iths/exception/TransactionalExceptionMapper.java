package se.iths.exception;

import javax.transaction.TransactionalException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class TransactionalExceptionMapper implements ExceptionMapper<TransactionalException> {
    @Override
    public Response toResponse(TransactionalException e) {
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity("Constraint violation")
                .type(MediaType.TEXT_PLAIN_TYPE)
                .build();
    }
}
