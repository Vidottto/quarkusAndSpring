package com.study.quarkus.dto;

import javax.persistence.Entity;
import javax.persistence.Id;


/**
 * Example JPA entity.
 * <p>
 * To use it, get access to a JPA EntityManager via injection.
 * <p>
 * {@code
 *
 * @Inject EntityManager em;
 * <p>
 * public void doSomething() {
 * MyEntity entity1 = new MyEntity();
 * entity1.setNome("nome-1");
 * em.persist(entity1);
 * <p>
 * List<MyEntity> entities = em.createQuery("from MyEntity", MyEntity.class).getResultList();
 * }
 * }
 */
@Entity
public class Aluno {
    @Id
    // @GeneratedValue(strategy=AUTO, generator="CUST_SEQ")
    // @NotNull
    // private Long id;

    private int matricula;
    private String nome;
    private String sexo;

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    
    // public Long getId() {
    //     return id;
    // }
// 
    // @Id
    // @GeneratedValue(strategy=AUTO, generator="CUST_SEQ")
    // @NotNull
    // public void setId(Long id) {
    //     this.id = id;
    // }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    @Override
    public String toString() {
        return "Aluno [matricula=" + matricula + ", nome=" + nome + "]";
    }

    // @Override
    // public int compareTo(Aluno outroAluno) {
    //     return this.getId() > outroAluno.getId() ? 1 : -1;
    // }
}
