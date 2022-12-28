package com.study.quarkus.resource;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
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

import com.study.quarkus.dto.Professor.ProfessorRequest;
import com.study.quarkus.dto.Professor.ProfessorResponse;
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
    public Response registraProfessor(@Valid ProfessorRequest professor) {
        ProfessorResponse response = service.save(professor);
        return Response.ok(response).build();
    }

    @GET
    public Response listProfessors(){
        List<ProfessorResponse> response = service.retrieveAll();

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
    public Response update(@PathParam("professor_id") int id, @NotBlank String name) {
        ProfessorResponse professor = service.update(id, name);
        
        return Response.ok(professor).build();
    }
}
