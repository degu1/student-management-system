package se.iths.rest;

import se.iths.exception.ConnectedException;
import se.iths.exception.IdNotFoundException;
import se.iths.exception.NoConnectionWithEntityException;
import se.iths.service.Service;

import javax.transaction.TransactionalException;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

import static se.iths.rest.ExceptionWrapperImpl.execute;

public abstract class CRUDRest<T, U extends Service<T>> {

    protected U service;

    @POST()
    public Response create(T t) throws ConnectedException, NoConnectionWithEntityException, IdNotFoundException, TransactionalException {
        return execute(() -> {
            service.create(t);
            return Response.ok(t).build();
        });
    }

    @PUT
    public Response updateWithPUT(T t) throws ConnectedException, NoConnectionWithEntityException, IdNotFoundException {
        return execute(() -> {
            service.updateWithPUT(t);
            return Response.ok().build();
        });
    }

    @PATCH
    public Response updateWithPATCH(T t) throws ConnectedException, NoConnectionWithEntityException, IdNotFoundException {
        return execute(() -> {
            service.updateWithPATCH(t);
            return Response.ok().build();
        });
    }

    @GET
    public Response getAll() throws ConnectedException, NoConnectionWithEntityException, IdNotFoundException {
        return execute(() -> {
            List<T> all = service.getAll();
            return Response.ok(all).build();
        });
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) throws ConnectedException, NoConnectionWithEntityException, IdNotFoundException {
        return execute(() -> Response.ok(service.getById(id)).build());
    }

    @DELETE
    @Path("/{id}")
    public Response Remove(@PathParam("id") Long id) throws ConnectedException, NoConnectionWithEntityException, IdNotFoundException {
        return execute(() -> {
            service.remove(id);
            return Response.ok().build();
        });
    }
}
