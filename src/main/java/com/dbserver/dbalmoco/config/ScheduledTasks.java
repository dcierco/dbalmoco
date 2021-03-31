package com.dbserver.dbalmoco.config;

import com.dbserver.dbalmoco.service.Restaurante.RestauranteService;
import com.dbserver.dbalmoco.service.Voto.VotoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    @Autowired
    VotoService votoService;

    @Autowired
    RestauranteService restauranteService;

    @Scheduled(cron = "0 30 11 * * *")
    public void atualizaMaisVotado(){
        this.restauranteService.atualizaMaisVotadoDia();
        this.votoService.excluirTodosVotos();
    }
}
