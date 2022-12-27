package com.study.quarkus.repository;

import javax.enterprise.context.RequestScoped;

import com.study.quarkus.model.Curso;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@RequestScoped
public class CursoRepository implements PanacheRepositoryBase<Curso, Integer> {
    
}
