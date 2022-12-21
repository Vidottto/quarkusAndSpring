package com.study.quarkus.resource;

import javax.inject.Inject;
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

import com.study.quarkus.dto.AlunoRequest;
import com.study.quarkus.dto.AlunoResponse;
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
    public AlunoResponse registraAluno(AlunoRequest aluno) {
        return service.registraAluno(aluno);
    }

    @PUT
    @Path("/{aluno_id}")
    @Consumes(MediaType.TEXT_PLAIN)
    public AlunoResponse modifcaAluno(@PathParam("aluno_id") int id, String nome) {
        return service.modifcaAluno(id, nome);
    }

    @DELETE
    @Path("/update/{aluno_id}")
    public AlunoResponse deletaAluno(@PathParam("aluno_id")int id) {
        return service.deletaAluno(id);
    }

}