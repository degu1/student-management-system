package se.iths.rest;

import se.iths.exception.ConnectedException;
import se.iths.exception.IdNotFoundException;
import se.iths.exception.NoConnectionWithEntityException;

import javax.ws.rs.core.Response;

@FunctionalInterface
public interface ExceptionWrapper {
    Response wrapper() throws IdNotFoundException, ConnectedException, NoConnectionWithEntityException;
}
