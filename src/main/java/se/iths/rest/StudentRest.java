package se.iths.rest;

import se.iths.entity.Student;
import se.iths.exception.ConnectedException;
import se.iths.exception.IdNotFoundException;
import se.iths.exception.NoConnectionWithEntityException;
import se.iths.service.StudentService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static se.iths.rest.ExceptionWrapperImpl.execute;

@Path("students")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentRest extends CRUDRest<Student, StudentService> {

    @Inject
    public StudentRest(StudentService service) {
        super.service = service;
    }

    @GET
    @Path("lastname")
    public Response getStudentsByLastName(@QueryParam("lastname") String lastName) throws ConnectedException, NoConnectionWithEntityException, IdNotFoundException {
        return execute(() -> Response.ok(service.getStudentsByLastName(lastName)).build());
    }

    @POST
    @Path("add_subject/{studentId}/{subjectId}")
    public Response addSubject(@PathParam("studentId") Long studentId, @PathParam("subjectId") Long subjectId) throws IdNotFoundException, ConnectedException, NoConnectionWithEntityException {
        return execute(() -> {
            service.addSubject(studentId, subjectId);
            return Response.ok().build();
        });
    }

    @DELETE
    @Path("remove_subject/{studentId}/{subjectId}")
    public Response removeSubject(@PathParam("studentId") Long studentId, @PathParam("subjectId") Long subjectId) throws IdNotFoundException, NoConnectionWithEntityException, ConnectedException {
        return execute(() -> {
                    service.removeSubject(studentId, subjectId);
                    return Response.ok().build();
                }
        );
    }
}
