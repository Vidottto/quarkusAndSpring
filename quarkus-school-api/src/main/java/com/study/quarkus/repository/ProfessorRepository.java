package com.study.quarkus.repository;

import javax.enterprise.context.RequestScoped;

import com.study.quarkus.model.ProfessorModel;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@RequestScoped
public class ProfessorRepository implements PanacheRepositoryBase<ProfessorModel, Integer> {
    
}
