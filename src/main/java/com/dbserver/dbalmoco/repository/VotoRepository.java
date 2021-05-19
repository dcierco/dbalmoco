package com.dbserver.dbalmoco.repository;

import com.dbserver.dbalmoco.models.Voto;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoRepository extends CrudRepository<Voto, Integer> {

}
