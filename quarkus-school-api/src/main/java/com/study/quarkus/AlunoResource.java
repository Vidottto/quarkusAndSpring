package com.study.quarkus;

import java.util.HashMap;
import java.util.Map;

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

import com.study.quarkus.dto.Aluno;

@Path("/aluno")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AlunoResource {

Map<Long, Aluno> alunos = new HashMap<>();
Long id = 0L;

    @GET
    public Response getAlunos() {
        System.out.println("Lista de alunos\n\n");
        
        return Response.ok(alunos).build();
    }
    
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("{id}")
    public Response getAlunoById(@PathParam("id") String strId) {
        
        return Response.ok(alunos.get(Long.parseLong(strId))).build();
    }

    @POST
    public Response registraAluno(Aluno aluno) {
        String nome = aluno.getNome();
        int matricula = aluno.getMatricula();
        System.out.printf("Aluno registrado ::: nome: {%s}, matrícula: {%d}\n\n", nome, matricula);
        alunos.put(id, aluno); id++;

        return Response.status(Status.CREATED).build();
    }

    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    public Response modifcaAluno(String idNome) {
        String idAluno = idNome.split(", ")[0];
        String nome = idNome.split(", ")[1];
        Aluno aluno = alunos.get(Long.valueOf(idAluno));
        String oldNome = aluno.getNome();
        aluno.setNome(nome);
        int matricula = aluno.getMatricula();
        System.out.printf("Aluno modificado ::: matrícula: {%d}, nome anterior: {%s}, nome atualizado:{%s}\n\n", matricula, oldNome, nome);

        return Response.status(Status.OK).build();
    }

    @DELETE
    @Consumes(MediaType.TEXT_PLAIN)
    public Response deletaAluno(Long id) {
        Aluno aluno = alunos.get(id);
        int matricula = aluno.getMatricula();
        String nome = aluno.getNome();
        alunos.remove(id, aluno);
        System.out.printf("Aluno excluído ::: matrícula: {%d}, nome:{%s}\n\n", matricula, nome);
        
        return Response.status(Status.OK).build();
    }

}