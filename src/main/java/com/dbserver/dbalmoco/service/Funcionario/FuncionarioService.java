package com.dbserver.dbalmoco.service.Funcionario;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.dbserver.dbalmoco.models.Funcionario;
import com.dbserver.dbalmoco.repository.FuncionarioRepository;

import org.springframework.stereotype.Service;


@Service
public class FuncionarioService implements IFuncionarioService{

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository){
        this.funcionarioRepository = funcionarioRepository;
    }

    @Override
    public List<Funcionario> listarFuncionarios() {
        return StreamSupport.stream(this.funcionarioRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Funcionario obterFuncionarioPorId(Integer id) throws NoSuchElementException{
        return this.funcionarioRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Não existe no banco de dados funcionário com o id:" + id));
    }

    @Override
    public Funcionario salvarFuncionario(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    @Override
    public Funcionario atualizarFuncionario(Funcionario funcionario) {
        this.obterFuncionarioPorId(funcionario.getId());
        return funcionarioRepository.save(funcionario);
    }

    @Override
    public void excluirFuncionario(Integer id) {
        this.funcionarioRepository.deleteById(id);
    }
}
