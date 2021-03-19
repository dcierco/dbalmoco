package com.dbserver.dbalmoco.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.*;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "Restaurante")
@Table(name = "RESTAURANTE")
@Getter
@Setter
public class Restaurante implements Serializable{

    /**
     Default serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "NOME")
    @NotBlank(message = "O nome do restaurante é obrigatório!")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "RESTAURANTE", orphanRemoval = true)
    private List<Voto> votos;

    @Column(name = "DESCRICAO")
    @Size(max = 50, message = "A descrição pode ter no maximo 50 caracteres!")
    private String descricao;

    @Column(name = "ENDERECO")
    @NotBlank(message = "O endereço do restaurante é obrigatório!")
    @Size(min = 5, max = 50, message = "O endereço do restaurante deve ter entre 5 e 50 caracteres!")
    private String endereco;


    

    
}
