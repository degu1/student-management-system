package se.iths.rest;

import se.iths.exception.ConnectedException;
import se.iths.exception.IdNotFoundException;
import se.iths.exception.NoConnectionWithEntityException;

import javax.transaction.TransactionalException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class ExceptionWrapperImpl {
    public static Response execute(ExceptionWrapper exceptionWrapper) throws IdNotFoundException, NoConnectionWithEntityException, ConnectedException {
        try {
            return exceptionWrapper.wrapper();
        } catch (IdNotFoundException | NoConnectionWithEntityException | ConnectedException | TransactionalException exception) {
            throw exception;
        } catch (Exception e) {
            throw new WebApplicationException();
        }
    }
}
