package org.generation.blogPessoal.seguranca;

import java.util.Optional;

import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service // classe se trata de um serviço
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository userRepository; //injetando o nosso repositório de usuário nessa camada, pois temos qye localizar o usuario no nosso banco e converte-lo em userdetails
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException  {//carregar user por nome utilizado em uso no blog pessoal
		Optional<Usuario> user = userRepository.findByUsuario(userName);
		user.orElseThrow(() -> new UsernameNotFoundException (userName + " not found."));
		
		return user.map(UserDetailsImpl:: new).get();
	}
		
} 
