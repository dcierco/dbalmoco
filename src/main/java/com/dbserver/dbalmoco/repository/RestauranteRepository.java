package com.dbserver.dbalmoco.repository;

import com.dbserver.dbalmoco.models.Restaurante;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestauranteRepository extends CrudRepository<Restaurante, Integer> {
    
}
