package com.study.quarkus.repository;

import javax.enterprise.context.RequestScoped;

import com.study.quarkus.model.Professor;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@RequestScoped
public class ProfessorRepository implements PanacheRepositoryBase<Professor, Integer> {
    
}
