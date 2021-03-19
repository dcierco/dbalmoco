package com.dbserver.dbalmoco.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "Funcionario")
@Table(name = "FUNCIONARIO")
@Getter
@Setter
public class Funcionario implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "NOME")
    @NotBlank(message = "O Nome do funcionário não pode estar vazio!")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "FUNCIONARIO", orphanRemoval = true)
    private List<Voto> votos;

    @Column(name = "EMAIL")
    @Email(message = "Email deve ser válido!")
    private String email;

    @Column(name = "SENHA")
    @Size(min = 5, max = 20, message = "A senha deve ter entre 5 e 20 caracteres!")
    private String password;
}
