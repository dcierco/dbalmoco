package com.dbserver.dbalmoco.repository;

import java.util.List;
import java.util.Optional;

import com.dbserver.dbalmoco.models.Restaurante;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestauranteRepository extends CrudRepository<Restaurante, Integer> {

    @Query("SELECT RESTAURANTE FROM Restaurante RESTAURANTE WHERE VOTADO_DIA = TRUE")
    Optional<List<Restaurante>> findAllVotadoDia();
}
