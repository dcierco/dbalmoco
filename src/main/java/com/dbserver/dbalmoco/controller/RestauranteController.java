package com.dbserver.dbalmoco.controller;

import java.util.List;

import javax.validation.Valid;

import com.dbserver.dbalmoco.models.Restaurante;
import com.dbserver.dbalmoco.service.Restaurante.RestauranteService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@Api(value = "Restaurante")
public class RestauranteController {
    private final RestauranteService restauranteService;

    @GetMapping("/api/restaurantes")
    @ApiOperation(value = "Obtem a lista de todos os Restaurantes")
    public List<Restaurante> listarRestaurantes(){
        return restauranteService.listarRestaurantes();
    }

    @GetMapping("/api/restaurante/{id}")
    @ApiOperation(value = "Busca o Restaurante por ID")
    public Restaurante obterRestaurantePorId(@PathVariable(value = "id") Integer id) {
        return restauranteService.obterRestaurantePorId(id);
    }

    @PostMapping("/api/restaurante")
    @ApiOperation(value = "Salva o Restaurante informado")
    public Restaurante salvarRestaurante(@Valid @RequestBody Restaurante restaurante) {
        return restauranteService.salvarRestaurante(restaurante);
    }

    @PutMapping("/api/restaurante")
    @ApiOperation(value = "Atualiza o Restaurante informado")
    public Restaurante atualizarRestaurante(@Valid @RequestBody Restaurante restaurante) {
        return restauranteService.atualizarRestaurante(restaurante);
    }

    @DeleteMapping("/api/restaurante/{id}")
    @ApiOperation(value = "Exclui o Restaurante informado")
    public void excluirRestaurante(@PathVariable(value = "id") Integer id) {
        restauranteService.excluirRestaurante(id);
    }
}
