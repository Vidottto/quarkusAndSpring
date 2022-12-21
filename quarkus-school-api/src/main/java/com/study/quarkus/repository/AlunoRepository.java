package com.study.quarkus.repository;

import javax.enterprise.context.RequestScoped;

import com.study.quarkus.model.AlunoModel;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@RequestScoped
public class AlunoRepository implements PanacheRepositoryBase<AlunoModel, Integer> {
    
}
