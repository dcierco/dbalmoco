package com.dbserver.dbalmoco.repository;

import com.dbserver.dbalmoco.models.Funcionario;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends CrudRepository<Funcionario, Integer> {
    
}
