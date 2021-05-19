package com.dbserver.dbalmoco.service.Restaurante;

import java.util.List;
import java.util.NoSuchElementException;

import com.dbserver.dbalmoco.models.Restaurante;

public interface RestauranteService {
    List<Restaurante> listarRestaurantes();

    List<Restaurante> listaRestaurantesVotaveis();

    Restaurante obterRestaurantePorId(Integer id) throws NoSuchElementException;

    Restaurante salvarRestaurante(Restaurante Restaurante);

    Restaurante obterMaisVotadoDia();

    Restaurante atualizarRestaurante(Restaurante Restaurante);

    void excluirRestaurante(Integer id);

    void atualizaMaisVotadoDia();
}
