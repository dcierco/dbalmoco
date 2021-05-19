package com.dbserver.dbalmoco.service.Voto;

import java.util.List;
import java.util.NoSuchElementException;

import javax.activity.InvalidActivityException;

import com.dbserver.dbalmoco.models.Voto;

public interface VotoService {
    List<Voto> listarVotos();
    Voto obterVotoPorId(Integer id) throws NoSuchElementException;
  
    Voto salvarVoto(Voto voto);
    Voto atualizarVoto(Voto voto);
    void excluirTodosVotos();
  
    void excluirVoto(Integer id);
    Voto votar(int idUser, int idRestaurante) throws Exception;
}
