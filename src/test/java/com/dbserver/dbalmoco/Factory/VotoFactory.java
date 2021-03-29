package com.dbserver.dbalmoco.Factory;

import java.sql.Date;

import com.dbserver.dbalmoco.models.Funcionario;
import com.dbserver.dbalmoco.models.Restaurante;
import com.dbserver.dbalmoco.models.Voto;

public class VotoFactory {

    public VotoFactory(){}

    public Voto factory(Integer id, Restaurante restaurante, Funcionario funcionario, Date vdDate){
        Voto Voto = com.dbserver.dbalmoco.models.Voto.builder().
                id(id).
                restaurante(restaurante).
                funcionario(funcionario).
                data(vdDate).
                build();
        return Voto;
    }
}