package com.study.quarkus.resource;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.PathParam;

import com.study.quarkus.dto.ProfessorRequest;
import com.study.quarkus.dto.ProfessorResponse;
import com.study.quarkus.service.ProfessorService;

@Consumes(MediaType.APPLICATION_JSON)
@Path("/professor")
public class ProfessorResource {
    private final ProfessorService service;

    @Inject
    public ProfessorResource(ProfessorService service) {
        this.service = service;
    }

    @POST
    public ProfessorResponse registraProfessor(ProfessorRequest professor) {
        return service.save(professor);
    }

    @GET
    public Response listProfessors(){
        final List<ProfessorResponse> response = service.retrieveAll();

        return Response.status(Status.OK).entity(response).build();
        
    }

    @GET
    @Path("/{professor_id}")
    public Response getProfessorById(@PathParam("professor_id") int id) {
        ProfessorResponse professor = service.getProfessorById(id);
        return Response.ok(professor).build();
    }

    @DELETE
    @Path("/{professor_id}")
    public Response deleteProfessor(@PathParam("professor_id") int id) {
        service.delete(id);
        return Response.ok().build();
    }

    @PUT
    @Path("/update/{professor_id}")
    public ProfessorResponse update(@PathParam("professor_id") int id, String name) {
        ProfessorResponse professor = service.update(id, name);
        return professor;
    }
}
