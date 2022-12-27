package com.study.quarkus.repository;

import javax.enterprise.context.RequestScoped;

import com.study.quarkus.model.Disciplina;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@RequestScoped
public class DisciplinaRepository implements PanacheRepositoryBase<Disciplina, Integer> {
    
}
