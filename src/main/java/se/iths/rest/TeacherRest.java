package se.iths.rest;

import se.iths.entity.Teacher;
import se.iths.exception.IdNotFoundException;
import se.iths.service.TeacherService;

import javax.inject.Inject;
import javax.transaction.TransactionalException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("teachers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TeacherRest {

    @Inject
    TeacherService service;

    @POST()
    public Response createTeacher(Teacher teacher) throws TransactionalException {
        try {
            service.createTeacher(teacher);
            return Response.ok(teacher).build();
        } catch (TransactionalException transactionalException) {
            throw transactionalException;
        } catch (Exception e) {
            throw new WebApplicationException();
        }
    }

    @PUT
    public Response updateTeacher(Teacher teacher) {
        try {
            service.updateTeacher(teacher);
            return Response.ok().build();
        } catch (TransactionalException transactionalException) {
            throw transactionalException;
        } catch (Exception e) {
            throw new WebApplicationException();
        }
    }

    @PATCH
    public Response patchTeacher(Teacher teacher) throws IdNotFoundException {
        try {
            service.patchTeacher(teacher);
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
    public Response getAllTeachers() {
        try {
            List<Teacher> teachers = service.getAllTeacher();
            return Response.ok(teachers).build();
        } catch (Exception e) {
            throw new WebApplicationException();
        }
    }

    @GET
    @Path("{id}")
    public Response getTeacherById(@PathParam("id") Long id) throws IdNotFoundException {
        try {
            return Response.ok(service.getTeacherById(id)).build();
        } catch (IdNotFoundException idNotFoundException) {
            throw idNotFoundException;
        } catch (Exception e) {
            throw new WebApplicationException();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteTeacherById(@PathParam("id") Long id) throws IdNotFoundException {
        try {
            service.deleteTeacher(id);
            return Response.ok().build();
        } catch (IdNotFoundException idNotFoundException) {
            throw idNotFoundException;
        } catch (Exception e) {
            throw new WebApplicationException();
        }
    }

    @POST
    @Path("add_subject/{teacherId}/{subjectId}")
    public Response addSubject(@PathParam("teacherId") Long teacherId, @PathParam("subjectId") Long subjectId) throws IdNotFoundException {
        try {
            service.addSubject(teacherId, subjectId);
            return Response.ok().build();
        } catch (IdNotFoundException idNotFoundException) {
            throw idNotFoundException;
        } catch (Exception e) {
            throw new WebApplicationException();
        }
    }

    @DELETE
    @Path("remove_subject/{subjectId}")
    public Response removeSubject(@PathParam("subjectId") Long subjectId) throws IdNotFoundException {
        try {
            service.removeSubject(subjectId);
            return Response.ok().build();
        } catch (IdNotFoundException idNotFoundException) {
            throw idNotFoundException;
        } catch (Exception e) {
            throw new WebApplicationException();
        }
    }
}
