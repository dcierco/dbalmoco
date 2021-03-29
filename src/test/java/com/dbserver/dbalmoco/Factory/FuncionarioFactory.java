package com.dbserver.dbalmoco.Factory;

import com.dbserver.dbalmoco.config.UserRole;
import com.dbserver.dbalmoco.models.Funcionario;

public class FuncionarioFactory {

    public FuncionarioFactory(){}

    public Funcionario factory(Integer id, String nome, String email, String senha){
        Funcionario Funcionario = com.dbserver.dbalmoco.models.Funcionario.builder().
                id(id).
                nome(nome).
                email(email).
                password(senha).
                userRole(UserRole.ROLE_USER).
                build();
        return Funcionario;
    }
}