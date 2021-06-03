package org.generation.blogPessoal.service;

import java.nio.charset.Charset;
import java.util.Optional;
import org.apache.commons.codec.binary.Base64;

import org.generation.blogPessoal.model.UserLogin;
import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service // classe se trata de um serviço
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository; // injetando o usuáriorepository através do objeto repository
	
	public Usuario CadastrarUsuario(Usuario usuario) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); // estanciando um novo objeto do tipo bcryptpasswordencoder e vamos chamá-lo de encoder
		
		String senhaEncoder = encoder.encode(usuario.getSenha()); // codificando a senha e armazenar dentro de uma variável do tipo string chamada de senha Encoder
		usuario.setSenha(senhaEncoder); // substituir a senha do objeto usuario pela senha criptografada
		
		return repository.save(usuario); // salvando a senha no banco através do nosso repository
	}
	
	public Optional<UserLogin> Logar(Optional<UserLogin> user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); // instanciando um objeto do tipo bcryptpasswordencoder, pois usaremos ele para encodar a senha do usuário
		Optional<Usuario> usuario = repository.findByUsuario(user.get().getUsuario());
		
		if(usuario.isPresent()) {
			if(encoder.matches(user.get().getSenha(), usuario.get().getSenha())) { // encoder.matches compara a senha digitada pelo usuario com a senha encodade no banco e os devolve um booleano
				
				String auth = user.get().getUsuario() +":" + user.get().getSenha(); // conectando o usuário+":"+a senha para gerarmos no token encodado
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII"))); // encodando o usuario juntamente com a senha no padrão (us-ascii) e incluindo em um array de bytes
				String authHeader = "Basic " + new String(encodedAuth); // inserindo tudo em ums String com o Prefixo Basic
				
				user.get().setToken(authHeader);
				user.get().setNome(usuario.get().getNome()); // populando o nosso objeto user pra retomar como recurso para nosso client
				user.get().setFoto(usuario.get().getFoto());
				user.get().setId(usuario.get().getId());
				user.get().setTipo(usuario.get().getTipo());
				
				return user;
			}
		}
		
		return null;
		
	}

}
