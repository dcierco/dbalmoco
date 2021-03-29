package com.dbserver.dbalmoco.Factory;

import com.dbserver.dbalmoco.models.Restaurante;



public class RestauranteFactory {

    public RestauranteFactory(){}

    public Restaurante factory(Integer id, String nome, String descricao, String endereco, String votado_data){
        Restaurante Restaurante = com.dbserver.dbalmoco.models.Restaurante.builder().
                id(id).
                nome(nome).
                descricao(descricao).
                endereco(endereco).
                build();
        return Restaurante;
    }
}