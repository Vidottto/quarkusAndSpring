package com.study.quarkus.resource;

import java.util.List;

import javax.inject.Inject;
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

import com.study.quarkus.dto.CursoRequest;
import com.study.quarkus.dto.CursoResponse;
import com.study.quarkus.service.CursoService;


@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/curso")
public class CursoResource {
    

    @Inject
    CursoService service;
    
    @POST
    public CursoResponse cadastraCurso(CursoRequest curso){
        
        return service.cadastraCurso(curso);
        
    }

    @GET
    public Response listaDeCursos(){
        final List<CursoResponse> response = service.getCursos();

        return Response.status(Status.OK).entity(response).build();

    }

    @PUT
    @Path("/update/{curso_id}")
    public CursoResponse update(@PathParam("curso_id") int id, String descricao) {
        CursoResponse curso = service.update(id, descricao);
        return curso;
    }
}

