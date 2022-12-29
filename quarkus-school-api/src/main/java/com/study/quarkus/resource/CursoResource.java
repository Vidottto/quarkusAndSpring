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
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.MediaType;

import com.study.quarkus.dto.Curso.CursoRequest;
import com.study.quarkus.dto.Curso.CursoResponse;
import com.study.quarkus.service.CursoService;


@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/curso")
public class CursoResource {
    

    @Inject
    CursoService service;
    
    @POST
    public Response cadastraCurso(@Valid CursoRequest curso){
        CursoResponse response = service.cadastraCurso(curso);

        return Response.status(Status.CREATED).entity(response).build();
    }

    @GET
    public Response listaDeCursos(){
        List<CursoResponse> response = service.getCursos();

        return Response.ok(response).build();
    }

    @GET
    @Path("/{curso_id}")
    public Response getCursoById(@PathParam("curso_id") int id){
        CursoResponse response = service.getCursoById(id);

        return Response.ok(response).build();
    }

    @PUT
    @Path("/update/{id}/descricao")
    public Response updateDescricao(@PathParam("id") int id, @NotBlank String descricao) {
        CursoResponse response = service.updateDescricao(id, descricao);

        return Response.ok(response).build();
    }

    @GET
    @Path("/{id}/disciplinas")
    public Response getDisciplinasByIdCurso(@PathParam("id") int id){
        List<String> listDisciplinas = service.getDisciplinasByIdCurso(id);

        return Response.ok(listDisciplinas).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCurso(@PathParam("id")int id) {
        service.deleteCurso(id);
        
        return Response.ok().build();
    }
}

