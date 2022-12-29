package com.study.quarkus.resource;

import java.util.List;

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
import com.study.quarkus.dto.Professor.ProfessorResponse;
import com.study.quarkus.service.AlunoService;


@Path("/aluno")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AlunoResource {


    @Inject
    AlunoService service;


    @POST
    public Response registraAluno(@Valid AlunoRequest aluno) {
        AlunoResponse response = service.registraAluno(aluno);

        return Response.status(Status.CREATED).entity(response).build();
    }

    @GET
    public Response getAlunos() {
        List<AlunoResponse> listAlunos = service.listarAlunos();

        return Response.ok(listAlunos).build();
    }
    
    @GET
    @Path("/{id}")
    public Response getAlunoById(@PathParam("id") int id) {
        AlunoResponse response = service.getAlunoById(id);

        return Response.ok(response).build();  
    }

    @GET
    @Path("/{id}/tutor")
    public Response getTutorByIdAluno(@PathParam("id")int id) {
        ProfessorResponse response = service.getTutorByAlunoId(id);

        return Response.ok(response).build();
    }



    @PUT
    @Path("/{id}/nome")
    public Response modifcaNomeAluno(@PathParam("id") int id, String nome) {
        AlunoResponse response = service.modifcaAluno(id, nome);
        
        return Response.ok(response).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletaAluno(@PathParam("id")int id) {
        service.deletaAluno(id);
        
        return Response.ok().build();
    }

    @PUT
    @Path("/{id}/tutor")
    public Response modifcaTutorAluno(@PathParam("id") int id, int idNovoTutor) {
        AlunoResponse response = service.modifcaTutorAluno(id, idNovoTutor);

        return Response.ok(response).build();
    }
}