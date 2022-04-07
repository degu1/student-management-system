package se.iths.rest;

import se.iths.entity.Subject;
import se.iths.exception.IdNotFoundException;
import se.iths.service.SubjectService;

import javax.inject.Inject;
import javax.transaction.TransactionalException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("subjects")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SubjectRest {

    @Inject
    SubjectService service;

    @POST()
    public Response createSubject(Subject subject) throws TransactionalException {
        try {
            service.createSubject(subject);
            return Response.ok(subject).build();
        } catch (TransactionalException transactionalException) {
            throw transactionalException;
        } catch (Exception e) {
            throw new WebApplicationException();
        }
    }

    @PUT
    public Response updateSubject(Subject subject) {
        try {
            service.updateSubject(subject);
            return Response.ok().build();
        } catch (TransactionalException transactionalException) {
            throw transactionalException;
        } catch (Exception e) {
            throw new WebApplicationException();
        }
    }

    @PATCH
    public Response patchSubject(Subject subject) throws IdNotFoundException {
        try {
            service.patchSubject(subject);
            return Response.ok().build();
        } catch (TransactionalException transactionalException) {
            throw transactionalException;
        } catch (IdNotFoundException idNotFoundException) {
            throw idNotFoundException;
        } catch (Exception e) {
            throw new WebApplicationException();
        }
    }

    @GET()
    public Response getAllSubjects() {
        try {
            List<Subject> subjects = service.getAllSubjects();
            return Response.ok(subjects).build();
        } catch (Exception e) {
            throw new WebApplicationException();
        }
    }

    @GET
    @Path("{id}")
    public Response getSubjectById(@PathParam("id") Long id) throws IdNotFoundException {
        try {
            return Response.ok(service.getSubjectsById(id)).build();
        } catch (IdNotFoundException idNotFoundException) {
            throw idNotFoundException;
        } catch (Exception e) {
            throw new WebApplicationException();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteSubjectById(@PathParam("id") Long id) throws IdNotFoundException {
        try {
            service.deleteSubject(id);
            return Response.ok().build();
        } catch (IdNotFoundException idNotFoundException) {
            throw idNotFoundException;
        } catch (Exception e) {
            throw new WebApplicationException();
        }
    }
}
