package com.dbserver.dbalmoco.service.Restaurante;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.dbserver.dbalmoco.models.Restaurante;
import com.dbserver.dbalmoco.repository.RestauranteRepository;

import org.springframework.stereotype.Service;

@Service
public class RestauranteServiceImpl implements RestauranteService{
    private final RestauranteRepository restauranteRepository;

    public RestauranteServiceImpl(RestauranteRepository restauranteRepository){
        this.restauranteRepository = restauranteRepository;
    }

    @Override
    public List<Restaurante> listarRestaurantes() {
        return StreamSupport.stream(this.restauranteRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Restaurante obterRestaurantePorId(Integer id) throws NoSuchElementException{
        return this.restauranteRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Não existe no banco de dados funcionário com o id:" + id));
    }

    @Override
    public Restaurante salvarRestaurante(Restaurante restaurante) {
        return restauranteRepository.save(restaurante);
    }

    @Override
    public Restaurante atualizarRestaurante(Restaurante restaurante) {
        this.obterRestaurantePorId(restaurante.getId());
        return restauranteRepository.save(restaurante);
    }

    @Override
    public void excluirRestaurante(Integer id) {
        this.restauranteRepository.deleteById(id);
    }
}
