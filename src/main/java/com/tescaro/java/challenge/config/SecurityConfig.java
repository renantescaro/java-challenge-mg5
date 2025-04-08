package com.tescaro.java.challenge.config;

import com.tescaro.java.challenge.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	private final UserRepository userRepository;

	public SecurityConfig(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return username -> userRepository
				.findByUsername(username)
				.orElseThrow(
						() -> new UsernameNotFoundException(
								"Usuário não encontrado: " + username));
	}

	// Define as regras de segurança
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// Desativa CSRF para facilitar durante o desenvolvimento
		http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(
						authz -> authz
								.requestMatchers("/", "/index", "/css/**", "/js/**", "/api/**")
								.permitAll()
								.anyRequest()
								.authenticated() // Todas as outras rotas também exigem login
				)
				.formLogin(
						// Redireciona para /panel após login
						form -> form.defaultSuccessUrl("/panel", true)
								.permitAll())
				.logout(
						// Redireciona para index após logout
						logout -> logout
								.logoutSuccessUrl("/")
								.permitAll());

		return http.build();
	}

	// Define o encoder para senhas (BCrypt)
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// Configura o AuthenticationManager
	@Bean
	public AuthenticationManager authenticationManager(
			HttpSecurity http,
			PasswordEncoder passwordEncoder,
			UserDetailsService userDetailsService)
			throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class)
				.userDetailsService(userDetailsService)
				.passwordEncoder(passwordEncoder)
				.and()
				.build();
	}
}
