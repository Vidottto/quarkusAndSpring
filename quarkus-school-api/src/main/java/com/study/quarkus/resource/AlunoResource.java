package com.study.quarkus.resource;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.study.quarkus.dto.Aluno.AlunoRequest;
import com.study.quarkus.dto.Aluno.AlunoResponse;
import com.study.quarkus.service.AlunoService;


@Path("/aluno")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AlunoResource {


@Inject
AlunoService service;

    @GET
    public Response getAlunos() {
        return service.listarAlunos();
    }
    
    @GET
    @Path("{aluno_id}")
    public AlunoResponse getAlunoById(@PathParam("aluno_id") int id) {
        return service.getAlunoById(id);  
    }

    @POST
    public Response registraAluno(@Valid AlunoRequest aluno) {
        AlunoResponse response = service.registraAluno(aluno);
        return Response.status(Status.CREATED).entity(response).build();
    }

    @PUT
    @Path("/{aluno_id}/nome")
    public AlunoResponse modifcaNomeAluno(@PathParam("aluno_id") int id, String nome) {
        return service.modifcaAluno(id, nome);
    }

    @DELETE
    @Path("/{aluno_id}")
    public AlunoResponse deletaAluno(@PathParam("aluno_id")int id) {
        return service.deletaAluno(id);
    }

    @PUT
    @Path("/{aluno_id}/tutor")
    public Response modifcaTutorAluno(@PathParam("aluno_id") int id, int idNovoTutor) {
        AlunoResponse response = service.modifcaTutorAluno(id, idNovoTutor);
        return Response.ok(response).build();
    }
}