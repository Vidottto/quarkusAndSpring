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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.study.quarkus.dto.Disciplina.DisciplinaRequest;
import com.study.quarkus.dto.Disciplina.DisciplinaResponse;
import com.study.quarkus.mapper.DisciplinaMapper;
import com.study.quarkus.service.DisciplinaService;

@Path("/disciplina")
@Consumes(MediaType.APPLICATION_JSON)
public class DisciplinaResource {

@Inject
DisciplinaService service;

@Inject
DisciplinaMapper mapper;

    @GET
    public Response getDisciplinas(){
        List<DisciplinaResponse> listDisciplinas = service.getDisciplinas();
        
        return Response.ok(listDisciplinas).build();
    }

    @GET
    @Path("/{id}")
    public Response getDisciplinaById(@PathParam("id") int id) {
        DisciplinaResponse response = service.getDisciplinaById(id);

        return Response.ok(response).build();
    }


    @POST
    public Response registraDisciplina(@Valid DisciplinaRequest disciplina){
        DisciplinaResponse disciplinaResponse = service.registraDisciplina(disciplina);

        return Response.status(Status.CREATED).entity(disciplinaResponse).build();
    }
    
    @DELETE
    @Path("/{id}")
    public Response deletaDisciplinaById(@PathParam("id")int id){
        service.deletaDisciplinaById(id);

        return Response.ok().build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizaProfessorByDisciplinaId(@PathParam("id") int id, int idProfessorNovo) {
        DisciplinaResponse response = service.atualizaProfessorByDisciplinaId(id, idProfessorNovo);
        
        return Response.ok(response).build();
    } 
}
