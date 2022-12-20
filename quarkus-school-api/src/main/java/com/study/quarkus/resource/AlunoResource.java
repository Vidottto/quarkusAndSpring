package com.study.quarkus.resource;
// package com.study.quarkus;

// import javax.inject.Inject;
// import javax.ws.rs.Consumes;
// import javax.ws.rs.DELETE;
// import javax.ws.rs.GET;
// import javax.ws.rs.POST;
// import javax.ws.rs.PUT;
// import javax.ws.rs.Path;
// import javax.ws.rs.PathParam;
// import javax.ws.rs.Produces;
// import javax.ws.rs.core.MediaType;
// import javax.ws.rs.core.Response;

// import com.study.quarkus.dto.Aluno;


// @Path("/aluno")
// @Produces(MediaType.APPLICATION_JSON)
// @Consumes(MediaType.APPLICATION_JSON)
// public class AlunoResource {


// @Inject
// AlunoService service;
//     @GET
//     public Response getAlunos() {
//         return service.listarAlunos();
//     }
    
//     @GET
//     @Consumes(MediaType.TEXT_PLAIN)
//     @Path("{id}")
//     public Response getAlunoById(@PathParam("id") String strId) {
//         return service.getAlunoById(strId);  
//     }

//     @POST
//     public Response registraAluno(Aluno aluno) {
//         return service.registraAluno(aluno);
//     }

//     @PUT
//     @Consumes(MediaType.TEXT_PLAIN)
//     public Response modifcaAluno(String idNome) {
//         return service.modifcaAluno(idNome);
//     }

//     @DELETE
//     @Consumes(MediaType.TEXT_PLAIN)
//     public Response deletaAluno(Long id) {
//         return service.deletaAluno(id);
//     }

// }