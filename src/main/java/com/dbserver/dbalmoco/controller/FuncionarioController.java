package com.dbserver.dbalmoco.controller;

import java.util.List;

import javax.validation.Valid;

import com.dbserver.dbalmoco.models.Funcionario;
import com.dbserver.dbalmoco.service.Funcionario.FuncionarioService;

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
@Api(value = "Funcionário")
public class FuncionarioController {
    private final FuncionarioService funcionarioService;

    @GetMapping("/api/funcionarios")
    @ApiOperation(value = "Obtem a lista de todos os Funcionarios")
    public List<Funcionario> listarFuncionarios(){
        return funcionarioService.listarFuncionarios();
    }

    @GetMapping("/api/funcionario/{id}")
    @ApiOperation(value = "Busca o Funcionario por ID")
    public Funcionario obterFuncionarioPorId(@PathVariable(value = "id") Integer id) {
        return funcionarioService.obterFuncionarioPorId(id);
    }

    @PostMapping("/api/funcionario")
    @ApiOperation(value = "Salva o Funcionario informado")
    public Funcionario salvarFuncionario(@Valid @RequestBody Funcionario funcionario) {
        return funcionarioService.salvarFuncionario(funcionario);
    }

    @PutMapping("/api/funcionario")
    @ApiOperation(value = "Atualiza o Funcionario informado")
    public Funcionario atualizarFuncionario(@Valid @RequestBody Funcionario funcionario) {
        return funcionarioService.atualizarFuncionario(funcionario);
    }

    @DeleteMapping("/api/funcionario/{id}")
    @ApiOperation(value = "Exclui o Funcionario informado")
    public void excluirFuncionario(@PathVariable(value = "id") Integer id) {
        funcionarioService.excluirFuncionario(id);
    }
}