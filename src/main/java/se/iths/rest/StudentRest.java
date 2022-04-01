package se.iths.rest;

import se.iths.entity.Student;
import se.iths.exception.IdNotFoundException;
import se.iths.service.StudentService;

import javax.inject.Inject;
import javax.transaction.TransactionalException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("students")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentRest {

    @Inject
    StudentService service;

    @POST()
    public Response createStudent(Student student) throws TransactionalException {
        try {
            service.createStudent(student);
            return Response.ok(student).build();
        } catch (TransactionalException transactionalException) {
            throw transactionalException;
        } catch (Exception e) {
            throw new WebApplicationException();
        }
    }

    @PUT
    public Response updateStudent(Student student) {
        try {
            service.updateStudent(student);
            return Response.ok().build();
        } catch (TransactionalException transactionalException) {
            throw transactionalException;
        } catch (Exception e) {
            throw new WebApplicationException();
        }
    }

    @PATCH
    public Response patchStudent(Student student) throws IdNotFoundException {
        try {
            service.patchStudent(student);
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
    public Response getAllStudents() {
        try {
            List<Student> students = service.getAllStudents();
            return Response.ok(students).build();
        } catch (Exception e) {
            throw new WebApplicationException();
        }
    }

    @GET
    @Path("{id}")
    public Response getStudentById(@PathParam("id") Long id) throws IdNotFoundException {
        try {
            return Response.ok(service.getStudentById(id)).build();
        } catch (IdNotFoundException idNotFoundException) {
            throw idNotFoundException;
        } catch (Exception e) {
            throw new WebApplicationException();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteStudent(@PathParam("id") Long id) throws IdNotFoundException {
        try {
            service.deleteStudent(id);
            return Response.ok().build();
        } catch (IdNotFoundException idNotFoundException) {
            throw idNotFoundException;
        } catch (Exception e) {
            throw new WebApplicationException();
        }
    }

    @GET
    @Path("lastname")
    public Response getStudentsByLastName(@QueryParam("lastname") String lastName) {
        try {
            return Response.ok(service.getStudentsByLastName(lastName)).build();
        } catch (Exception e) {
            throw new WebApplicationException();
        }
    }
}
