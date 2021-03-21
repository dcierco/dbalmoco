package com.dbserver.dbalmoco.service;

import java.text.MessageFormat;
import java.util.Optional;

import com.dbserver.dbalmoco.models.Funcionario;
import com.dbserver.dbalmoco.repository.FuncionarioRepository;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService{
	private final FuncionarioRepository funcionarioRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		final Optional<Funcionario> optionalUser = funcionarioRepository.findByEmail(email);

		return optionalUser.orElseThrow(() -> new UsernameNotFoundException(MessageFormat.format("Funcionario com email {0} não está cadastrado.", email)));
	}

    @Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}


}
