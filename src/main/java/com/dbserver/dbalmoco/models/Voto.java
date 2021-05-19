package com.dbserver.dbalmoco.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@JsonSerialize
@Entity(name = "Voto")
@Table(name = "VOTO")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Voto implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 3L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RESTAURANTE_ID", nullable = false)
    @NotNull(message = "Voto deve obrigatóriamente ter um restaurente.")
    @JsonBackReference
    private Restaurante restaurante;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FUNCIONARIO_ID", nullable = false)
    @NotNull(message = "Voto deve obrigatóriamente ter um votante.")
    private Funcionario funcionario;

    @Column(name = "DATA")
    @NotNull
    private Date data;

}
