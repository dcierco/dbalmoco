package com.dbserver.dbalmoco.service.Voto;


import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.activity.InvalidActivityException;

import com.dbserver.dbalmoco.models.Funcionario;
import com.dbserver.dbalmoco.models.Restaurante;
import com.dbserver.dbalmoco.models.Voto;
import com.dbserver.dbalmoco.repository.VotoRepository;
import com.dbserver.dbalmoco.service.Funcionario.FuncionarioService;
import com.dbserver.dbalmoco.service.Restaurante.RestauranteService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import net.bytebuddy.implementation.bytecode.Throw;

@Service
public class VotoServiceImpl implements VotoService {
    private final VotoRepository votoRepository;
    private final FuncionarioService funcionarioService;
    private final RestauranteService restauranteService;

    public VotoServiceImpl(VotoRepository votoRepository, FuncionarioService funcionarioService, RestauranteService restauranteService){
        this.votoRepository = votoRepository;
        this.funcionarioService = funcionarioService;
        this.restauranteService = restauranteService;
    }

    @Override
    public List<Voto> listarVotos() {
        return StreamSupport.stream(this.votoRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Voto obterVotoPorId(Integer id) throws NoSuchElementException{
        return this.votoRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Não existe no banco de dados voto com o id:" + id));
    }

    @Override
    public Voto salvarVoto(Voto Voto) {
        return votoRepository.save(Voto);
    }

    @Override
    public Voto atualizarVoto(Voto Voto) {
        this.obterVotoPorId(Voto.getId());
        return votoRepository.save(Voto);
    }

    @Override
    public void excluirVoto(Integer id) {
        this.votoRepository.deleteById(id);
    }

    @Override
    public void excluirTodosVotos() {
        this.votoRepository.deleteAll();
    }

    @Override
    public Voto votar(int idUser, int idRestaurante) throws Exception {
        Funcionario funcionario = this.funcionarioService.obterFuncionarioPorId(idUser);
        Restaurante restaurante = this.restauranteService.obterRestaurantePorId(idRestaurante);
        if(funcionario.getVoto() != null){
            throw(new Exception("O funcionario com o id " + idUser + " já teve seu voto cadastrado hoje!"));
        }
        return salvarVoto(new Voto(0, restaurante, funcionario, new Date()));
    }
}
