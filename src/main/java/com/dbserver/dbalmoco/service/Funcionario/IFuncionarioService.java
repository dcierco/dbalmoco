package com.dbserver.dbalmoco.service.Funcionario;

import java.util.List;
import java.util.NoSuchElementException;

import com.dbserver.dbalmoco.models.Funcionario;


public interface IFuncionarioService {
    List<Funcionario> listarFuncionarios();
    Funcionario obterFuncionarioPorId(Integer id) throws NoSuchElementException;
  
    Funcionario salvarFuncionario(Funcionario Funcionario);
    Funcionario atualizarFuncionario(Funcionario Funcionario);
  
    void excluirFuncionario(Integer id);
}
