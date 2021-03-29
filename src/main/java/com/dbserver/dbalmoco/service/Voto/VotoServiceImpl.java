package com.dbserver.dbalmoco.service.Voto;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.dbserver.dbalmoco.models.Voto;
import com.dbserver.dbalmoco.repository.VotoRepository;

import org.springframework.stereotype.Service;

@Service
public class VotoServiceImpl implements VotoService {
    private final VotoRepository votoRepository;

    public VotoServiceImpl(VotoRepository votoRepository){
        this.votoRepository = votoRepository;
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
}
