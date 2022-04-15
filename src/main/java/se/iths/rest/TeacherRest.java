package se.iths.rest;

import se.iths.entity.Teacher;
import se.iths.exception.ConnectedException;
import se.iths.exception.IdNotFoundException;
import se.iths.exception.NoConnectionWithEntityException;
import se.iths.service.TeacherService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static se.iths.rest.ExceptionWrapperImpl.execute;

@Path("teachers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TeacherRest extends CRUDRest<Teacher, TeacherService> {

    @Inject
    public TeacherRest(TeacherService service) {
        super.service = service;
    }

    @POST
    @Path("add_subject/{teacherId}/{subjectId}")
    public Response addSubject(@PathParam("teacherId") Long teacherId, @PathParam("subjectId") Long subjectId) throws IdNotFoundException, ConnectedException, NoConnectionWithEntityException {
        return execute(() -> {
            service.addSubject(teacherId, subjectId);
            return Response.ok().build();
        });
    }

    @DELETE
    @Path("remove_subject/{subjectId}")
    public Response removeSubject(@PathParam("subjectId") Long subjectId) throws IdNotFoundException, NoConnectionWithEntityException, ConnectedException {
        return execute(() -> {
            service.removeSubject(subjectId);
            return Response.ok().build();
        });
    }
}
