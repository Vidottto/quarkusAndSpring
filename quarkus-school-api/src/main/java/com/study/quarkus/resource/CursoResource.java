package com.study.quarkus.resource;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.ws.rs.Consumes;
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
    public CursoResponse cadastraCurso(@Valid CursoRequest curso){
        
        return service.cadastraCurso(curso);
        
    }

    @GET
    public Response listaDeCursos(){
        final List<CursoResponse> response = service.getCursos();

        return Response.status(Status.OK).entity(response).build();

    }

    @GET
    @Path("/{curso_id}")
    public Response getCursoById(@PathParam("curso_id") int id){
        CursoResponse response = service.getCursoById(id);

        return Response.status(Status.OK).entity(response).build();

    }

    @PUT
    @Path("/update/{curso_id}")
    public CursoResponse updateDescricao(@PathParam("curso_id") int id, @NotBlank String descricao) {
        CursoResponse curso = service.updateDescricao(id, descricao);
        return curso;
    }

    @GET
    @Path("{curso_id}/disciplinas")
    public Response getDisciplinasByIdCurso(@PathParam("curso_id") int id){
        List<String> listDisciplinas = service.getDisciplinasByIdCurso(id);
        return Response.ok(listDisciplinas).build();
    }
}

