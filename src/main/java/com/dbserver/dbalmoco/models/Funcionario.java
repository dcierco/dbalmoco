package com.dbserver.dbalmoco.models;

import java.util.Collection;
import java.util.Collections;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.dbserver.dbalmoco.config.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@JsonSerialize
@Entity(name = "Funcionario")
@Table(name = "FUNCIONARIO")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Funcionario implements UserDetails {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NOME")
    @NotBlank(message = "O Nome do funcionário não pode estar vazio!")
    private String nome;

    @JsonIgnore
    @OneToOne(mappedBy = "funcionario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Voto voto;

    @NotNull(message = "Email não pode ser Null!")
    @Column(name = "EMAIL", nullable = false, unique = true)
    @Email(message = "Email deve ser válido!")
    private String email;

    @NotNull(message = "Senha não pode ser null!")
    @Column(name = "SENHA", nullable = false)
    @Size(min = 5, max = 20, message = "A senha deve ter entre 5 e 20 caracteres!")
    private String password;

    @Column(name = "ROLE")
    private UserRole userRole;

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(userRole.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
