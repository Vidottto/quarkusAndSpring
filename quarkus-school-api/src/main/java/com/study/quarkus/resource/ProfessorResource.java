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

import com.study.quarkus.dto.Aluno.AlunoResponse;
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

        return Response.status(Status.CREATED).entity(response).build();
    }

    @GET
    public Response listProfessores(){
        List<ProfessorResponse> response = service.retrieveAll();

        return Response.ok(response).build();
        
    }

    @GET
    @Path("/{id}")
    public Response getProfessorById(@PathParam("id") int id) {
        ProfessorResponse professor = service.getProfessorById(id);

        return Response.ok(professor).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteProfessor(@PathParam("id") int id) {
        service.delete(id);
        
        return Response.ok().build();
    }

    @PUT
    @Path("/{id}/update/nome")
    public Response update(@PathParam("id") int id, @NotBlank String name) {
        ProfessorResponse professor = service.update(id, name);
        
        return Response.ok(professor).build();
    }

    @GET
    @Path("/{id}/tutorados")
    public Response getTutoradosByProfessorId(@PathParam("id")int id){
        List<AlunoResponse> listTutorados = service.getTutoradosByProfessorId(id);

        return Response.ok(listTutorados).build();

    }

    @PUT
    @Path("/{id}/update/tutorados")
    public Response updateTutoradoByIdProfessor(@PathParam("id")int id, int idTutorado){
        ProfessorResponse response = service.updateTutoradoByIdProfessor(id, idTutorado);
          
        return Response.ok(response).build();
        
    }

    @DELETE
    @Path("/{id}/delete-tutorado/")
    public Response deleteTutoradoByIdProfessor(@PathParam("id")int id, int tutoradoId){
        ProfessorResponse response = service.deleteTutoradoByIdProfessor(id, tutoradoId);

        return Response.ok(response).build();
    }
}
