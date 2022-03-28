package se.iths.rest;

import se.iths.entity.Student;
import se.iths.service.StudentService;

import javax.inject.Inject;
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
    public Response createStudent(Student student) {
        service.createStudent(student);
        return Response.ok(student).build();
    }

    @PUT
    public Response updateStudent(Student student) {
        service.updateStudent(student);
        return Response.ok().build();
    }

    @PATCH
    public Response patchStudent(Student student) {
        service.patchStudent(student);
        return Response.ok().build();
    }

    @GET()
    public Response getAllStudents() {
        List<Student> students = service.getAllStudents();
        return Response.ok(students).build();
    }

    @GET
    @Path("{id}")
    public Response getStudentById(@PathParam("id") Long id) {
        return Response.ok(service.getStudentById(id)).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteStudent(@PathParam("id") Long id) {
        service.deleteStudent(id);
        return Response.ok().build();
    }
}
