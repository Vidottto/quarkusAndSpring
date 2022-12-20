package com.study.quarkus.service;
// package com.study.quarkus;

// import java.util.HashMap;
// import java.util.Map;

// import javax.enterprise.context.ApplicationScoped;
// import javax.ws.rs.core.Response;
// import javax.ws.rs.core.Response.Status;

// import com.study.quarkus.dto.Aluno;

// import lombok.extern.slf4j.Slf4j;

// @ApplicationScoped
// @Slf4j
// public class AlunoService {
//     Long id = 0L;
//     Map<Long, Aluno> alunos = new HashMap<>();

//     public Response listarAlunos(){
//         log.info("Lista de alunos\n\n");

//         return Response.ok(alunos).build();
//     }

//     public Response getAlunoById(String strId){
//         log.info("Aluno de id {} listado\n\n", strId);

//         return Response.ok(alunos
//                 .get(Long.parseLong(strId)))
//                 .build();
//     }

//     public Response registraAluno(Aluno aluno) {
//         String nome = aluno.getNome();
//         int matricula = aluno.getMatricula();
//         log.info("Aluno registrado ::: nome: {}, matrícula: {}\n\n", nome, matricula);
//         alunos.put(id, aluno); id++;

//         return Response.status(Status.CREATED).build();
//     }

//     public Response modifcaAluno(String idNome) {
//         String idAluno = idNome.split(", ")[0];
//         String nome = idNome.split(", ")[1];
//         Aluno aluno = alunos.get(Long.valueOf(idAluno));
//         String oldNome = aluno.getNome();
//         aluno.setNome(nome);
//         int matricula = aluno.getMatricula();
//         log.info("Aluno modificado ::: matrícula: {}, nome anterior: {}, nome atualizado:{}\n\n", matricula, oldNome, nome);

//         return Response.status(Status.OK).build();
//     }

//     public Response deletaAluno(Long id) {
//         Aluno aluno = alunos.get(id);
//         int matricula = aluno.getMatricula();
//         String nome = aluno.getNome();
//         alunos.remove(id, aluno);
//         log.info("Aluno excluído ::: matrícula: {}, nome:{}\n\n", matricula, nome);  
//         return Response.status(Status.OK).build();
//     }

    
// }
