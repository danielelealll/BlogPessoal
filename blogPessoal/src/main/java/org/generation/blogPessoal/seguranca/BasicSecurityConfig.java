package org.generation.blogPessoal.seguranca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity // anotação que habilita a configuração web security
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter { // habilitou extensão que conigura segurança web e adapta
	
	@Autowired // anotação responsável pela injeção de dependência
	private UserDetailsService userDetailsService;
	
	@Override//método que vai sobreescrever o método que tem dentro de userdetailsservice
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/usuarios/logar").permitAll() // configuração realizada para liberar end points, ou seja, alguns caminhos dentro do controller para que o client tenha acesso a eles sem precisar passar uma chave token
		.antMatchers("/usuarios/cadastrar").permitAll() //para o cliente fazer requisições de cadatro dentro da nossa api
		.anyRequest().authenticated() // todas as outras requisições deverão ser autenticadas por chave token
		.and().httpBasic() // http basic para gerar chave token
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // tipo de sessão que será utilizada. SESSÃO CRIADA NA API NÃO GUARDARÁ SESSÃO
		.and().cors()
		.and().csrf().disable();
	}
	

}
