package com.dbserver.dbalmoco.controller;

import java.util.List;

import javax.validation.Valid;

import com.dbserver.dbalmoco.models.Voto;
import com.dbserver.dbalmoco.service.Voto.VotoService;

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
@Api(value = "Voto")
public class VotoController {
    private final VotoService votoService;

    @GetMapping("/votos")
    @ApiOperation(value = "Obtem a lista de todos os Votos")
    public List<Voto> listarVotos(){
        return votoService.listarVotos();
    }

    @GetMapping("/voto/{id}")
    @ApiOperation(value = "Busca o Voto por ID")
    public Voto obterVotoPorId(@PathVariable(value = "id") Integer id) {
        return votoService.obterVotoPorId(id);
    }

    @PostMapping("/voto")
    @ApiOperation(value = "Salva o Voto informado")
    public Voto salvarVoto(@Valid @RequestBody Voto voto) {
        return votoService.salvarVoto(voto);
    }

    @PutMapping("/voto")
    @ApiOperation(value = "Atualiza o Voto informado")
    public Voto atualizarVoto(@Valid @RequestBody Voto voto) {
        return votoService.atualizarVoto(voto);
    }

    @DeleteMapping("/voto/{id}")
    @ApiOperation(value = "Exclui o Voto informado")
    public void excluirVoto(@PathVariable(value = "id") Integer id) {
        votoService.excluirVoto(id);
    }

}
