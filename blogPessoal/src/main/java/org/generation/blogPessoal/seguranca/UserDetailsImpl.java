package org.generation.blogPessoal.seguranca;

import java.util.Collection;

import org.generation.blogPessoal.model.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails; // anotações e bibliotecas

public class UserDetailsImpl implements UserDetails {
	
	
	private static final long serialVersionUID = 1L;
	
	private String userName;
	private String password;
	
	public UserDetailsImpl(Usuario user) {
		this.userName = user.getUsuario();
		this.password = user.getSenha();
		// construtor de classe
	}
	
	public UserDetailsImpl() {}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password; // método para resgatar a senha do usuário do token
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userName; // método para resgatar o nome de usuário do token
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true; // booleano que informa se a senha expirou ou não
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	} // aplicar uma regra de negócio que já existe, no caso, userdetails

}
